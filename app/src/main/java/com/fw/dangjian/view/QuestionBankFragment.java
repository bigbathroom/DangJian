package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.QuestionAdapter;
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

public class QuestionBankFragment extends BaseFragment implements ActionMvpView {

    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private QuestionAdapter mAdapter;
    private ArrayList<ActionBean.ResultBean.ListBean> lists;
    private ActionPresenter actionPresenter;

    int page = 1;
    private MyHandler handler;
    private int refreshTime = 0;

    int managerId;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_question_bank, null);
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
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);
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

        mAdapter = new QuestionAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }

    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new QuestionAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                intent.putExtra("squareId", lists.get(position - 1).id);
                intent.putExtra("title", lists.get(position - 1).square_name);
                startActivity(intent);
            }
        });
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
