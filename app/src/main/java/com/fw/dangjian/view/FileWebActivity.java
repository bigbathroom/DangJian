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
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FileWebActivity extends BaseActivity {
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tablayout4)
    TabLayout tablayout4;
    @BindView(R.id.vp4)
    ViewPager vp4;


    private List<Fragment> mFragments;
    private List<String> mTabTitles;

    private int managerId;
    private Intent intent;
    private int studyId;

    @Override
    protected int fillView() {
        return R.layout.activity_file_web;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("文件详情");
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        intent = getIntent();
        if (intent != null) {
            studyId = intent.getIntExtra("studyId", -1);
        }
    }


    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.add("学习文件");
        mTabTitles.add("笔记评论");

        FileWebFragment fileWebFragment = FileWebFragment.newInstance(studyId);
        NoteCommentFragment noteCommentFragment = NoteCommentFragment.newInstance(studyId);

        mFragments.add(fileWebFragment);
        mFragments.add(noteCommentFragment);

        vp4.setOffscreenPageLimit(1);
        vp4.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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


        tablayout4.post(new Runnable() {
            @Override
            public void run() {
                tablayout4.setupWithViewPager(vp4);
            }
        });

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
