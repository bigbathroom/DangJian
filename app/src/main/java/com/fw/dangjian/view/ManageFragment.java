package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class ManageFragment extends BaseFragment {
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
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_manage, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("功能管理");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.search,R.id.iv_add,R.id.ll1,R.id.ll2,R.id.ll3,R.id.ll4,R.id.ll5,R.id.ll6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:

                break;
            case R.id.iv_add:

                break;
            case R.id.ll1:
                Intent intent1 = new Intent(getActivity(), DJEActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll2:
                Intent intent2 = new Intent(getActivity(), PartyMemberActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll3:

                Intent intent3 = new Intent(getActivity(), MapActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll4:
                Intent intent4 = new Intent(getActivity(), MoneyActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll5:
                Intent intent5 = new Intent(getActivity(), FenXIActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll6:
                Intent intent6 = new Intent(getActivity(), BoxActivity.class);
                startActivity(intent6);
                break;

        }
    }



}