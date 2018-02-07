package com.fw.dangjian.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.BoxBean;
import com.fw.dangjian.bean.BoxPageBean;
import com.fw.dangjian.mvpView.BoxMvpView;
import com.fw.dangjian.presenter.BoxPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BoxActivity extends BaseActivity implements BoxMvpView{
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tablayout2)
    TabLayout tablayout2;
    @BindView(R.id.vp2)
    ViewPager vp2;

    private int index;

    private List<Fragment> mFragments;
    private BoxPresenter boxPresenter;
    private List<String> mTabTitles;
    private List<BoxBean.ResultBean> results;

    @Override
    protected int fillView() {
        return R.layout.activity_box;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("党建百宝箱");
        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        boxPresenter = new BoxPresenter();
        boxPresenter.getColunm(this);
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
    public void onGetColunmDataNext(BoxBean boxBean) {
        if(boxBean.result_code != null && boxBean.result_code.equals("200")){
            results = boxBean.result;

            for (int i = 0;i<results.size();i++){
                BoxFragment boxFragment = BoxFragment.newInstance(String.valueOf(results.get(i).id));
                mFragments.add(boxFragment);
                mTabTitles.add(results.get(i).name);
            }

            setData();
        }else {

            Toast.makeText(this, boxBean.reason, Toast.LENGTH_SHORT).show();
        }
    }


    private void setData() {
        vp2.setOffscreenPageLimit(1);
        vp2.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles.get(position);
            }

            @Override
            public int getCount() {
                return mTabTitles.size();
            }
        });


        tablayout2.post(new Runnable() {
            @Override
            public void run() {
                tablayout2.setupWithViewPager(vp2);
            }
        });
    }


    @Override
    public void onGetDataNext(BoxPageBean boxPageBean) {

    }
}
