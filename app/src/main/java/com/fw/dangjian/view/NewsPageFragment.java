package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.NewsAdapter;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.mvpView.HomeMvpView;
import com.fw.dangjian.presenter.HomePresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.GlideImageLoader;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsPageFragment extends Fragment implements HomeMvpView{
    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private Banner banner;
    private ArrayList<String> images;
    private ArrayList<String> titles;

    private NewsAdapter mAdapter;
    private ArrayList<HomeBean.ResultBean.PageInfoBean.ListBean> lists;

    private String mTitleCode = "";
    private String title = "";
    private HomePresenter homePresenter;

    int page = 1;
    private MyHandler handler;
    private List<HomeBean.ResultBean.LinksEntityBean> linksEntity;

    private int refreshTime = 0;
    private int columnid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_page, container, false);
        ButterKnife.bind(this, view);
        initUi();
        initData();
        return view;
    }

    public static NewsPageFragment newInstance(String code,String title) {
        NewsPageFragment fragment = new NewsPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initUi() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.news_headview, (ViewGroup)getActivity().findViewById(android.R.id.content),false);
        banner = (Banner) headView.findViewById(R.id.view_pager);
        nrecycler.addHeaderView(headView);
        handler = new MyHandler();


        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        title = getArguments().getString("title");


        columnid = Integer.valueOf(mTitleCode).intValue();

        homePresenter = new HomePresenter();

    }

    private void initData() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
        lists = new ArrayList<>();

        requestServer(page);

        nrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        page = 1;
                        requestServer(page);
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        page++;
                        requestServer(page);
                    }
                }, 1000);

            }
        });

        mAdapter = new NewsAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);

        initAdapterClike();
    }

    private void requestServer(int page) {
        homePresenter.getHomePage(columnid,page,this);
    }

    private void initAdapterClike() {

        mAdapter.setonItemClickLitener(new NewsAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(),WorkInfoActivity.class);
                intent.putExtra("news_id",lists.get(position-2).id);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }

    private void setBanner(final List<HomeBean.ResultBean.LinksEntityBean> linksEntity) {
        images.clear();
        titles.clear();

        for (int i = 0;i<linksEntity.size();i++){
            images.add(linksEntity.get(i).link_image);
            titles.add(linksEntity.get(i).link_description);
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(),WorkInfoActivity.class);
                intent.putExtra("url",linksEntity.get(position).link_url);
                startActivity(intent);
            }
        });

    }



    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {

    }

    @Override
    public void onGetColunmDataNext(ColumnBean columnBean) {

    }

    @Override
    public void onGetDataNext(HomeBean homeBean) {
        if (homeBean.result_code != null && homeBean.result_code.equals("200")){
            linksEntity = homeBean.result.linksEntity;

            setBanner(linksEntity);

            if(homeBean.result.pageInfo.list.size()>0){
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(homeBean.result.pageInfo.list);
                        break;
                    default:
                        lists.addAll(homeBean.result.pageInfo.list);
                        break;
                }
                handler.sendEmptyMessageDelayed(1, 500);
            }else{
                switch (page) {
                    case 1:
                        lists.clear();
                        handler.sendEmptyMessageDelayed(2, 500);
                        break;
                    default:
                        ToastUtils.showShort(getActivity(), "没有更多数据", false);
                        page--;
                        handler.sendEmptyMessageDelayed(1, 500);
                        break;
                }
            }
        }else{


            handler.sendEmptyMessageDelayed(3, 500);
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    nrecycler.setVisibility(View.VISIBLE);
                    linearLayout_no_net.setVisibility(View.GONE);
                    linearLayout_no_content.setVisibility(View.GONE);
                    mAdapter.notifyDataSetChanged();
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
                case 2:
                    nrecycler.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.GONE);
                    linearLayout_no_content.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
                case 3:
                    nrecycler.setVisibility(View.GONE);
                    linearLayout_no_content.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
            }
        }
    }

}
