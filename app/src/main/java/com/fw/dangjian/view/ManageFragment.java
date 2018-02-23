package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.activity_data_book, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("大事记");
        lists = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        mAdapter = new DataBookAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);

        mAdapter.setonItemClickLitener(new DataBookAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WorkInfoActivity.class);
                startActivity(intent);
            }
        });
    }




}