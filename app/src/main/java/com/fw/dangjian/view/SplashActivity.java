package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

public class SplashActivity extends BaseActivity {

    private boolean isFirst=true;
    @Override
    protected int fillView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initUi() {
        isFirst=getIsFirst();

        new Handler(new Handler.Callback() {
            //处理接收到的消息的方法
            @Override
            public boolean handleMessage(Message arg0) {
                //实现页面跳转
                if (isFirst){
                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
                return false;
            }
        }).sendEmptyMessageDelayed(0, 2000); //表示延时三秒进行任务的执行





    }

    @Override
    protected void initData() {

    }

    private boolean getIsFirst() {
        String read = (String) SPUtils.get(this,ConstanceValue.IS_FIRST_START,"false");
        if ("true".equals(read)){
            return false;
        }else {
            return true;
        }
    }
}
