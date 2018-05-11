package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.ActionAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.ActionBean;
import com.fw.dangjian.mvpView.ActionMvpView;
import com.fw.dangjian.presenter.ActionPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class TestQuestionFragment extends BaseFragment implements ActionMvpView {
    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private ActionAdapter mAdapter;
    private ArrayList<ActionBean.ResultBean.ListBean> lists;
    private ActionPresenter actionPresenter;

    int page = 1;
    private MyHandler handler;
    private int refreshTime = 0;

    int managerId;
    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_test_question, null);
    }


    @Override
    protected void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        handler = new MyHandler();
        actionPresenter = new ActionPresenter();
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onResume() {
        super.onResume();
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

        setData();
    }

    public void setData() {

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

        mAdapter = new ActionAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }
    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new ActionAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (managerId == -1){
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1000);
                }else{
                    if (lists.get(position - 1).state != null && lists.get(position - 1).state.equals("1")) {
                        Intent intent = new Intent(getActivity(), ScoreActivity.class);
                        intent.putExtra("testId", lists.get(position - 1).id);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), QuizActivity.class);
                        intent.putExtra("squareId", lists.get(position - 1).id);
                        intent.putExtra("title", lists.get(position - 1).square_name);
                        startActivity(intent);
                    }
                }


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);
        }
    }

    private void requestServer(int page) {
        actionPresenter.getActionPage(managerId, page, this);
    }

    @Override
    public void onGetDataNext(ActionBean actionBean) {
        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {
            if (actionBean.result.list.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(actionBean.result.list);
                        break;
                    default:
                        lists.addAll(actionBean.result.list);
                        break;
                }
                handler.sendEmptyMessageDelayed(1, 200);
            } else {
                switch (page) {
                    case 1:
                        lists.clear();
                        handler.sendEmptyMessageDelayed(2, 200);
                        break;
                    default:
                        ToastUtils.showShort(getActivity(), "没有更多数据", false);
                        page--;
                        handler.sendEmptyMessageDelayed(1, 200);
                        break;
                }
            }
        } else {
            handler.sendEmptyMessageDelayed(3, 200);
        }
    }

    @Override
    public void onGetDataError(Throwable e) {
        super.onGetDataError(e);
        nrecycler.loadMoreComplete();
        nrecycler.refreshComplete();
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

