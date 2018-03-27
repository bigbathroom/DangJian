package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.iv_banner)
    ImageView iv_banner;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.ll4)
    LinearLayout ll4;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.ll6)
    LinearLayout ll6;



    @Override
    protected int fillView() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("功能管理");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left,R.id.iv_add,R.id.ll1,R.id.ll2,R.id.ll3,R.id.ll4,R.id.ll5,R.id.ll6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.iv_add:

                break;
            case R.id.ll1:
                Intent intent1 = new Intent(this, DJEActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll2:
                Intent intent2 = new Intent(this, PartyMemberActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll3:

                Intent intent3 = new Intent(this, MapActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll4:
                Intent intent4 = new Intent(this, MoneyActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll5:
                Intent intent5 = new Intent(this, FenXIActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll6:
                Intent intent6 = new Intent(this, BoxActivity.class);
                startActivity(intent6);
                break;

        }
    }
}
