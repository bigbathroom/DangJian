package com.fw.dangjian.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.WaterFallAdapter;
import com.fw.dangjian.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class WaterFallActivity extends BaseActivity {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;

    WaterFallAdapter  adapter;
    private String title;

    @Override
    protected int fillView() {
        return R.layout.activity_water_fall;
    }

    @Override
    protected void initUi() {

        left.setVisibility(View.VISIBLE);
        tv_title.setText("照片详情");

        title = getIntent().getStringExtra("title");
        tv_title1.setText(title);

        ArrayList<Integer> pictures = new ArrayList<>();
        pictures.add(R.mipmap.er);
        pictures.add(R.mipmap.wu);
        pictures.add(R.mipmap.si);
        pictures.add(R.mipmap.ba);
        pictures.add(R.mipmap.liu);
        pictures.add(R.mipmap.si);
        pictures.add(R.mipmap.qi);
        pictures.add(R.mipmap.liu);
        pictures.add(R.mipmap.si);
        pictures.add(R.mipmap.qi);
        adapter = new WaterFallAdapter(pictures,this);

        nrecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        nrecycler.setAdapter(adapter);
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
}
