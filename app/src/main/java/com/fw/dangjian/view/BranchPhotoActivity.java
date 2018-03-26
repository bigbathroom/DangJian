package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.BranchAdapter;
import com.fw.dangjian.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BranchPhotoActivity extends BaseActivity {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;

    BranchAdapter  mAdapter;
    private ArrayList<String> lists;
    @Override
    protected int fillView() {
        return R.layout.activity_branch_photo;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("支部照片墙");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {

        mAdapter = new BranchAdapter(lists, this);
        nrecycler.setAdapter(mAdapter);

        mAdapter.setonItemClickLitener(new BranchAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(BranchPhotoActivity.this, WaterFallActivity.class);
                intent.putExtra("title", "互动课题活动");
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
