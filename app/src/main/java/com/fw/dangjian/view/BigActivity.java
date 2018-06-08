package com.fw.dangjian.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BigActivity extends BaseActivity {


    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    @Override
    protected int fillView() {
        return R.layout.activity_big;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("");

        Intent intent = getIntent();
        if(intent!=null){
            byte[] bis = intent.getByteArrayExtra("image");
            Bitmap image= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            iv_pic.setImageBitmap(image);
        }
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
