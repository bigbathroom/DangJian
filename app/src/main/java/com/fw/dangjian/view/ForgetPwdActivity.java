package com.fw.dangjian.view;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.IdentifyCode;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.RegistBean;
import com.fw.dangjian.mvpView.RegistMvpView;
import com.fw.dangjian.presenter.RegistPresenter;
import com.fw.dangjian.util.KeyboardLayout;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity implements RegistMvpView{

    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.main_ll)
    KeyboardLayout main_ll;
    @BindView(R.id.et_name)
    EditText et_phone;
    @BindView(R.id.et_yzm)
    EditText et_yzm;
    @BindView(R.id.btn_getcode)
    Button btn_getcode;
    @BindView(R.id.et_psw)
    EditText et_pwd;
    @BindView(R.id.tv_login)
    TextView btn_login;
    private String phone;
    private String zym;
    private String pwd;
    private RegistPresenter registPresenter;
    @Override
    protected int fillView() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("忘记密码");
        registPresenter = new RegistPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left,R.id.btn_getcode,R.id.tv_login,R.id.main_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.btn_getcode:
                phone = et_phone.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    ToastUtils.showShort(act,"手机号不能为空");
                    return ;
                }else if(!StringUtils.isMobileNo(phone)){
                    ToastUtils.showShort(act,"手机号格式不正确");
                    return ;
                }

                registPresenter.getIdentifyMessge(phone,2);
                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString();
                zym = et_yzm.getText().toString();
                pwd = et_pwd.getText().toString();

                if (TextUtils.isEmpty(phone)){
                    ToastUtils.showShort(act,"手机号不能为空");
                    return ;
                }else if(!StringUtils.isMobileNo(phone)){
                    ToastUtils.showShort(act,"手机号格式不正确");
                    return ;
                }

                if (TextUtils.isEmpty(zym)){
                    ToastUtils.showShort(act,"验证码不能为空");
                    return ;
                }

                if (TextUtils.isEmpty(pwd)){
                    ToastUtils.showShort(act,"密码不能为空");
                    return ;
                }else if(!StringUtils.isPassword(pwd)){
                    ToastUtils.showShort(act,"密码格式不正确");
                    return ;
                }

                //    TODO 注册
                registPresenter.forget(phone, pwd,zym);
                break;
            case R.id.main_ll:
                if(main_ll.isKeyboardActive()){
                    InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                break;
        }
    }


    @Override
    public void onRegisterNext(RegistBean kongBean) {

    }

    @Override
    public void onGetIdentifyCodeNext(IdentifyCode identifyCode) {
        if (identifyCode.result_code != null&&identifyCode.result_code.equals("200")) {
            btn_getcode.setEnabled(false);
            TimeCount mTimeCount = new TimeCount(60000,1000);
            mTimeCount.start();
        }else {

        }
    }

    @Override
    public void onForgetPswNext(KongBean kongBean) {
        btn_login.setEnabled(false);
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {

            startActivity(new Intent(this,LoginActivity.class));
            finish();

        }else {
            btn_login.setEnabled(true);
            ToastUtils.show(this,kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            btn_getcode.setText("获取验证码");
            btn_getcode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            btn_getcode.setText(millisUntilFinished / 1000 + "秒重发");
        }
    }
}
