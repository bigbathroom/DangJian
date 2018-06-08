package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.mvpView.HomeMvpView;
import com.fw.dangjian.presenter.HomePresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment implements HomeMvpView {

    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.iv_msg)
    ImageView iv_msg;
    @BindView(R.id.tv_message_count)
    TextView tv_message_count;


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private List<Fragment> mFragments;

    private HomePresenter homePresenter;

    private List<ColumnBean.ResultBean> result;
    int managerId;
    private List<String> mTabTitles;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }


    private void initUi() {
        tv_title.setText("党建");
        search.setVisibility(View.VISIBLE);
        iv_msg.setVisibility(View.VISIBLE);
        tv_message_count.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);
        mTabTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        homePresenter = new HomePresenter();
        homePresenter.getColunm(this);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    protected void setData() {

        for (int i = 0; i < mTabTitles.size(); i++) {
            NewsPageFragment newsFragment = NewsPageFragment.newInstance(String.valueOf(result.get(i).id),result.get(i).name);
            mFragments.add(newsFragment);
        }

        vp.setOffscreenPageLimit(1);
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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


        tablayout.post(new Runnable() {
            @Override
            public void run() {
                tablayout.setupWithViewPager(vp);
            }
        });
    }

    @OnClick({R.id.search, R.id.iv_msg})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:

                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_msg:

                if (managerId == -1) {
                    Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent1 = new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent1);
                }
                break;
        }
    }







    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {

    }

    @Override
    public void onGetColunmDataNext(ColumnBean columnBean) {
        if(columnBean.result_code != null && columnBean.result_code.equals("200")){
            result = columnBean.result;
            for (int i = 0;i<result.size();i++){

                mTabTitles.add(result.get(i).name);
            }

            if(result.size()>4){
                tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }else{
                tablayout.setTabMode(TabLayout.MODE_FIXED);
                tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }

            setData();
        }else {

            Toast.makeText(getActivity(), columnBean.reason, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetDataNext(HomeBean homeBean) {

    }
}
