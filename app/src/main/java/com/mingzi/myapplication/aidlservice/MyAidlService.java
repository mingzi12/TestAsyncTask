package com.mingzi.myapplication.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.mingzi.aidl.IPerson;

/**
 * Created by Administrator on 2016/1/4.
 */
public class MyAidlService extends Service {
    private String[] name = {"Kobe","James","Jardon","T-MAC","KD"};
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    private IPerson.Stub mIBinder = new IPerson.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String queryPerson(int num) throws RemoteException {
            if (num>=0 && num<=4){
                return name[num];
            }
            return null;
        }
    };
}
