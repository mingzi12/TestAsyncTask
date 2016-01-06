package com.mingzi.myapplication;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mingzi.myapplication.asynctask.MyAsyncTask;
import com.mingzi.myapplication.broadcast.LoginActivity;
import com.mingzi.myapplication.gesture.AddGestureActivity;
import com.mingzi.myapplication.gesture.GestureActivity;
import com.mingzi.myapplication.service.MyServive;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int UPDATE_TXT=1;
    private static final String SERVICE_TAG = "CONNECTION";
    private TextView mTextView;
    private Button mButton;
    private TextView mText;
    private Button mProgressBtn;
    private ProgressBar mProgressBar;
    private Button mGestureBtn;
    private Button mAddgestureBtn;
    private boolean isExit = false;
    private Button startBtn;
    private Button stopBtn;
    private Button bindBtn;
    private Button unBindBtn;
    private Button statusBtn;
    private MyServive.MyBinder mBinder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
          System.out.println("onServiceConnected()方法被调用");
            mBinder = (MyServive.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("onServiceDisconnected()方法被调用");
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initServiceBtn();
        initBindServiceBtn();
        initLoginBtn();
        initQueryContactBtn();
        mTextView = (TextView) findViewById(R.id.mtextview);
        mText = (TextView) findViewById(R.id.mtextview_1);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mProgressBtn = (Button) findViewById(R.id.mProgressBtn);
        mAddgestureBtn = (Button) findViewById(R.id.add_gesture_btn);
        mProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask mMyAsynTask = new MyAsyncTask(mProgressBar,mText);
                mMyAsynTask.execute(1000);
            }
        });
        mButton = (Button) findViewById(R.id.mBtn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message mMessage = new Message();
                        mMessage.what=UPDATE_TXT;
                        mHandler.sendMessage(mMessage);
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        mGestureBtn = (Button) findViewById(R.id.gestureBtn);
        mGestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GestureActivity.class));
            }
        });
        mAddgestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddGestureActivity.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
        if (!isExit){
            isExit = true;
            Toast.makeText(MainActivity.this,"再按一次退出应用",Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
        else {
            android.os.Process.killProcess(android.os.Process.myPid());
            //System.exit(0);
        }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    Handler mHandler = new Handler(){
       @Override
        public void handleMessage(Message msg){
           switch (msg.what){
               case UPDATE_TXT:
                   mTextView.setText("通过Handler来更新UI组件");
                   break;
               case 0:
                   super.handleMessage(msg);
                   isExit = false;
                   break;
           }
       }
   };
    public void initServiceBtn(){
         final Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("com.mingzi.myapplication.service.Myservice");
        startBtn = (Button) findViewById(R.id.start_service_btn);
        stopBtn = (Button) findViewById(R.id.stop_service_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(mServiceIntent);
                sendBroadcast(new Intent("com.mingzi.MyBroadcast"));
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(mServiceIntent);
            }
        });
    }
    public void initBindServiceBtn(){
        bindBtn = (Button) findViewById(R.id.bind_service_btn);
        unBindBtn = (Button) findViewById(R.id.unbind_service_btn);
        statusBtn = (Button) findViewById(R.id.status_btn);
        bindBtn.setOnClickListener(this);
        unBindBtn.setOnClickListener(this);
        statusBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("com.mingzi.myapplication.service.Myservice");
        switch (v.getId()){
            case R.id.bind_service_btn:
                bindService(mServiceIntent,conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_btn:
                unbindService(conn);
                break;
            case R.id.status_btn:
                System.out.println(mBinder.getCount());
                break;
            default:
                break;
        }
    }
    public void initLoginBtn(){
        Button mLoginBtn = (Button) findViewById(R.id.emu_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
    private void initQueryContactBtn(){
        Button mContactBtn = (Button) findViewById(R.id.queerycontactBtn);
        mContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsgs();
                insertMsg();
            }
        });
    }
    private void getMsgs(){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        //获取的是哪些列的信息
        Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
        while(cursor.moveToNext())
        {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            System.out.println("地址:" + address);
            System.out.println("时间:" + date);
            System.out.println("类型:" + type);
            System.out.println("内容:" + body);
            System.out.println("======================");
        }
        cursor.close();
    }
    private void insertMsg(){
        ContentResolver mMsgResolver = getContentResolver();
        Uri mContactUri = Uri.parse("content://sms/");
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("address","15676389765");
        mContentValues.put("type",1);
        mContentValues.put("date",System.currentTimeMillis());
        mContentValues.put("body","成功插入短信");
        mMsgResolver.insert(mContactUri,mContentValues);
    }
}
