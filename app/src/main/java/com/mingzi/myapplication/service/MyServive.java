package com.mingzi.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2016/1/3.
 */
public class MyServive extends Service {
    private static final String TAG = "TestService";
    private int count = 0;
    private boolean isQuit=false;
    private MyBinder mBinder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind()方法被调用");
        return mBinder;
    }
    @Override
    public void onCreate(){
        Log.d(TAG,"onCreate()方法被调用");
        super.onCreate();
        new Thread() {
            public void run() {
                while (!isQuit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                count++;
            }
        }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand()被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind()方法被调用");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy()被调用");
        super.onDestroy();
    }
    public class MyBinder extends Binder{

        public int getCount(){
            return count;
        }
    }
}
