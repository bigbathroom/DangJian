package com.fw.dangjian.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.PrintNoteAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.PrintBean;
import com.fw.dangjian.bean.PrintHtmlBean;
import com.fw.dangjian.mvpView.PrintMvpView;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PrintNoteActivity extends BaseActivity implements PrintMvpView {
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.recyclerview1)
    RecyclerView nrecycler;
    @BindView(R.id.cb_all)
    CheckBox chkbox_all;

    private List<PrintBean> beanList;
    private String from;
    PrintNoteAdapter mAdapter;
    public Map<Integer, Boolean> selectedMap;
    public HashSet<PrintBean> printIdSet;
    private int managerId;
    private String timeString;

    WebView mWebView;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) { //判断是否全部选中
                boolean isAll = (boolean) msg.obj;
                if (isAll) {
                    chkbox_all.setChecked(true);//是全部被选中，改变全选check状态为选中
                } else {
                    chkbox_all.setChecked(false);//不是，继续维持未选中状态
                }
            } else if (msg.what == 11) {
                printIdSet = (HashSet<PrintBean>) msg.obj;
            }
        }
    };
    private UserPresenter userPresenter;

    @Override
    protected int fillView() {
        return R.layout.activity_print_note;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("打印");

        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        timeString = StringUtils.getTimeString();
        beanList = (List<PrintBean>) getIntent().getSerializableExtra("BeanList");
        from = getIntent().getStringExtra("from");
        if (from.equals("learn")) {
            tv_title.setText("学习笔记");
        } else {
            tv_title.setText("会议笔记");
        }

        // 保存每条记录是否被选中的状态
        selectedMap = new HashMap<Integer, Boolean>();
        // 保存被选中记录作数据库表中的Id
        printIdSet = new HashSet<PrintBean>();
        for (int i = 0; i < beanList.size(); i++) {
            selectedMap.put(i, false);
        }

    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        mAdapter = new PrintNoteAdapter(beanList, this, handler, selectedMap, printIdSet);
        nrecycler.setAdapter(mAdapter);

        userPresenter = new UserPresenter();

    }

    @OnClick({R.id.left, R.id.tv_right, R.id.cb_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.cb_all:
                if (chkbox_all.isChecked()) {
                    for (int i = 0; i < beanList.size(); i++) {
                        selectedMap.put(i, true);
                        //click事件：全选checkbox被勾选则把所有添加到delContactsIdSet中
                        printIdSet.add(beanList.get(i));
                    }
                } else {
                    for (int i = 0; i < beanList.size(); i++) {
                        selectedMap.put(i, false);
                    }
                    //click事件：全选checkbox被取消勾选则把delContactsIdSet清空
                    printIdSet.clear();
                }
                mAdapter = new PrintNoteAdapter(beanList, this, handler, selectedMap, printIdSet);
                nrecycler.setAdapter(mAdapter);

                break;
            case R.id.tv_right:
                List<PrintBean> result = new ArrayList<>();
                for (PrintBean s : printIdSet) {
                    result.add(s);
                }

                Gson gson = new Gson();
                String json = gson.toJson(result);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);


                if (result.size() > 0) {
                /*    // 打印
                    String jobName = this.getString(R.string.app_name) + " Document";

                    PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                    PrintAttributes.Builder builder = new PrintAttributes.Builder();

                    if(from.equals("learn")){
                        MyPrintAdapter myPrintAdapter = new MyPrintAdapter(this,result,result.size(),0);
                        printManager.print(jobName, myPrintAdapter, builder.build());
                    }else{
                        MyPrintAdapter myPrintAdapter = new MyPrintAdapter(this,result,result.size(),1);
                        printManager.print(jobName, myPrintAdapter, builder.build());
                    }*/

                    if (from.equals("learn")) {
                        userPresenter.getLearnNoteWeb(managerId, body, this);
                    } else {
                        userPresenter.getMeetingsNoteWeb(managerId,body,this);

                    }

                } else {
                    ToastUtils.show(this, "您还未选择", Toast.LENGTH_SHORT);
                }


                break;
        }
    }





    @Override
    public void onGetLearnNext(PrintHtmlBean actionBean) {
        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {
            String  url =  actionBean.result;

            Log.d("ss",url);
            doWebViewPrint(url);
        }else{
            ToastUtils.showShort(act,"没有内容");
        }

    }

    @Override
    public void onGetMeetingNext(PrintHtmlBean actionBean) {

        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {
           String  url =  actionBean.result;

            Log.d("ss",url);
            doWebViewPrint(url);

        }else{
            ToastUtils.showShort(act,"没有内容");
        }

    }


    private void doWebViewPrint(String  url) {
        Map<String, String> map = new HashMap<>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);
        map.put("managerid", managerId + "");

        WebView webView = new WebView(this);


        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }


            @Override

            public void onPageFinished(WebView view, String url) {

                Log.i(TAG, "page finished loading " + url);

                createWebPrintJob(view);

                mWebView = null;

            }

        });

        webView.loadUrl(url,map);

        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView) {

        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();


        String jobName = getString(R.string.app_name) + " Document";

        PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());

    }
}
