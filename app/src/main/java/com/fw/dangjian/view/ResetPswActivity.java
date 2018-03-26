package com.fw.dangjian.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.mvpView.PutPasswordMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPswActivity extends BaseActivity implements PutPasswordMvpView{

    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_old_psw)
    EditText et_old_psw;
    @BindView(R.id.et_again_psw)
    EditText et_again_psw;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private UserPresenter userPresenter;
    int managerid;

    @Override
    protected int fillView() {
        return R.layout.activity_reset_psw;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("修改密码");
        managerid = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        userPresenter = new UserPresenter();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left,R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_login:

                String initPwd = et_old_psw.getText().toString();
                String newPwd1 = et_again_psw.getText().toString();

                if (TextUtils.isEmpty(initPwd)){
                    Toast.makeText(this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (TextUtils.isEmpty(newPwd1)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return ;
                }else  if(!StringUtils.isPassword(newPwd1)) {
                    Toast.makeText(this, "请输入6~12位字母或数字", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(initPwd.length() > 0 && newPwd1.length() > 0) {
                    // 调用后台接口
                    userPresenter.changePsw(managerid,initPwd,newPwd1,this);
                }

                break;
        }
}

    @Override
    public void onPutPasswordNext(KongBean kongBean) {
        if ( kongBean.result_code!= null&& kongBean.result_code.equals("200")){
            ToastUtils.show(this,"修改成功",Toast.LENGTH_SHORT);
            finish(); //调用返回键
        }else{
            ToastUtils.show(this,"修改失败",Toast.LENGTH_SHORT);
        }


    }
}
