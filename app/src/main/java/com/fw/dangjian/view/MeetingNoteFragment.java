package com.fw.dangjian.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.CourseAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.AllNoteBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.dialog.BookDialog;
import com.fw.dangjian.mvpView.MeetingNoteMvpView;
import com.fw.dangjian.presenter.UserPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MeetingNoteFragment extends BaseFragment implements MeetingNoteMvpView{

    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    UserPresenter userPresenter;
    int page = 1;

    private CourseAdapter mAdapter;
    private int refreshTime = 0;
    private MyHandler handler;
    private List<AllNoteBean.ResultBean> lists;
    int managerId;
    BookDialog bookDialog;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_meeting_note, null);
    }

    @Override
    protected void initUi() {
        userPresenter = new UserPresenter();
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        handler = new MyHandler();
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

        mAdapter = new CourseAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }

    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new CourseAdapter.onItemClickLitener() {

            private int id;

            @Override
            public void onItemClick(View view, int position) {

                id = lists.get(position - 1).id;
                bookDialog = new BookDialog(getActivity(), lists.get(position - 1).content);
                bookDialog.show();
                bookDialog.setOnCommitListener(new BookDialog.OnCommitListener() {
                    @Override
                    public void onCommit(EditText et, View v) {
                        String s = et.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            ToastUtils.show(getActivity(), "请先输入笔记", Toast.LENGTH_SHORT);
                        } else {
                            changeNote(s, id);
                        }
                    }
                });
            }
        });

    }


    private void changeNote(String s, int id) {
        userPresenter.changeMeetingNote(managerId, id, s, this);
    }

    private void requestServer(int page) {
        userPresenter.getMeetingNote(managerId, this);
    }


    @Override
    public void onGetDataNext(AllNoteBean noteBean) {
        if (noteBean.result_code != null && noteBean.result_code.equals("200")) {
            if (noteBean.result.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(noteBean.result);
                        break;
                    default:
                        lists.addAll(noteBean.result);
                        break;
                }
                handler.sendEmptyMessageDelayed(1, 500);
            } else {
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
        } else {
            handler.sendEmptyMessageDelayed(3, 500);
        }
    }


    @Override
    public void onNoteNext(KongBean kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            bookDialog.dismiss();
            requestServer(1);
            ToastUtils.show(getActivity(), "提交成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(getActivity(), "提交失败", Toast.LENGTH_SHORT);
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
