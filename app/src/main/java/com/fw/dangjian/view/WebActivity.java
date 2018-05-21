package com.fw.dangjian.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.MyPrintAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.MeetingPrintBean;
import com.fw.dangjian.mvpView.MeetingWebMvpView;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.fw.dangjian.netUtil.Constants.BASE_URL;

public class WebActivity extends BaseActivity implements MeetingWebMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.wv)
    WebView wv;

    private int managerId;
    int flag;
    int id;
    private String timeString;
    private String url2;
    private int isAdmin;
    private UserPresenter userPresenter;
    private List<MeetingPrintBean.ResultBean> result;
    WebView  mWebView;

    @Override
    protected int fillView() {
        return R.layout.activity_web;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        isAdmin = (int) SPUtils.get(this, ConstanceValue.ADMIN, -1);

        userPresenter = new UserPresenter();


        wv.setWebContentsDebuggingEnabled(true);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (isAdmin == 1) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText("打印");
        } else {
            tv_right.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {

            flag = intent.getIntExtra("flag_id", -1);
            id = intent.getIntExtra("id", -1);
        }

        timeString = StringUtils.getTimeString();

        final String url1 = BASE_URL + "plan/info";
        if (id != -1) {
            url2 = BASE_URL + "meeting/" + id;
        }


        final Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);
        map.put("managerid", managerId + "");

//        Toast.makeText(this, managerId + "", Toast.LENGTH_SHORT).show();
        if (flag == 100) {
            wv.loadUrl(url1, map);
            tv_title.setText("学习计划");
        } else if (flag == 200) {
            wv.loadUrl(url2, map);
            tv_title.setText("会议详情");
        }


        wv.setWebChromeClient(new WebChromeClient() {
                                  // 拦截输入框(原理同方式2)
                                  // 参数message:代表promt（）的内容（不是url）
                                  // 参数result:代表输入框的返回值
                                  @Override
                                  public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                                      final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                                      builder.setTitle("").setMessage(message);

                                      final EditText et = new EditText(view.getContext());
                                      et.setSingleLine();
                                      et.setText(defaultValue);
                                      builder.setView(et)
                                              .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      wv.loadUrl(url1, map);
                                                      result.confirm(et.getText().toString());

                                                  }

                                              })
                                              .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      result.cancel();
                                                  }
                                              });

                                      // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                                      builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                          public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                              Log.v("onJsPrompt", "keyCode==" + keyCode + "event=" + event);
                                              return true;
                                          }
                                      });

                                      // 禁止响应按back键的事件
                                      // builder.setCancelable(false);
                                      AlertDialog dialog = builder.create();
                                      dialog.show();
                                      dialog.getWindow().setLayout((int) dip2px(400), LinearLayout.LayoutParams.WRAP_CONTENT);
                                      return true;
                                      // return super.onJsPrompt(view, url, message, defaultValue,
                                      // result);

                                  }

// 通过alert()和confirm()拦截的原理相同，此处不作过多讲述

                                  // 拦截JS的警告框
                                  @Override
                                  public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                                      final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                                      builder.setTitle("").setMessage(message).setPositiveButton("确定", null);

                                      // 不需要绑定按键事件
                                      // 屏蔽keycode等于84之类的按键
                                      builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                          public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                              Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
                                              return true;
                                          }
                                      });
                                      // 禁止响应按back键的事件
                                      builder.setCancelable(false);
                                      AlertDialog dialog = builder.create();
                                      dialog.show();
                                      dialog.getWindow().setLayout((int) dip2px(400), LinearLayout.LayoutParams.WRAP_CONTENT);
                                      result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
                                      return true;
                                      // return super.onJsAlert(view, url, message, result);

                                  }

                                  // 拦截JS的确认框
                                  @Override
                                  public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                                      final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                      builder.setTitle("")
                                              .setMessage(message)
                                              .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      wv.loadUrl(url1, map);
                                                      result.confirm();
                                                  }
                                              })
                                              .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      result.cancel();
                                                  }
                                              });
                                      builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                          @Override
                                          public void onCancel(DialogInterface dialog) {
                                              result.cancel();
                                          }
                                      });

                                      // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                                      builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                          @Override
                                          public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                              Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                                              return true;
                                          }
                                      });
                                      // 禁止响应按back键的事件
                                      // builder.setCancelable(false);
                                      AlertDialog dialog = builder.create();
                                      dialog.show();
                                      dialog.getWindow().setLayout((int) dip2px(400), LinearLayout.LayoutParams.WRAP_CONTENT);
                                      return true;
                                      // return super.onJsConfirm(view, url, message, result);

                                  }
                              }
        );


    }

    public static float dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }


    @OnClick({R.id.left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_right:
//              打印
//                userPresenter.getMeetingWeb(managerId, id, this);

                doWebViewPrint();

                break;
        }
    }

    private  void doWebViewPrint(){
        String url = BASE_URL+"slender/learn/getMeetingNotess_h5?meetingId="+id;
        Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);
        map.put("managerid", managerId + "");

        // Create a WebView object specifically for printing

        WebView webView =new WebView(this);



        webView.setWebViewClient(new WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view,String url){

                return false;
            }



            @Override

            public void onPageFinished(WebView view,String url){

                Log.i(TAG,"page finished loading "+ url);

                createWebPrintJob(view);

                mWebView =null;

            }

        });

        webView.loadUrl(url, map);

        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView){

        PrintManager printManager =(PrintManager)getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();


        String jobName = getString(R.string.app_name)+" Document";

        PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        wv.removeAllViews();
        wv.destroy();
    }


    @Override
    public void onGetDataNext(MeetingPrintBean actionBean) {

        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {

            result = actionBean.result;

            if (result.size() > 0) {
                // 打印
                String jobName = this.getString(R.string.app_name) + " Document";

                PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                PrintAttributes.Builder builder = new PrintAttributes.Builder();

                MyPrintAdapter myPrintAdapter = new MyPrintAdapter(this, result, 2);

                printManager.print(jobName, myPrintAdapter, builder.build());
            } else {
                ToastUtils.show(this, "数据为空", Toast.LENGTH_SHORT);
            }


        } else {
            ToastUtils.show(this, actionBean.result_msg, Toast.LENGTH_SHORT);
        }


    }
}
