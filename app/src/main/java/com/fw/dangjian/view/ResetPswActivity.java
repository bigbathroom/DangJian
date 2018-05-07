package com.fw.dangjian.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.mvpView.PutPasswordMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPswActivity extends AppCompatActivity implements PutPasswordMvpView{

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.et_old_psw)
    EditText et_old_psw;
    @BindView(R.id.et_again_psw)
    EditText et_again_psw;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private UserPresenter userPresenter;
    int managerid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_psw);
        MyApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this, 15);
        managerid = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        userPresenter = new UserPresenter();
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

    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {

    }
}
