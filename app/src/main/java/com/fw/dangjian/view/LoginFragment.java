package com.fw.dangjian.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.LoginBean;
import com.fw.dangjian.mvpView.LoginMvpView;
import com.fw.dangjian.presenter.LoginPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.KeyboardLayout;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginFragment extends  BaseFragment implements LoginMvpView{
    @BindView(R.id.main_ll)
    KeyboardLayout main_ll;
    @BindView(R.id.et_name)
    EditText et_phone;
    @BindView(R.id.et_psw)
    EditText et_pwd;
    @BindView(R.id.tv_forget_psw)
    TextView  forget_pwd;
    @BindView(R.id.tv_login)
    TextView btn_login;

    private String phone;
    private String pwd;
    private LoginPresenter loginPresenter;
    private int managerid;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_login, null);
    }

    @Override
    protected void initUi() {
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_forget_psw,R.id.tv_login,R.id.main_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_psw:
                jumpToActivity(ForgetPwdActivity.class);
                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString();
                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    ToastUtils.showShort(act,"用户名不能为空");
                    return ;
                }else if(!StringUtils.isMobileNo(phone)){
                    ToastUtils.showShort(act,"手机号格式不正确");
                    return ;
                }
                if (TextUtils.isEmpty(pwd)){
                    ToastUtils.showShort(act,"密码不能为空");
                    return ;
                }else if(!StringUtils.isPassword(pwd)){
                    ToastUtils.showShort(act,"密码格式不正确");
                    return ;
                }
                //    TODO 登录
                loginPresenter.login(phone, pwd);
                break;
            case R.id.main_ll:
                if(main_ll.isKeyboardActive()){
                    InputMethodManager imm1 = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                break;
        }
    }


    @Override
    public void onGetDataNext(LoginBean kongBean) {

        if (kongBean.result_code!= null&&kongBean.result_code.equals("200")){

                if (kongBean.result != null){
                    managerid = kongBean.result.managerid;

                    SPUtils.put(getActivity(), ConstanceValue.LOGIN_TOKEN,managerid);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

        }else{
            ToastUtils.show(getActivity(),kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }
}
