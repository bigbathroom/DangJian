package com.fw.dangjian.view;


import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.fw.dangjian.netUtil.Constants.BASE_URL;

public class WebActivity extends BaseActivity {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv)
    WebView wv;

    private int managerId;
    int flag;
    int id;
    private String timeString;
    private String url2;


    @Override
    protected int fillView() {
        return R.layout.activity_web;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {

            flag = intent.getIntExtra("flag_id", -1);
            id = intent.getIntExtra("id", -1);
        }

        timeString = StringUtils.getTimeString();

        String url1 = BASE_URL +"plan/info";
        if(id != -1){
            url2 = BASE_URL+"meeting/"+id;
        }


        Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);

        if (flag == 100) {
            wv.loadUrl(url1, map);
            tv_title.setText("学习计划");
        } else if (flag == 200) {
            wv.loadUrl(url2, map);
            tv_title.setText("会议纪要");
        }
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
