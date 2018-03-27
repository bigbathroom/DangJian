package com.fw.dangjian.view;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.ActionAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.MoneyBean;
import com.fw.dangjian.dialog.MoneyDialog;
import com.fw.dangjian.mvpView.MoneyMvpView;
import com.fw.dangjian.presenter.MoneyPresenter;
import com.fw.dangjian.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MoneyActivity extends BaseActivity implements MoneyMvpView{
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_party)
    RelativeLayout rl_party;
    @BindView(R.id.tv_party)
    TextView tv_party;
    @BindView(R.id.rl_money)
    RelativeLayout rl_money;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_tax_money)
    EditText tv_tax_money;
    @BindView(R.id.tv_calculate)
    TextView tv_calculate;

    private PopupWindow popWindow;
    private PopupWindow popWindow1;

    private ListView listView;
    private ListView listView1;
    private List<String> partys = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private MyAdapter adapter;
    private MoneyPresenter moneyPresenter;
    private String s;
    private Double money;
    private MoneyDialog moneyDialog;
    @Override
    protected int fillView() {
        return R.layout.activity_money;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("党费管理");

        partys.add("在职党员");
        partys.add("普通党员");
        partys.add("流动党员");
        partys.add("两新党员");
        partys.add("窗口党员");

        type.add("月薪");
        type.add("年薪");

        moneyPresenter = new MoneyPresenter();
    }

    @Override
    protected void initData() {

        listView = new ListView(this);
        listView.setBackgroundColor(getResources().getColor(R.color.white)); // 设置一个背景
        listView.setDivider(null);
        adapter = new MyAdapter(partys);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            // position 点击的条目的下标
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 条目点击事件
                tv_party.setText(partys.get(position));
                popWindow.dismiss(); // 关闭
            }
        });


        listView1 = new ListView(this);
        listView1.setBackgroundColor(getResources().getColor(R.color.white)); // 设置一个背景
        listView1.setDivider(null);
        adapter = new MyAdapter(type);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            // position 点击的条目的下标
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 条目点击事件
                tv_money.setText(type.get(position));
                popWindow1.dismiss(); // 关闭
            }
        });

    }

    @OnClick({R.id.left,R.id.rl_party,R.id.rl_money,R.id.tv_calculate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.rl_party:
                if (popWindow == null) {
                    popWindow = new PopupWindow(this);
//                    popWindow.setBackgroundDrawable(null);
                    popWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popWindow.setHeight(250);
                    popWindow.setAnimationStyle(R.style.ActionSheetDialogAnimation);
                    popWindow.setContentView(listView);
                    //设置popupWindow,当点击popupWindow外面的时候可以消失
                    popWindow.setBackgroundDrawable(new BitmapDrawable());
                    popWindow.setOutsideTouchable(true);
                    // 设置 popWindow可以响应焦点
                    popWindow.setFocusable(true);
                }

                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                } else {
                    popWindow.showAtLocation(this.findViewById(R.id.root), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 显示在输入框的下方
                }

                break;
            case R.id.rl_money:
                if (popWindow1 == null) {
                    popWindow1 = new PopupWindow(this);
//                    popWindow.setBackgroundDrawable(null);
                    popWindow1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popWindow1.setHeight(250);
                    popWindow1.setAnimationStyle(R.style.ActionSheetDialogAnimation);
                    popWindow1.setContentView(listView1);
                    //设置popupWindow,当点击popupWindow外面的时候可以消失
                    popWindow1.setBackgroundDrawable(new BitmapDrawable());
                    popWindow1.setOutsideTouchable(true);
                    // 设置 popWindow可以响应焦点
                    popWindow1.setFocusable(true);
                }

                if (popWindow1.isShowing()) {
                    popWindow1.dismiss();
                } else {
                    popWindow1.showAtLocation(this.findViewById(R.id.root), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 显示在输入框的下方
                }
                break;
            case R.id.tv_calculate:
                s = tv_tax_money.getText().toString().trim();
                money = Double.valueOf(s);

                moneyPresenter.getMoney(money.doubleValue(),this);
                break;
        }
    }


    private class MyAdapter extends BaseAdapter {

        private List<String> lists;
        private ActionAdapter.onItemClickLitener monItemClickLitener;

        public MyAdapter(List<String> lists) {
            this.lists = lists;
        }


        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(MoneyActivity.this).inflate(R.layout.item_list_school, null);
            } else {
                view = convertView;
            }

            TextView tvMsg = (TextView) view.findViewById(R.id.tv_msg);

            tvMsg.setText(lists.get(position));
            return view;
        }

    }



    @Override
    public void onGetDataNext(MoneyBean homeBean) {

        if(homeBean.result_code!= null && homeBean.result_code.equals("200")){

            moneyDialog = new MoneyDialog(this, R.style.MyDarkDialog,homeBean.result) {
                @Override
                public void confirm() {
                    dismiss();
                }
            };
            moneyDialog.show();
        }else{
            ToastUtils.show(this,homeBean.result_msg, Toast.LENGTH_SHORT);
        }
    }
}
