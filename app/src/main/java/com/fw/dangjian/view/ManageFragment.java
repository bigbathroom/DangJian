package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.DataBookAdapter;
import com.fw.dangjian.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ManageFragment extends BaseFragment {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;
    private DataBookAdapter mAdapter;
    private ArrayList<String> lists;
    ImageView iv;
    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.activity_data_book, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("大事记");
        lists = new ArrayList<>();

        nrecycler.setLayoutManager(new GridLayoutManager(getActivity(),1));

        mAdapter = new DataBookAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);
        mAdapter.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.book_head,null));

    }

    @Override
    protected void initData() {


        mAdapter.setonItemClickLitener(new DataBookAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WorkInfoActivity.class);
                startActivity(intent);
            }
        });
    }




}