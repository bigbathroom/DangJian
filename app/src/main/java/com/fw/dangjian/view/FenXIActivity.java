package com.fw.dangjian.view;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.BoardBean;
import com.fw.dangjian.mvpView.BoardMvpView;
import com.fw.dangjian.presenter.BoardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class FenXIActivity extends BaseActivity implements BoardMvpView{

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_count1)
    TextView tv_count1;
    @BindView(R.id.tv_count2)
    TextView tv_count2;
    @BindView(R.id.tv_count3)
    TextView tv_count3;
    @BindView(R.id.tv_count4)
    TextView tv_count4;
    @BindView(R.id.tv_count5)
    TextView tv_count5;
    @BindView(R.id.chart)
    LineChartView lineChart;

    String[] weeks = {"周一","周二","周三","周四","周五","周六","周日"};//X轴的标注
//    int[] weather = {7,9,6,5,8,6,9};
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();
    private BoardPresenter boardPresenter;
    private int[] weather;

    @Override
    protected int fillView() {
        return R.layout.activity_fen_xi;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("统计分析");

        int managerId =1;

        boardPresenter = new BoardPresenter();
        boardPresenter.getBoard(managerId,this);
    }

    @Override
    public void onGetDataNext(BoardBean boardBean) {
        if(boardBean.result_code != null&&boardBean.result_code.equals("200")){
            tv_count1.setText(boardBean.result.memberCount);
            tv_count2.setText(boardBean.result.committeeCount);
            tv_count3.setText(boardBean.result.branchCount);
            tv_count4.setText(boardBean.result.todayInfoCount);
            tv_count4.setText(boardBean.result.totleInfoCount);

            int day1 = Integer.valueOf(boardBean.result.weekInfoCount.day1).intValue();
            int day2 = Integer.valueOf(boardBean.result.weekInfoCount.day2).intValue();
            int day3 = Integer.valueOf(boardBean.result.weekInfoCount.day3).intValue();
            int day4 = Integer.valueOf(boardBean.result.weekInfoCount.day4).intValue();
            int day5 = Integer.valueOf(boardBean.result.weekInfoCount.day5).intValue();
            int day6 = Integer.valueOf(boardBean.result.weekInfoCount.day6).intValue();
            int day7 = Integer.valueOf(boardBean.result.weekInfoCount.day7).intValue();

            //图表的数据
            weather = new int[]{day1,day2,day3,day4,day5,day6,day7};

            getAxisLables();//获取x轴的标注
            getAxisPoints();//获取坐标点
            initLineChart();//初始化

        }else{

        }
    }
    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.RED);  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
//        axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标
        axisX.setValues(mAxisValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//      data.setAxisXTop(axisX);  //x 轴在顶部

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        axisY.setTextColor(Color.BLACK);
//        axisY.setName("温度");//y轴标注
        axisY.setTextSize(10);//设置字体大小

        data.setAxisYLeft(axisY);  //Y轴设置在左边
//      data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }


    /**
     * X 轴的显示
     */
    private void getAxisLables(){
        for (int i = 0; i < weeks.length; i++) {
            mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(){
        for (int i = 0; i < weather.length; i++) {
            mPointValues.add(new PointValue(i, weather[i]));
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
