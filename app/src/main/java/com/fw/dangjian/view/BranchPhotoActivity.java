package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.BranchAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.WaterBean;
import com.fw.dangjian.mvpView.BranchMvpView;
import com.fw.dangjian.presenter.BranchPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BranchPhotoActivity extends BaseActivity implements BranchMvpView{
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



    BranchAdapter  mAdapter;
    private BranchPresenter branchPresenter;

    int page = 1;
    private int refreshTime = 0;
    private int managerId;
    private List<WaterBean.ResultBean> lists;
    private ArrayList<String> imageLists;

    @Override
    protected int fillView() {
        return R.layout.activity_branch_photo;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("党建e连心");

        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        branchPresenter = new BranchPresenter();

    }

    @Override
    protected void initData() {
        lists = new ArrayList<>();

        requestServer(page,managerId);

        nrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page = 1;
                        requestServer(page,managerId);
                    }

                }, 200);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        page++;
                        requestServer(page,managerId);
                    }
                }, 200);

            }
        });


        mAdapter = new BranchAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);


        mAdapter.setonItemClickLitener(new BranchAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                imageLists = new ArrayList<>();
                imageLists.clear();

                for (int i = 0;i<lists.get(position-1).imgAarray.size();i++){
                    imageLists.add(lists.get(position-1).imgAarray.get(i).url);
                }

                Intent intent = new Intent(BranchPhotoActivity.this, WaterFallActivity.class);
                intent.putExtra("title", lists.get(position-1).activityName);
                intent.putStringArrayListExtra("photos", imageLists);
                startActivity(intent);
            }
        });
    }


    private void requestServer(int page, int managerId) {
        branchPresenter.getWaterPage(page,managerId,this);
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
    public void onGetDataNext(WaterBean waterBean) {
        if (waterBean.result_code != null && waterBean.result_code.equals("200")) {
            if (waterBean.result.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(waterBean.result);
                        break;
                    default:
                        lists.addAll(waterBean.result);
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
}
