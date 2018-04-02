package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.NewSearchAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.mvpView.HomeMvpView;
import com.fw.dangjian.presenter.HomePresenter;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements HomeMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private NewSearchAdapter mAdapter;
    private ArrayList<HomeBean.ResultBean.PageInfoBean.ListBean> lists;
    private HomePresenter homePresenter;

    private int refreshTime = 0;
    int page;
    private String search_content;

    @Override
    protected int fillView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        homePresenter = new HomePresenter();
    }

    @Override
    protected void initData() {
        lists = new ArrayList<>();

        nrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page = 1;
                        requestServer(page, search_content);
                    }

                }, 200);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page++;
                        requestServer(page, search_content);
                    }
                }, 200);

            }
        });

    }



    private void requestServer(int page, String name) {
        homePresenter.getHomePage1(page, name, this);

        mAdapter = new NewSearchAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }

    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new NewSearchAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, WorkInfoActivity.class);
                intent.putExtra("news_id",lists.get(position-1).id);
                intent.putExtra("title","");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.left, R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;

            case R.id.rl_search:
                search_content = et_search.getText().toString();
                if (TextUtils.isEmpty(search_content)) {
                    ToastUtils.show(this, "请输入查询内容", Toast.LENGTH_SHORT);
                    return;
                }
                page = 1;
                requestServer(page, search_content);

                break;
        }
    }

    @Override
    public void onGetColunmDataNext(ColumnBean columnBean) {

    }

    @Override
    public void onGetDataNext(HomeBean homeBean) {

        if (homeBean.result_code != null && homeBean.result_code.equals("200")) {
            if (homeBean.result != null && homeBean.result.pageInfo.list.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(homeBean.result.pageInfo.list);
                        break;
                    default:
                        lists.addAll(homeBean.result.pageInfo.list);
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
