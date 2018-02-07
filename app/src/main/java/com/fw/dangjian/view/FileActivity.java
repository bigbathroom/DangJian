package com.fw.dangjian.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.FileBean;
import com.fw.dangjian.mvpView.FileMvpView;
import com.fw.dangjian.presenter.FilePresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class FileActivity extends BaseActivity implements FileMvpView{
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_organzition)
    TextView tv_organzition;
    @BindView(R.id.tv_zhibu)
    TextView tv_zhibu;
    @BindView(R.id.tv_liucheng)
    TextView tv_liucheng;
    @BindView(R.id.tv_hangye)
    TextView tv_hangye;
    @BindView(R.id.tv_renzheng)
    TextView tv_renzheng;
    @BindView(R.id.tv_yiji)
    TextView tv_yiji;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.tv_kecheng)
    TextView tv_kecheng;
    @BindView(R.id.tv_qiandao)
    TextView tv_qiandao;
    @BindView(R.id.tv_active)
    TextView tv_active;

    private FilePresenter filePresenter;
    private int managerId;

    @Override
    protected int fillView() {
        return R.layout.activity_file;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("档案详情");
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        filePresenter = new FilePresenter();
        filePresenter.getFile(managerId,this);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
        }
    }



    @Override
    public void onGetDataNext(FileBean kongBean) {
        if(kongBean.result_code != null && kongBean.result_code.equals("200")){
            String manager_phone = kongBean.result.manager_phone;
            String organize_name = kongBean.result.organize_name;
            tv_phone.setText(manager_phone);
            tv_organzition.setText(organize_name);
        }else{
            ToastUtils.show(this,kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }
}
