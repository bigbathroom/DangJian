package com.fw.dangjian.view;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
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

public class RegistFragment extends BaseFragment implements RegistMvpView {
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
    @BindView(R.id.xieyi)
    LinearLayout xieyi;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;
    private String phone;
    private String zym;
    private String pwd;
    private RegistPresenter registPresenter;


    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_regist, null);
    }

    @Override
    protected void initUi() {

        registPresenter = new RegistPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_getcode, R.id.tv_login, R.id.main_ll, R.id.xieyi, R.id.cb_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getcode:
                phone = et_phone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort(act, "手机号不能为空");
                    return;
                } else if (!StringUtils.isMobileNo(phone)) {
                    ToastUtils.showShort(act, "手机号格式不正确");
                    return;
                }

                registPresenter.getIdentifyMessge(phone, 1);
                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString();
                zym = et_yzm.getText().toString();
                pwd = et_pwd.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort(act, "手机号不能为空");
                    return;
                } else if (!StringUtils.isMobileNo(phone)) {
                    ToastUtils.showShort(act, "手机号格式不正确");
                    return;
                }

                if (TextUtils.isEmpty(zym)) {
                    ToastUtils.showShort(act, "验证码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort(act, "密码不能为空");
                    return;
                } else if (!StringUtils.isPassword(pwd)) {
                    ToastUtils.showShort(act, "请输入6~12位字母或数字");
                    return;
                }
                if (!cb_agree.isChecked()) {
                    ToastUtils.showShort(act, "请阅读并同意用户协议");
                    return;
                }

                //    TODO 注册
                registPresenter.regist(phone, pwd, zym);
                break;
            case R.id.xieyi:

                Intent intent = new Intent(getActivity(), RegistProActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);

                break;
            case R.id.main_ll:
                if (main_ll.isKeyboardActive()) {
                    InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                break;
        }
    }


    @Override
    public void onRegisterNext(RegistBean kongBean) {
        btn_login.setEnabled(false);
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {

            jumpToActivity(MainActivity.class);
            getActivity().finish();

        } else {
            btn_login.setEnabled(true);
            ToastUtils.show(getActivity(), kongBean.result_msg, Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void onGetIdentifyCodeNext(IdentifyCode identifyCode) {
        if (identifyCode.result_code != null && identifyCode.result_code.equals("200")) {
            btn_getcode.setEnabled(false);
            TimeCount mTimeCount = new TimeCount(60000, 1000);
            mTimeCount.start();
        } else {

        }
    }

    @Override
    public void onForgetPswNext(KongBean kongBean) {

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
