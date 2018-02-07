package com.fw.dangjian.view;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.map)
    MapView mMapView;
    AMap aMap;
    public int alpha = 15;
    public int color = Color.parseColor("#D00808");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, color, alpha);
        MyApplication.getInstance().addActivity(this);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象

        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        initUi();
        initData();

    }


    public void initUi() {
//        蓝点定位
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


//       marker标记
        LatLng latLng = new LatLng(39.906901, 116.397972);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.title("北京").snippet("");
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.flag)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        final Marker marker = aMap.addMarker(markerOption);

    }

    public void initData() {

    }

    @OnClick({R.id.left, R.id.rl_search, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.rl_search:

                break;
            case R.id.et_search:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}
