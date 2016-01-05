package com.mingzi.myapplication.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.mingzi.myapplication.ActivityCollector;

/**
 * Created by Administrator on 2016/1/5.
 */
public class MyBroascastReceiver extends BroadcastReceiver {
    private static final String MY_ACTION="com.mingzi.MyBroadcast";
    @Override
    public void onReceive(final Context context, final Intent intent) {
        AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(context);
        mAlertBuilder.setTitle("Warmming!");
        mAlertBuilder.setMessage("Your account is logined in another palce!");
        mAlertBuilder.setCancelable(false);
        mAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.removeAll();
                Intent mIntent1 = new Intent(context, LoginActivity.class);
                mIntent1.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mIntent1);
            }
        });
        AlertDialog mAlertDialog = mAlertBuilder.create();
        mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mAlertDialog.show();
    }
}
