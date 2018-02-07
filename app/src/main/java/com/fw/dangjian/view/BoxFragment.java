package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.BoxAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.BoxBean;
import com.fw.dangjian.bean.BoxPageBean;
import com.fw.dangjian.mvpView.BoxMvpView;
import com.fw.dangjian.presenter.BoxPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.HandlerUtil;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class BoxFragment extends  BaseFragment implements BoxMvpView{

    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private BoxAdapter mAdapter;
    private ArrayList<BoxPageBean.ResultBean.PageInfoBean.ListBean> lists;

    int page = 1;
    private HandlerUtil handler;
    private int refreshTime = 0;
    private String mTitleCode = "";
   int columnid;
    private BoxPresenter boxPresenter;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_box, null);
    }

    @Override
    protected void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        columnid = Integer.valueOf(mTitleCode).intValue();

        boxPresenter = new BoxPresenter();

    }

    public static BoxFragment newInstance(String code) {
        BoxFragment fragment = new BoxFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
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

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page++;
                        requestServer(page);
                    }
                }, 1000);

            }
        });


        mAdapter = new BoxAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        handler = new HandlerUtil(nrecycler);
        mAdapter.setonItemClickLitener(new BoxAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), WorkInfoActivity.class);
                intent.putExtra("news_id",lists.get(position-1).id);
                startActivity(intent);
            }
        });
    }

    private void requestServer(int page) {
        boxPresenter.getBoxPage(columnid,page,this);
    }



    @Override
    public void onGetDataNext(BoxPageBean boxPageBean) {
        if (boxPageBean.result_code != null && boxPageBean.result_code.equals("200")) {
            if (boxPageBean.result.pageInfo.list.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(boxPageBean.result.pageInfo.list);
                        break;
                    default:
                        lists.addAll(boxPageBean.result.pageInfo.list);
                        break;
                }

                linearLayout_no_content.setVisibility(View.GONE);
                linearLayout_no_net.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(2, 200);

                mAdapter.notifyDataSetChanged();

            } else {
                switch (page) {
                    case 1:
                        linearLayout_no_content.setVisibility(View.VISIBLE);
                        linearLayout_no_net.setVisibility(View.GONE);
                        nrecycler.setVisibility(View.GONE);
                        break;
                    default:
                        ToastUtils.showShort(act, "没有数据");
                        page--;
                        linearLayout_no_content.setVisibility(View.GONE);
                        linearLayout_no_net.setVisibility(View.GONE);
                        nrecycler.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetChanged();
                        handler.sendEmptyMessageDelayed(1, 1000);
                        break;
                }
            }
        } else {
            switch (page) {
                case 1:
                    linearLayout_no_content.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.VISIBLE);
                    nrecycler.setVisibility(View.GONE);

                    break;
                default:
                    page--;
                    linearLayout_no_content.setVisibility(View.GONE);
                    linearLayout_no_net.setVisibility(View.GONE);
                    nrecycler.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(3, 1000);
                    break;
            }
        }

    }

    @Override
    public void onGetColunmDataNext(BoxBean boxBean) {
    }

}
