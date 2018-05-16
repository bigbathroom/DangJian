package com.fw.dangjian.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseActivity extends BaseActivity{
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tablayout4)
    TabLayout tablayout3;
    @BindView(R.id.vp4)
    ViewPager vp3;

    private List<Fragment> mFragments;
    private List<String> mTabTitles;

    int position = 0;
    private LearnNoteFragment learnNoteFragment;
    private MeetingNoteFragment meetingNoteFragment;

    @Override
    protected int fillView() {
        return R.layout.activity_course;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("选择");
        tv_title.setText("我的笔记");

        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.add("学习笔记");
        mTabTitles.add("会议笔记");

        learnNoteFragment = new LearnNoteFragment();
        meetingNoteFragment = new MeetingNoteFragment();
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

        vp3.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setPosition(int position) {
        this.position = position;
    }


    @Override
    protected void initData() {

    }
    @OnClick({R.id.left,R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(this, PrintNoteActivity.class);

                if (position == 0) {

                    intent.putExtra("BeanList", (Serializable) learnNoteFragment.getLearnNoteBeanList());
                    intent.putExtra("from","learn");
                    startActivity(intent);
                } else {
                    intent.putExtra("BeanList", (Serializable) meetingNoteFragment.getMeetingNoteBeanList());
                    intent.putExtra("from","meeting");
                    startActivity(intent);
                }

                break;
        }
    }
}
