package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.ActionAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.ActionBean;
import com.fw.dangjian.mvpView.ActionMvpView;
import com.fw.dangjian.presenter.ActionPresenter;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class ActionFragment extends BaseFragment implements ActionMvpView{

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private ImageView iv;
    private ActionAdapter mAdapter;
    private ArrayList<ActionBean.ResultBean.ListBean> lists;
    private ActionPresenter actionPresenter;

    int page = 1;
    private MyHandler handler;
    private int refreshTime = 0;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_action, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("互动广场");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.action_headview, (ViewGroup)getActivity().findViewById(android.R.id.content),false);
        iv = (ImageView) headView.findViewById(R.id.view_pager);
        nrecycler.addHeaderView(headView);

        handler = new MyHandler();
        actionPresenter = new ActionPresenter();
    }

    @Override
    protected void initData() {

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

        mAdapter = new ActionAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }

    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new ActionAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("squareId",lists.get(position-2).id);

                startActivity(intent);
            }
        });

    }

    private void requestServer(int page) {
        actionPresenter.getActionPage(page,this);
    }

    @Override
    public void onGetDataNext(ActionBean actionBean) {
        if (actionBean.result_code != null && actionBean.result_code.equals("200")){
            if(actionBean.result.list.size()>0){
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(actionBean.result.list);
                        break;
                    default:
                        lists.addAll(actionBean.result.list);
                        break;
                }
                handler.sendEmptyMessageDelayed(2, 200);
            }else{
                switch (page) {
                    case 1:

                        lists.clear();
                        handler.sendEmptyMessageDelayed(4, 1000);
                        break;
                    default:
                        ToastUtils.showShort(getActivity(), "没有更多数据", false);
                        handler.sendEmptyMessageDelayed(1, 1000);

                        break;
                }
            }
        }else{
            handler.sendEmptyMessageDelayed(3, 1000);
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

                    mAdapter.notifyDataSetChanged();
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
                case 2:
                    nrecycler.setVisibility(View.VISIBLE);
                    linearLayout_no_net.setVisibility(View.GONE);

                    mAdapter.notifyDataSetChanged();
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
                case 3:
                    nrecycler.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.VISIBLE);
                  /*  bt_back.setText("重新加载");
                    bt_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           *//* MainActivity mainActivity = (MainActivity) act;
                            MarketFragment fragmet = new MarketFragment();
                            mainActivity.changeRadioButton(R.id.rd_home, fragmet,0);*//*
                            page = 0;
                            requestServer();
                        }
                    });*/
                    break;
                case 4:
                    nrecycler.setVisibility(View.VISIBLE);
                    linearLayout_no_net.setVisibility(View.GONE);
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    mAdapter.notifyDataSetChanged();
                    break;
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
