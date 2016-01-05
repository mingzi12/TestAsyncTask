package com.mingzi.myapplication;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/5.
 */
public class ActivityCollector {
    private static ArrayList<Activity> mActivityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        mActivityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }
    public static void removeAll(){
        for (Activity activity : mActivityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
