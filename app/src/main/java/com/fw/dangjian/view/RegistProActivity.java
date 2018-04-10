package com.fw.dangjian.view;

import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.netUtil.Constants;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + RetrofitHelper.timeString));
        map.put("timestamp", RetrofitHelper.timeString);

        if (type == 1) {
            tv_title.setText("注册协议");
            String url = Constants.BASE_URL+"userDeal";
            wv.loadUrl(url,map);
        }else if (type == 2){
            String url = Constants.BASE_URL+"userDeal";
            wv.loadUrl(url,map);
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
