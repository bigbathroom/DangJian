package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private boolean isFirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MyApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this, 15);
        initUi();
        initData();
    }


    public void initUi() {

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

    public void initData() {

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
