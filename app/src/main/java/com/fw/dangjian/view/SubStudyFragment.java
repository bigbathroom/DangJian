package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.StudyAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.bean.StudyPageBean;
import com.fw.dangjian.mvpView.StudyMvpView;
import com.fw.dangjian.presenter.StudyPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SubStudyFragment extends  BaseFragment implements StudyMvpView{
    @BindView(R.id.recyclerview)
    XRecyclerView nrecycler;
    @BindView(R.id.no_content)
    LinearLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.no_net)
    LinearLayout linearLayout_no_net;

    private StudyAdapter mAdapter;

    private String mTitleCode = "";
    private StudyPresenter  studyPresenter;
    private String title = "";
    int page = 1;
    private int refreshTime = 0;
    private int columnid;
    private List<StudyPageBean.ResultBean.PageInfoBean.ListBean> lists;


    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_sub_study, null);
    }

    public static SubStudyFragment newInstance(String code,String title) {
        SubStudyFragment fragment = new SubStudyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        columnid = Integer.valueOf(mTitleCode).intValue();
        title = getArguments().getString("title");

        studyPresenter = new StudyPresenter();
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


        mAdapter = new StudyAdapter(lists, getActivity(),columnid);
        nrecycler.setAdapter(mAdapter);

        mAdapter.setonItemClickLitener(new StudyAdapter.onItemClickLitener() {


            @Override
            public void onItemClick(View view, int position) {
              /*  Intent intent = new Intent(getActivity(),VideoActivity.class);
                 intent.putExtra("studyId", lists.get(position - 1).id);
                startActivity(intent);
*/
              if(columnid == 12){
                  Intent intent = new Intent(getActivity(),FileWebActivity.class);
                  intent.putExtra("studyId", lists.get(position - 1).id);
                  startActivity(intent);

              }else{
                  Intent intent = new Intent(getActivity(),WorkInfoActivity.class);
                  intent.putExtra("news_id",lists.get(position-1).id);
                  intent.putExtra("title",title);
                  intent.putExtra("type","study");
                  startActivity(intent);
              }
            }
        });
    }

    private void requestServer(int page) {

        studyPresenter.getStudyPage(columnid,page,this);
    }


    @Override
    public void onGetColunmDataNext(StudyBean studyBean) {

    }

    @Override
    public void onGetDataNext(StudyPageBean studyPageBean) {
        if (studyPageBean.result_code != null && studyPageBean.result_code.equals("200")) {
            if (studyPageBean.result.pageInfo.list.size() > 0) {
                switch (page) {
                    case 1:
                        lists.clear();
                        lists.addAll(studyPageBean.result.pageInfo.list);
                        break;
                    default:
                        lists.addAll(studyPageBean.result.pageInfo.list);
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
                        mAdapter.notifyDataSetChanged();
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
