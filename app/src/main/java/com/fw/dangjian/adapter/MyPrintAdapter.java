package com.fw.dangjian.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.fw.dangjian.bean.MeetingPrintBean;
import com.fw.dangjian.bean.PrintBean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MyPrintAdapter extends PrintDocumentAdapter {

    Context mContext;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument myPdfDocument;
    List<PrintBean> result;
    int flag;
    List<MeetingPrintBean.ResultBean> result1;
    private int totalpages;

    public MyPrintAdapter(Context context,List<PrintBean> result,int size,int flag) {//这里传各种需要的参数就行
        this.mContext = context;
        this.result = result;
        this.flag = flag;

    }

    public MyPrintAdapter(Context context,List<MeetingPrintBean.ResultBean> result1,int flag) {//这里传各种需要的参数就行
        this.mContext = context;
        this.result1 = result1;
        this.flag = flag;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle metadata) {

        myPdfDocument = new PrintedPdfDocument(mContext, newAttributes); //创建可打印PDF文档对象

        pageHeight = newAttributes.getMediaSize().getHeightMils() / 1000 * 72; //设置尺寸,为什么是1000 * 72, 72dpi
        pageWidth = newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

        // Compute the expected number of printed pages
        totalpages = computePageCount(newAttributes);

        if (totalpages > 0) {
            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                    .Builder("whiteRadish")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT);
//                    .setPageCount(totalpages);  //构建文档配置信息

            PrintDocumentInfo info = builder.build();
            callback.onLayoutFinished(info, true);
        } else {
            callback.onLayoutFailed("Page count is zero.");
        }
    }

    private int computePageCount(PrintAttributes printAttributes) {
        int itemsPerPage; // default item count for portrait mode
        int printItemCount;
        if (flag == 1){
            itemsPerPage = 1;
            PrintAttributes.MediaSize pageSize = printAttributes.getMediaSize();
            if (!pageSize.isPortrait()) {
                // Six items per page in landscape orientation
                itemsPerPage = 1;
            }
//            // Determine number of print items
            printItemCount = result.size();
        }else if (flag == 2){
            itemsPerPage = 3;
            PrintAttributes.MediaSize pageSize = printAttributes.getMediaSize();
            if (!pageSize.isPortrait()) {
                // Six items per page in landscape orientation
                itemsPerPage = 1;
            }
           printItemCount = result1.size();
        }else{
            itemsPerPage = 1;
            PrintAttributes.MediaSize pageSize = printAttributes.getMediaSize();
            if (!pageSize.isPortrait()) {
                // Six items per page in landscape orientation
                itemsPerPage = 1;
            }
//            // Determine number of print items
            printItemCount = result.size();
        }

        return (int) Math.ceil(printItemCount / itemsPerPage);
    }



    @Override
    public void onWrite(final PageRange[] pageRanges, final ParcelFileDescriptor destination, final CancellationSignal cancellationSignal,
                        final WriteResultCallback callback) {


        for (int i = 0; i < totalpages; i++) {
            if (pageInRange(pageRanges, i)) //保证页码正确
            {
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i).create();//创建对应的Page

                PdfDocument.Page page = myPdfDocument.startPage(newPage);  //创建新页面

                if (cancellationSignal.isCanceled()) {  //取消信号
                    callback.onWriteCancelled();
                    myPdfDocument.close();
                    myPdfDocument = null;
                    return;
                }
                drawPage(page, i);  //将内容绘制到页面Canvas上
                myPdfDocument.finishPage(page);
            }
        }

        try {
            myPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            myPdfDocument.close();
            myPdfDocument = null;
        }

        callback.onWriteFinished(pageRanges);
    }

    private boolean pageInRange(PageRange[] pageRanges, int page) {
        for (int i = 0; i < pageRanges.length; i++) {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    //页面绘制
    private void drawPage(PdfDocument.Page page, int i) {
        //这里是页码。页码不能从0开始
//        pagenumber++;
        Canvas canvas = page.getCanvas();
        TextPaint paint = new TextPaint();
        //这里绘制要在打印的纸上的内容，如果精确一点的话，根据pageHeight，pageWidth计算就行，这里的内容和自定义View的内容一样,
        //把自定义View绘制的东西拉过来直接就可以用
        int titleBaseLine = 50;
        int leftMargin = 60;

        if (flag == 1){
//            会议
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            StaticLayout myStaticLayout = new StaticLayout(result.get(i).post_title, paint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            canvas.translate(0,titleBaseLine);
            myStaticLayout.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout1 = new StaticLayout(result.get(i).post_excerpt, paint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            canvas.translate(0,35);
            myStaticLayout1.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout6 = new StaticLayout("时间："+result.get(i).meeting_date_gmt+"\u3000\u3000"+"地址："+result.get(i).meeting_address+"\n"+"主持人："+result.get(i).meeting_author+"\u3000"+"记录人："+result.get(i).meeting_recorder+"\u3000"+"应到人数："+result.get(i).meeting_memberYD+"\u3000"+"实到人数："+result.get(i).meeting_memberSD, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(40,30);
            myStaticLayout6.draw(canvas);


            paint.setTextSize(13);
            paint.setColor(Color.BLACK);
            Spanned ss = Html.fromHtml(result.get(i).post_content);
            StaticLayout myStaticLayout2 = new StaticLayout(ss, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(0,50);
            myStaticLayout2.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.GRAY);
            StaticLayout myStaticLayout3 = new StaticLayout("笔记内容：", paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(0,titleBaseLine+myStaticLayout2.getLineCount()*11);
            myStaticLayout3.draw(canvas);


            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout4 = new StaticLayout(result.get(i).content, paint, canvas.getWidth()-leftMargin-25, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(25,30);
            myStaticLayout4.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout7 = new StaticLayout(result.get(i).name, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(canvas.getWidth()-180,30);
            myStaticLayout7.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout5 = new StaticLayout(result.get(i).addtime, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(-80,30);
            myStaticLayout5.draw(canvas);

        }else if (flag == 2){
//            单个会议
            if (i == 0){
                paint.setColor(Color.BLACK);
                paint.setTextSize(20);
                StaticLayout myStaticLayout = new StaticLayout(result1.get(i).post_title, paint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
                canvas.translate(0,titleBaseLine);
                myStaticLayout.draw(canvas);

                paint.setTextSize(14);
                paint.setColor(Color.BLACK);
                StaticLayout myStaticLayout1 = new StaticLayout(result1.get(i).post_excerpt, paint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
                canvas.translate(0,35);
                myStaticLayout1.draw(canvas);

                paint.setTextSize(14);
                paint.setColor(Color.BLACK);
                StaticLayout myStaticLayout6 = new StaticLayout("时间："+result1.get(i).meeting_date_gmt+"\u3000\u3000"+"地址："+result1.get(i).meeting_address+"\n"+"主持人："+result1.get(i).meeting_author+"\u3000"+"记录人："+result1.get(i).meeting_recorder+"\u3000"+"应到人数："+result1.get(i).meeting_memberYD+"\u3000"+"实到人数："+result1.get(i).meeting_memberSD, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                canvas.translate(40,30);
                myStaticLayout6.draw(canvas);


                paint.setTextSize(13);
                paint.setColor(Color.BLACK);
                Spanned ss = Html.fromHtml(result1.get(i).post_content);
                StaticLayout myStaticLayout2 = new StaticLayout(ss, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                canvas.translate(0,50);
                myStaticLayout2.draw(canvas);

                for(int a = 0;a< 3;a++){
                    paint.setTextSize(14);
                    paint.setColor(Color.GRAY);
                    StaticLayout myStaticLayout3 = new StaticLayout("笔记内容：", paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    if (a == 0){
                        canvas.translate(0,titleBaseLine+myStaticLayout2.getLineCount()*10);
                    }else{
                        int length = result1.get(a-1).content.length()/30+1;
                        canvas.translate(0,titleBaseLine+myStaticLayout2.getLineCount()*10+length*20+50);
                    }
                    myStaticLayout3.draw(canvas);
                    canvas.save();

                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout4 = new StaticLayout(result1.get(a).content, paint, canvas.getWidth()-leftMargin-25, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(25,30);
                    myStaticLayout4.draw(canvas);


                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout7 = new StaticLayout(result1.get(a).name, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(canvas.getWidth()-180,40+myStaticLayout4.getLineCount()*10);
                    myStaticLayout7.draw(canvas);

                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout5 = new StaticLayout(result1.get(a).addtime, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(-80,30);
                    myStaticLayout5.draw(canvas);
                    canvas.restore();
                }
            }else{
                for(int a = i*3;a< 3+i*3;a++){
                    paint.setTextSize(14);
                    paint.setColor(Color.GRAY);
                    StaticLayout myStaticLayout3 = new StaticLayout("笔记内容：", paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    if (a == i*3){
                        canvas.translate(40,titleBaseLine);
                    }else{
                        int length = result1.get(a-1).content.length()/30+1;
                        canvas.translate(0,titleBaseLine+length*20+50);
                    }
                    myStaticLayout3.draw(canvas);
                    canvas.save();

                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout4 = new StaticLayout(result1.get(a).content, paint, canvas.getWidth()-leftMargin-25, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(25,30);
                    myStaticLayout4.draw(canvas);


                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout7 = new StaticLayout(result1.get(a).name, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(canvas.getWidth()-180,40+myStaticLayout4.getLineCount()*10);
                    myStaticLayout7.draw(canvas);

                    paint.setTextSize(14);
                    paint.setColor(Color.BLACK);
                    StaticLayout myStaticLayout5 = new StaticLayout(result1.get(a).addtime, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    canvas.translate(-80,30);
                    myStaticLayout5.draw(canvas);
                    canvas.restore();
                }
            }

        }else{
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            StaticLayout myStaticLayout = new StaticLayout(result.get(i).post_title, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            canvas.translate(30,titleBaseLine);
            myStaticLayout.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.GRAY);
            StaticLayout myStaticLayout3 = new StaticLayout("笔记内容：", paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(0,titleBaseLine);
            myStaticLayout3.draw(canvas);


            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout4 = new StaticLayout(result.get(i).content, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(25,30);
            myStaticLayout4.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout7 = new StaticLayout(result.get(i).addtime, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(canvas.getWidth()-150,30);
            myStaticLayout7.draw(canvas);

            paint.setTextSize(14);
            paint.setColor(Color.BLACK);
            StaticLayout myStaticLayout5 = new StaticLayout(result.get(i).addtime, paint, canvas.getWidth()-leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            canvas.translate(-80,30);
            myStaticLayout5.draw(canvas);
        }

    }

}
