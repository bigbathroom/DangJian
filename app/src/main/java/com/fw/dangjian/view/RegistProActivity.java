package com.fw.dangjian.view;

import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.netUtil.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistProActivity extends BaseActivity{
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv)
    WebView wv;
    private int type;

    @Override
    protected int fillView() {
        return R.layout.activity_regist_pro;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);

        type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            tv_title.setText("注册协议");
            String url = Constants.BASE_URL+"userDeal";
            wv.loadUrl(url);
        }else if (type == 2){
            String url = Constants.BASE_URL+"userDeal";
            wv.loadUrl(url);
            tv_title.setText("党费收缴说明");
        }

    }

    @Override
    protected void initData() {
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
