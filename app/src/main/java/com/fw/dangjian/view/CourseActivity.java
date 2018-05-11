package com.fw.dangjian.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseActivity extends BaseActivity{
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tablayout4)
    TabLayout tablayout3;
    @BindView(R.id.vp4)
    ViewPager vp3;

    private List<Fragment> mFragments;
    private List<String> mTabTitles;

    @Override
    protected int fillView() {
        return R.layout.activity_course;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("我的笔记");

        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.add("学习笔记");
        mTabTitles.add("会议笔记");

        LearnNoteFragment  learnNoteFragment = new LearnNoteFragment();
        MeetingNoteFragment meetingNoteFragment = new MeetingNoteFragment();
        mFragments.add(learnNoteFragment);
        mFragments.add(meetingNoteFragment);

        vp3.setOffscreenPageLimit(1);
        vp3.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
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


        tablayout3.post(new Runnable() {
            @Override
            public void run() {
                tablayout3.setupWithViewPager(vp3);
            }
        });

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
}
