package com.fw.dangjian.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.dialog.ErrorDialog;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView{
    public static final String TAG = "DJ";
    public Activity act;
    public int alpha = 15;
    public int color = Color.parseColor("#D00808");
    private ErrorDialog errorDialog;
    private int managerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fillView());
        act = this;
        MyApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(act, color, alpha);
        initUi();
        initData();
    }

    protected abstract int fillView();
    /**
     * 初始化界面控件
     */
    protected abstract void initUi();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    //    加载失败网络异常框
    public void showErrorDialog(){
        errorDialog = new ErrorDialog(this, R.style.MyDarkDialog) {
            @Override
            public void confirm() {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        };
        errorDialog.show();
    }
    public void closeErrorDialog(){
        errorDialog.dismiss();
    }
    //    用户id
    public int getManagerId() {
        managerId = (int) SPUtils.get(act, ConstanceValue.LOGIN_TOKEN, -1);
        return managerId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(errorDialog!=null){
            errorDialog.dismiss();
        }
    }

    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {
//        showErrorDialog();
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
