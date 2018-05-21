package com.fw.dangjian.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
    @BindView(R.id.iv_qq)
    ImageView iv_qq;
    @BindView(R.id.iv_weixin)
    ImageView iv_weixin;

    private String phone;
    private String pwd;
    private LoginPresenter loginPresenter;
    private int managerid;
    private int phone1;

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

    @OnClick({R.id.tv_forget_psw,R.id.tv_login,R.id.main_ll,R.id.iv_qq,R.id.iv_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_psw:
                jumpToActivity(ForgetPwdActivity.class);
                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString();
                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    ToastUtils.showShort(act,"手机号不能为空");
                    return ;
                }else if(!StringUtils.isMobileNo(phone)){
                    ToastUtils.showShort(act,"手机号格式不正确");
                    return ;
                }
                if (TextUtils.isEmpty(pwd)){
                    ToastUtils.showShort(act,"密码不能为空");
                    return ;
                }else if(!StringUtils.isPassword(pwd)){
                    ToastUtils.showShort(act,"请输入6~12位字母或数字");
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
            case R.id.iv_qq:
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.iv_weixin:
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);

                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> map) {

            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();

            //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
            String uid = map.get("uid");
            String openid = map.get("openid");//微博没有
            String unionid = map.get("unionid");//微博没有
            String access_token = map.get("access_token");
            String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
            String expires_in = map.get("expires_in");
            String name = map.get("name");
            String gender = map.get("gender");
            String iconurl = map.get("iconurl");

            Toast.makeText(getActivity(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();

            //TODO 拿到信息去请求登录接口。。。


            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getActivity(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onGetDataNext(LoginBean kongBean) {

        if (kongBean.result_code!= null&&kongBean.result_code.equals("200")){

                if (kongBean.result != null){

                    managerid = kongBean.result.managerid;
                    phone1 = kongBean.result.phone;
                    SPUtils.put(getActivity(), ConstanceValue.LOGIN_TOKEN,managerid);
                    SPUtils.put(getActivity(), ConstanceValue.ADMIN,phone1);
                    getActivity().finish();
                }

        }else{
            ToastUtils.show(getActivity(),kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }
}
