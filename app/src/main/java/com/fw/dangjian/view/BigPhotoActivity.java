package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BigPhotoActivity extends BaseActivity {

    @BindView(R.id.left)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    private int image;

    @Override
    protected int fillView() {
        return R.layout.activity_big_photo;
    }

    @Override
    protected void initUi() {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("图片详情");
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if(intent!=null){
            image = intent.getIntExtra("image",0);
            iv_pic.setImageResource(image);
        }

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
