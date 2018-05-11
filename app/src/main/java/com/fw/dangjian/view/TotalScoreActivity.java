package com.fw.dangjian.view;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.TotalScoreAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.TotalScoreBean;
import com.fw.dangjian.mvpView.TotalScoreMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class TotalScoreActivity extends BaseActivity implements TotalScoreMvpView{

    @BindView(R.id.left)
    RelativeLayout left;
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

    private TotalScoreAdapter mAdapter;
    private ArrayList<TotalScoreBean.ResultBean> lists;

    int page = 1;
    private int refreshTime = 0;
    private UserPresenter userPresenter;

    int managerId;

    @Override
    protected int fillView() {
        return R.layout.activity_total_score;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("我的成绩");

        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        userPresenter = new UserPresenter();
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
//                        page++;
//                        requestServer(page);
                        nrecycler.loadMoreComplete();
                    }
                }, 200);

            }
        });


        mAdapter = new TotalScoreAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);
        mAdapter.setonItemClickLitener(new TotalScoreAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    private void requestServer(int page) {
        userPresenter.getTotalScore(managerId,this);
//        userPresenter.getTotalScore(managerId,page,this);
    }

    @Override
    public void onGetDataError(Throwable e) {
        super.onGetDataError(e);
        nrecycler.loadMoreComplete();
        nrecycler.refreshComplete();
    }
    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
        }
    }

    @Override
    public void onGetDataNext(TotalScoreBean scoreBean) {
        if (scoreBean.result_code != null && scoreBean.result_code.equals("200")) {
            if (scoreBean.result.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(scoreBean.result);
                        break;
                    default:
                        lists.addAll(scoreBean.result);
                        break;
                }

                linearLayout_no_content.setVisibility(View.GONE);
                linearLayout_no_net.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);
                nrecycler.loadMoreComplete();
                nrecycler.refreshComplete();
                mAdapter.notifyDataSetChanged();

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
                        mAdapter.notifyDataSetChanged();
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
}
