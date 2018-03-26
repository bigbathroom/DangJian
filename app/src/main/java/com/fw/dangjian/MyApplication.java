package com.fw.dangjian;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class MyApplication extends Application {

    private static Context context;
    private List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

//      Umeng分享和三方登录
        UMShareAPI.get(this);
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;

        //     Umeng统计
        //初始化
        UMConfigure.init(this,"5ab1c2638f4a9d6bda0000b2","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        //设置类型
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //开启Log
        UMConfigure.setLogEnabled(true);
        //打开调试模式
        MobclickAgent.setDebugMode( true );
    }

    {
        PlatformConfig.setWeixin("wxf6c3e58b34863382", "78767ac6eb8459d162dcf7687427ac65");
        PlatformConfig.setQQZone("101468642", "bff4eb8d46f414fae74b7dd673c9d2ef");
//        PlatformConfig.setSinaWeibo("1972583338", "4b500e1eca29b11110204d0f3d34b50c", "http://www.zyt1880.cn");
    }


    public static Context getContext() {
        return context;
    }

    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }


    //添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

}
