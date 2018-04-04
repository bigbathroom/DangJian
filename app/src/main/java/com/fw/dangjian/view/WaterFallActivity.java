package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.WaterFallAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.WaterBean;
import com.fw.dangjian.mvpView.WaterMvpView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class WaterFallActivity extends BaseActivity implements WaterMvpView{
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;

    WaterFallAdapter  adapter;
    private String title;
    private ArrayList<Integer> pictures;
    private Intent intent;
    private ArrayList<String> photos;


    @Override
    protected int fillView() {
        return R.layout.activity_water_fall;
    }

    @Override
    protected void initUi() {

        left.setVisibility(View.VISIBLE);
        tv_title.setText("照片详情");
        intent = getIntent();
        if (intent != null){
            title = intent.getStringExtra("title");
            tv_title1.setText(title);
            photos = intent.getStringArrayListExtra("photos");

        }


        adapter = new WaterFallAdapter(photos,this);

        nrecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        nrecycler.setHasFixedSize(true);
        nrecycler.setAdapter(adapter);

        adapter.setonItemClickLitener(new WaterFallAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(WaterFallActivity.this, BigPhotoActivity.class);
                intent.putExtra("index", position);
                intent.putExtra("image", photos);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {


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

    }
}
