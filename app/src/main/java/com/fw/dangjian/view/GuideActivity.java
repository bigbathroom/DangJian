package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.adapter.GuidePageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.guide_vp)
    ViewPager vp;
    @BindView(R.id.bt_tiyan)
    TextView bt_tiyan;
    @BindView(R.id.guide_ll_point)
    LinearLayout guide_ll_point;

    private ViewGroup vg;//放置圆点
    //实例化原点View
    private ImageView iv_point;
    private ImageView []ivPointArray;


    public int[] imageIdArray;
    private List<View> viewList;//图片资源的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        MyApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        initUI();

    }

    private void initUI() {


        //加载ViewPager
        initViewPager();

        //加载底部圆点
        initPoint();
    }


    /**
     * 加载底部圆点
     */

    private void initPoint() {
//这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[imageIdArray.length];

        //使用LayoutParams改变控件的位置
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(15,15);
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = imageIdArray.length;

        if(size>0){
            layoutParams.leftMargin=20;
        }
        for (int i = 0;i<size;i++){
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(layoutParams);
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0){
                iv_point.setBackgroundResource(R.drawable.gray_dots_black);
            }else{
                iv_point.setBackgroundResource(R.drawable.gray_radius);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }
    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        imageIdArray = new int[]{ R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }
        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 滑动后的监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0;i<length;i++){
            ivPointArray[position].setBackgroundResource(R.drawable.gray_dots_black);
            if (position != i){
                ivPointArray[i].setBackgroundResource(R.drawable.gray_radius);
            }
        }
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            bt_tiyan.setVisibility(View.VISIBLE);
            guide_ll_point.setVisibility(View.GONE);

        }else {
            bt_tiyan.setVisibility(View.GONE);
            guide_ll_point.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @OnClick({R.id.bt_tiyan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tiyan:

                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
                break;
        }
    }




}
