package com.fw.dangjian.view;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BigPhotoActivity extends BaseActivity {

    @BindView(R.id.left)
    RelativeLayout iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.view_page)
    ViewPager view_page;
    private ArrayList<String> image1;

    private List<View> viewList;//图片资源的集合
    private int index;

    @Override
    protected int fillView() {
        return R.layout.activity_big_photo;
    }

    @Override
    protected void initUi() {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("图片详情");

        Intent intent = getIntent();
        if(intent!=null){
            index = intent.getIntExtra("index", 0);
            image1 = intent.getStringArrayListExtra("image");
        }

        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = image1.size();
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            Glide.with(this).load(image1.get(i)).into(imageView);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }
    }

    @Override
    protected void initData() {

        view_page.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();//
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));

                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        });

        view_page.setCurrentItem(index);

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
