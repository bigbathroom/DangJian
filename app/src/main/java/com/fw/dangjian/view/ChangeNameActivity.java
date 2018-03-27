package com.fw.dangjian.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.mvpView.ChangeNameMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangeNameActivity extends BaseActivity implements ChangeNameMvpView{

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private UserPresenter userPresenter;
    int managerid;
    @Override
    protected int fillView() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("修改用户名");
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
                String s = et_name.getText().toString();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                userPresenter.saveUserName(managerid,s,this);
                break;
        }
    }


    @Override
    public void onsetDataNext(KongBean kongBean) {

        if ( kongBean.result_code!= null&& kongBean.result_code.equals("200")){

            Intent intent = new Intent();
            intent.putExtra("userName", et_name.getText().toString());
            setResult(RESULT_OK, intent);
            finish(); //调用返回键


        }else{
            ToastUtils.show(this,"修改失败",Toast.LENGTH_SHORT);
        }

    }
}
