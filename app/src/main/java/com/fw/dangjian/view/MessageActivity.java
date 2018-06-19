package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.MessageAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.MessageBean;
import com.fw.dangjian.mvpView.MessageMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity implements MessageMvpView {
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

    private MessageAdapter mAdapter;
    private UserPresenter userPresenter;
    int page = 1;
    private int managerId;
    private List<MessageBean.ResultBean.PageInfoBean.ListBean> lists;

    @Override
    protected int fillView() {
        return R.layout.activity_message;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("消息通知");

        userPresenter = new UserPresenter();
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);



    }

    private void requestServer() {
        userPresenter.getMessage(managerId, this);
    }


    @Override
    protected void initData() {

        lists = new ArrayList<>();
        requestServer();

        nrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page = 1;
                        requestServer();
                    }

                }, 200);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        nrecycler.loadMoreComplete();
                    }
                }, 200);

            }
        });


        mAdapter = new MessageAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();

    }



    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new MessageAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (lists.get(position-1).post_column == 3) {
                    Intent intent = new Intent(MessageActivity.this, WorkInfoActivity.class);
                    intent.putExtra("news_id", lists.get(position - 1).id);
                    intent.putExtra("title", "首页——活动新闻");
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 4) {
                    Intent intent = new Intent(MessageActivity.this, WorkInfoActivity.class);
                    intent.putExtra("news_id", lists.get(position - 1).id);
                    intent.putExtra("title", "首页——党风廉洁");
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 5) {
                    Intent intent = new Intent(MessageActivity.this, WorkInfoActivity.class);
                    intent.putExtra("news_id", lists.get(position - 1).id);
                    intent.putExtra("title", "首页——两学一做");
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 10) {
                    Intent intent = new Intent(MessageActivity.this,WorkInfoActivity.class);
                    intent.putExtra("news_id",lists.get(position-1).id);
                    intent.putExtra("title","学习——十九大");
                    intent.putExtra("type","study");
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 11) {

                    Intent intent = new Intent(MessageActivity.this,WorkInfoActivity.class);
                    intent.putExtra("news_id",lists.get(position-1).id);
                    intent.putExtra("title","学习——两学一做");
                    intent.putExtra("type","study");
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 12) {
                    Intent intent = new Intent(MessageActivity.this,FileWebActivity.class);
                    intent.putExtra("studyId", lists.get(position - 1).id);
                    startActivity(intent);
                } else if (lists.get(position-1).post_column == 13) {
                    Intent intent = new Intent(MessageActivity.this, WorkInfoActivity.class);
                    intent.putExtra("news_id", lists.get(position - 1).id);
                    intent.putExtra("title", "首页——要闻");
                    startActivity(intent);
                }

            }
        });

    }




    @Override
    public void onGetDataNext(MessageBean djeBean) {

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

}
