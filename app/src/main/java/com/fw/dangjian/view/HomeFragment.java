package com.fw.dangjian.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.ChannelPagerAdapter;
import com.fw.dangjian.bean.Channel;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.colortrackview.ColorTrackTabLayout;
import com.fw.dangjian.listener.OnChannelListener;
import com.fw.dangjian.mvpView.HomeMvpView;
import com.fw.dangjian.presenter.HomePresenter;
import com.fw.dangjian.util.CommonUtil;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SharedPreferencesMgr;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fw.dangjian.util.ConstanceValue.TITLE_SELECTED;


public class HomeFragment extends Fragment implements OnChannelListener,HomeMvpView {

    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    @BindView(R.id.tablayout)
    ColorTrackTabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;

    public List<Channel> mSelectedDatas = new ArrayList<>();
    public List<Channel> mUnSelectedDatas = new ArrayList<>();
    private List<Fragment> mFragments;
    private Gson mGson = new Gson();
    private ChannelPagerAdapter mTitlePagerAdapter;
    private HomePresenter homePresenter;

    private List<ColumnBean.ResultBean> result;

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
//        iv_add.setVisibility(View.VISIBLE);
        homePresenter = new HomePresenter();
        homePresenter.getColunm(this);
    }

    protected void processLogic() {

//        getTitleData();

        mFragments = new ArrayList<>();
        for (int i = 0; i < mSelectedDatas.size(); i++) {
            NewsPageFragment newsFragment = NewsPageFragment.newInstance(mSelectedDatas.get(i).TitleCode,mSelectedDatas.get(i).Title);
            mFragments.add(newsFragment);
        }

        mTitlePagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), mFragments, mSelectedDatas);

        vp.setAdapter(mTitlePagerAdapter);
        vp.setOffscreenPageLimit(mSelectedDatas.size());

        tablayout.setTabPaddingLeftAndRight(CommonUtil.dip2px(10), CommonUtil.dip2px(10));
        tablayout.setupWithViewPager(vp);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) tablayout.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());
            }
        });
        //隐藏指示器
        tablayout.setSelectedTabIndicatorHeight(0);
    }


    /**
     * 获取标题数据
     */
    private void getTitleData() {

      /*  String selectTitle = SharedPreferencesMgr.getString(TITLE_SELECTED, "");
        String unselectTitle = SharedPreferencesMgr.getString(TITLE_UNSELECTED, "");

        if (TextUtils.isEmpty(selectTitle) || TextUtils.isEmpty(unselectTitle)) {
            //本地没有title
            String[] titleStr = getResources().getStringArray(R.array.home_title);
            String[] titlesCode = getResources().getStringArray(R.array.home_title_code);
            //默认添加了全部频道
            for (int i = 0; i < titlesCode.length; i++) {
                String t = titleStr[i];
                String code = titlesCode[i];
                mSelectedDatas.add(new Channel(t, code));
            }

            String selectedStr = mGson.toJson(mSelectedDatas);
            SharedPreferencesMgr.setString(TITLE_SELECTED, selectedStr);

        } else {
            //之前添加过
            List<Channel> selecteData = mGson.fromJson(selectTitle, new TypeToken<List<Channel>>() {}.getType());
            List<Channel> unselecteData = mGson.fromJson(unselectTitle, new TypeToken<List<Channel>>() {}.getType());

            mSelectedDatas.addAll(selecteData);
            mUnSelectedDatas.addAll(unselecteData);
        }*/

    }


    @OnClick({ R.id.iv_add,R.id.search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:

                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.setOnChannelListener(this);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");

                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //保存选中和未选中的channel
                        SharedPreferencesMgr.setString(TITLE_SELECTED, mGson.toJson(mSelectedDatas));
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_UNSELECTED, mGson.toJson(mUnSelectedDatas));

                        mTitlePagerAdapter.notifyDataSetChanged();
//                        mTitlePagerAdapter =  new ChannelPagerAdapter(getChildFragmentManager(), mFragments, mSelectedDatas);
//                        vp.setAdapter(mTitlePagerAdapter);
                        vp.setOffscreenPageLimit(mSelectedDatas.size());
                        tablayout.setCurrentItem(tablayout.getSelectedTabPosition());

                        ViewGroup slidingTabStrip = (ViewGroup) tablayout.getChildAt(0);
                        //注意：因为最开始设置了最小宽度，所以重新测量宽度的时候一定要先将最小宽度设置为0
                        slidingTabStrip.setMinimumWidth(0);
                        slidingTabStrip.measure(0, 0);
                        slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth());

                    }
                });

                break;
            case R.id.search:

                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onItemMove(int starPos, int endPos) {
        listMove(mSelectedDatas, starPos, endPos);
        listMove(mFragments, starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
//移动到我的频道
        Channel channel = mUnSelectedDatas.remove(starPos);
        mSelectedDatas.add(endPos, channel);
        mFragments.add(NewsPageFragment.newInstance(channel.TitleCode,channel.Title));
        mTitlePagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        mUnSelectedDatas.add(endPos, mSelectedDatas.remove(starPos));
        mFragments.remove(starPos);
        mTitlePagerAdapter.notifyDataSetChanged();
    }
    
    private void listMove(List datas, int starPos, int endPos) {
        Object o = datas.get(starPos);
        //先删除之前的位置
        datas.remove(starPos);
        //添加到现在的位置
        datas.add(endPos, o);
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
                String t = result.get(i).name;
                String code = String.valueOf(result.get(i).id);
                mSelectedDatas.add(new Channel(t, code));
            }

            String selectedStr = mGson.toJson(mSelectedDatas);
            SharedPreferencesMgr.setString(TITLE_SELECTED, selectedStr);

            processLogic();
        }else {

            Toast.makeText(getActivity(), columnBean.reason, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetDataNext(HomeBean homeBean) {

    }
}
