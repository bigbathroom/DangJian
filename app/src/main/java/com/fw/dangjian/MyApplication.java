package com.fw.dangjian;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext(){
        return context;
    }

    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance()
    {
        if(null == instance)
        {
            instance = new MyApplication();
        }
        return instance;
    }


    //添加Activity到容器中
    public void addActivity(Activity activity)
    {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList)
        {
            activity.finish();
        }
        System.exit(0);
    }

}
