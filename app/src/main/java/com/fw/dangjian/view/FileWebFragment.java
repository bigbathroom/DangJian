package com.fw.dangjian.view;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.R.attr.id;
import static com.fw.dangjian.netUtil.Constants.BASE_URL;

public class FileWebFragment extends BaseFragment {

    @BindView(R.id.wv)
    WebView wv;
    private String timeString;
    private int studyId;
    private int managerId;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_file_web, null);
    }

    public static FileWebFragment newInstance(int studyId) {
        FileWebFragment fragment = new FileWebFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("studyId", studyId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initUi() {
        studyId = getArguments().getInt("studyId");
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                // handler.cancel();// Android默认的处理方式
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }
        });


        timeString = StringUtils.getTimeString();

        String  url = BASE_URL + "/note/" + studyId + "?managerid=" + managerId;

        final Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);

        if (id != -1) {

            wv.loadUrl(url, map);
        }
    }

    @Override
    protected void initData() {

    }
}
