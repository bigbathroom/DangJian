package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.DataBookAdapter1;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.MeetBean;
import com.fw.dangjian.mvpView.BookMvpView;
import com.fw.dangjian.presenter.BookPresenter;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ManageFragment extends BaseFragment implements BookMvpView{
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


    DataBookAdapter1 adapter;
    private BookPresenter bookPresenter;
//    private List<BookBean.ResultBean.PageInfoBean.ListBean> lists;
    int page = 1;
    private int refreshTime = 0;
    private List<MeetBean.ResultBean.PageInfoBean.ListBean> lists;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.activity_data_book1, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("会议");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        bookPresenter = new BookPresenter();
    }

    @Override
    protected void initData() {

        lists = new ArrayList<>();
        requestServer(page);

        nrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page = 1;
                        requestServer(page);
                    }

                }, 200);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page++;
                        requestServer(page);
                    }
                }, 200);

            }
        });

        adapter = new DataBookAdapter1(lists, getActivity());
        nrecycler.setAdapter(adapter);

        initAdapterClike();
    }

    private void requestServer(int page) {

//        bookPresenter.getBookPage(page, this);
        bookPresenter.getMeetingPage(page, this);
    }

    private void initAdapterClike() {

        adapter.setonItemClickLitener(new DataBookAdapter1.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("id", lists.get(position-1).id);
                intent.putExtra("flag_id", 200);
                intent.putExtra("title", "会议详情");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onGetDataNext(MeetBean djeBean) {

        if (djeBean.result_code != null && djeBean.result_code.equals("200")) {
            if (djeBean.result.pageInfo.list.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(djeBean.result.pageInfo.list);
                        break;
                    default:
                        lists.addAll(djeBean.result.pageInfo.list);
                        break;
                }

                linearLayout_no_content.setVisibility(View.GONE);
                linearLayout_no_net.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);
                nrecycler.loadMoreComplete();
                nrecycler.refreshComplete();
                adapter.notifyDataSetChanged();

            } else {
                switch (page) {
                    case 1:
                        linearLayout_no_content.setVisibility(View.VISIBLE);
                        linearLayout_no_net.setVisibility(View.GONE);
                        nrecycler.setVisibility(View.GONE);
                        nrecycler.loadMoreComplete();
                        nrecycler.refreshComplete();
                        break;
                    default:
                        ToastUtils.showShort(act, "没有更多数据");
                        page--;
                        linearLayout_no_content.setVisibility(View.GONE);
                        linearLayout_no_net.setVisibility(View.GONE);
                        nrecycler.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                        nrecycler.loadMoreComplete();
                        nrecycler.refreshComplete();
                        break;
                }
            }
        } else {
            switch (page) {
                case 1:
                    linearLayout_no_content.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.VISIBLE);
                    nrecycler.setVisibility(View.GONE);
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
                default:
                    ToastUtils.showShort(act, "网络错误");
                    page--;
                    linearLayout_no_content.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.GONE);
                    nrecycler.setVisibility(View.VISIBLE);
                    nrecycler.loadMoreComplete();
                    nrecycler.refreshComplete();
                    break;
            }
        }
    }

    @Override
    public void onGetDataError(Throwable e) {
        super.onGetDataError(e);
        nrecycler.loadMoreComplete();
        nrecycler.refreshComplete();
    }
}