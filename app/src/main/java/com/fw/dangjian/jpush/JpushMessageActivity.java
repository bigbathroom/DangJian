package com.fw.dangjian.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class JpushMessageActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int fillView() {
        return R.layout.activity_jpush_message;
    }

    @Override
    protected void initUi() {
        tv_title.setText("推送消息");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tv.setText("Title : " + title + "  " + "Content : " + content);
        }
    }

    @Override
    protected void initData() {

    }
}
