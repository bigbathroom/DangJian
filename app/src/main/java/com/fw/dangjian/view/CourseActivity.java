package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.CourseAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.CourseBean;
import com.fw.dangjian.mvpView.CourseMvpView;
import com.fw.dangjian.presenter.CoursePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseActivity extends BaseActivity implements CourseMvpView{
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

    CoursePresenter coursePresenter;
    int page = 1;

    private CourseAdapter mAdapter;
    private ArrayList<String> lists;
    private int refreshTime = 0;
    private MyHandler handler;
    @Override
    protected int fillView() {
        return R.layout.activity_course;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("学习课程");
        coursePresenter = new CoursePresenter();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

        mAdapter = new CourseAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);
        initAdapterClike();
    }


    private void initAdapterClike() {
        mAdapter.setonItemClickLitener(new CourseAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(CourseActivity.this, VideoActivity.class);
//                intent.putExtra("studyId", lists.get(position - 1).id);
                intent.putExtra("studyId", 1);
                startActivity(intent);
            }
        });

    }

    private void requestServer(int page) {
        coursePresenter.getCoursePage(page,this);
    }


    @Override
    public void onGetDataNext(CourseBean courseBean) {

        /*if (actionBean.result_code != null && actionBean.result_code.equals("200")){
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
                handler.sendEmptyMessageDelayed(1, 500);
            }else{
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
        }else{
            handler.sendEmptyMessageDelayed(3, 500);
        }*/


    }

    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
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
