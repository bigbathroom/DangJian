package com.fw.dangjian.view;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

import butterknife.BindView;

public class GardenFragment extends BaseFragment {

    @BindView(R.id.wv)
    WebView  wv;

    private int managerId;
    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_garden, null);
    }

    public static GardenFragment newInstance(String code) {
        GardenFragment fragment = new GardenFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void initUi() {
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

    }

    @Override
    public void onStart() {
        super.onStart();
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //开启 Application Caches 功能
        webSettings.setAppCacheEnabled(true);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        String url = "http://djbbs.yaoyu-soft.com/bbs/dangjian/m";
        wv.loadUrl(url);

        wv.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }
}
