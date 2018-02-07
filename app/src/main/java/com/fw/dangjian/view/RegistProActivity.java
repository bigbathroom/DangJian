package com.fw.dangjian.view;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistProActivity extends BaseActivity{
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv)
    WebView wv;

    @Override
    protected int fillView() {
        return R.layout.activity_regist_pro;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("注册协议");
    }

    @Override
    protected void initData() {
        String url ="";
        wv.loadUrl(url);
    }
    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
        }
    }
}
