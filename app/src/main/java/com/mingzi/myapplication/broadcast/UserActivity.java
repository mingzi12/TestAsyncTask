package com.mingzi.myapplication.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mingzi.myapplication.BaseAvtivity;
import com.mingzi.myapplication.R;

public class UserActivity extends BaseAvtivity {
    private MyBroascastReceiver mBroascastReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private Button mButton;
    private IntentFilter mIntentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBroascastReceiver = new MyBroascastReceiver();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.mingzi.MyBroadcast");
        mLocalBroadcastManager.registerReceiver(mBroascastReceiver, mIntentFilter);
        mButton = (Button) findViewById(R.id.emu_login_1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent("com.mingzi.MyBroadcast");
                mLocalBroadcastManager.sendBroadcast(mIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mBroascastReceiver);
    }
}
