package com.fw.dangjian.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.bean.StudyPageBean;
import com.fw.dangjian.mvpView.StudyMvpView;
import com.fw.dangjian.presenter.StudyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StudyFragment extends BaseFragment implements StudyMvpView{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tablayout1)
    TabLayout tablayout1;
    @BindView(R.id.vp1)
    ViewPager vp1;

    private List<String> mTabTitles;

    private List<Fragment> fragments1;
    private StudyPresenter studyPresenter;
    private List<StudyBean.ResultBean> result;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_study, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("学习视频");
        studyPresenter = new StudyPresenter();
        studyPresenter.getColunm(this);

        mTabTitles = new ArrayList<>();
        fragments1 = new ArrayList<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onGetColunmDataNext(StudyBean studyBean) {
        if(studyBean.result_code != null && studyBean.result_code.equals("200")){
            result = studyBean.result;
            for (int i = 0;i<result.size();i++){
                SubStudyFragment subStudyFragment = SubStudyFragment.newInstance(result.get(i).id);
                fragments1.add(subStudyFragment);
                mTabTitles.add(result.get(i).name);
            }

            setData();
        }else {

            Toast.makeText(getActivity(), studyBean.result_msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetDataNext(StudyPageBean studyPageBean) {

    }

    private void setData() {
        vp1.setOffscreenPageLimit(1);
        vp1.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments1.get(position);
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


        tablayout1.post(new Runnable() {
            @Override
            public void run() {
                tablayout1.setupWithViewPager(vp1);
            }
        });
    }
}