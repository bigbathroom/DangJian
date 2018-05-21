package com.fw.dangjian;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.view.ActionFragment;
import com.fw.dangjian.view.HomeFragment;
import com.fw.dangjian.view.ManageFragment;
import com.fw.dangjian.view.MineFragment;
import com.fw.dangjian.view.StudyFragment;
import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout fragment;
    @BindView(R.id.menu_group)
    RadioGroup  menu_group;
    @BindView(R.id.rd_home)
    RadioButton rd_home;
    @BindView(R.id.rd_action)
    RadioButton rd_action;
    @BindView(R.id.rd_study)
    RadioButton rd_study;
    @BindView(R.id.rd_manage)
    RadioButton rd_manage;
    @BindView(R.id.rd_mine)
    RadioButton rd_mine;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ActionFragment actionFragment;
    private StudyFragment studyFragment;
    private MineFragment mineFragment;
    private ManageFragment manageFragment;


    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private int selindex = 0;
    private String[] FRAGMENT_TAG = new String[]{"home", "market", "store", "cart", "mine"};

//    int color = Color.WHITE;
    int color =Color.parseColor("#D00808");

    int flag = 0;
    public Activity act;
    public int alpha = 0;
    int managerId;

    // 定义一个变量，来标识是否退出
    private static boolean isExit;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        act = this;
        SPUtils.put(this, ConstanceValue.IS_FIRST_START,"true");
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        MyApplication.getInstance().addActivity(this);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            //读取上一次界面Save的时候tab选中的状态
            selindex = savedInstanceState.getInt(PRV_SELINDEX, selindex);
            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            actionFragment = (ActionFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            studyFragment = (StudyFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            manageFragment = (ManageFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);
            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[4]);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selindex = extras.getInt("index", 0);
        }

        initlistener();

    }
    private void initlistener() {
        if (selindex == 0){
            menu_group.check(R.id.rd_home);
        }else if (selindex == 1){
            menu_group.check(R.id.rd_action);
        }else if (selindex == 2){
            menu_group.check(R.id.rd_study);
        }else if (selindex == 3){
            menu_group.check(R.id.rd_manage);
        }else if (selindex == 4){
            menu_group.check(R.id.rd_mine);
        }
        showFragment(selindex);

        menu_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_home:
                        flag = 1;
                        showFragment(0);
                        break;
                    case R.id.rd_action:
                        flag = 2;
                        showFragment(1);
                        break;
                    case R.id.rd_study:
                        flag = 3;
                        showFragment(2);
                        break;
                    case R.id.rd_manage:
                        flag = 4;
                        showFragment(3);
                        break;
                    case R.id.rd_mine:
                        showFragment(4);
                        break;
                }
            }
        });
    }



    private void showFragment(int i) {
        ft = fragmentManager.beginTransaction();
        hideFragment(ft);/*想要显示一个fragment,先隐藏所有fragment，防止重叠*/
        switch (i) {
            case 0:
                StatusBarUtil.setColor(act, color, alpha);
                /*如果fragment1已经存在则将其显示出来*/
                if (homeFragment != null)
                    ft.show(homeFragment);
                /*否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add*/
                else {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.frame, homeFragment, FRAGMENT_TAG[i]);
                }
                break;
            case 1:
//                StatusBarUtil.setColor(act, color, alpha);
                if (actionFragment != null)
                    ft.show(actionFragment);
                else {
                    actionFragment = new ActionFragment();
                    ft.add(R.id.frame, actionFragment, FRAGMENT_TAG[i]);
                }
                break;
            case 2:
//                StatusBarUtil.setColor(act, color, alpha);
                if (studyFragment != null)
                    ft.show( studyFragment);
                else {

                    studyFragment = new StudyFragment();
                    ft.add(R.id.frame, studyFragment, FRAGMENT_TAG[i]);
                }
                break;
            case 3:
//                StatusBarUtil.setColor(act, color, alpha);
                if (manageFragment != null)
                    ft.show(manageFragment);
                else {
                    manageFragment = new ManageFragment();
                    ft.add(R.id.frame, manageFragment, FRAGMENT_TAG[i]);
                }

                break;
            case 4:
//                StatusBarUtil.setColor(act,color_mine , 0);
                if (mineFragment != null)
                    ft.show(mineFragment);
                else {
                    mineFragment = new MineFragment();
                    ft.add(R.id.frame, mineFragment, FRAGMENT_TAG[i]);
                }

                break;

        }
        selindex = i;
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (homeFragment != null)
            ft.hide(homeFragment);
        if (actionFragment != null)
            ft.hide(actionFragment);
        if (studyFragment != null)
            ft.hide(studyFragment);
        if (manageFragment != null)
            ft.hide(manageFragment);
        if (mineFragment != null)
            ft.hide(mineFragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRV_SELINDEX, selindex);
        super.onSaveInstanceState(outState);
    }

    public void onResume() {
        super.onResume();
        isExit = false;
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    public void onBackPressed() {
        exit();
//        super.onBackPressed();
    }



    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            MyApplication.getInstance().exit();
        }
    }


}
