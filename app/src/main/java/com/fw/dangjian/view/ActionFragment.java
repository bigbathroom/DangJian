package com.fw.dangjian.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActionFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tablayout3)
    TabLayout tablayout3;
    @BindView(R.id.vp3)
    ViewPager vp3;

    private List<Fragment> mFragments;
    private List<String> mTabTitles;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_action, null);
    }


    @Override
    protected void initUi() {
        tv_title.setText("互动广场");
        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.add("题库");
        mTabTitles.add("测评");

        QuestionBankFragment  questionBankFragment = new QuestionBankFragment();
        TestQuestionFragment testQuestionFragment = new TestQuestionFragment();
        mFragments.add(questionBankFragment);
        mFragments.add(testQuestionFragment);

        vp3.setOffscreenPageLimit(1);
        vp3.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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

}
