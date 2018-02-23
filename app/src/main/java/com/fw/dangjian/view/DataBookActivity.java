package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.DataBookAdapter;
import com.fw.dangjian.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DataBookActivity extends BaseActivity {

    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;
    private DataBookAdapter mAdapter;
    private ArrayList<String> lists;
    @Override
    protected int fillView() {
        return R.layout.activity_data_book;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("大事记");
        lists = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        mAdapter = new DataBookAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);

        mAdapter.setonItemClickLitener(new DataBookAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DataBookActivity.this, WorkInfoActivity.class);
                startActivity(intent);
            }
        });
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
