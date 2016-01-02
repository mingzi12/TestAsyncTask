package com.mingzi.myapplication.asynctask;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/2.
 */
public class DelayOperator {
    private static final String TAG = "Current Thread is:";
    public void delay(){
        try {
            Thread.sleep(1000);
            Log.d(TAG,Thread.currentThread().getName());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
