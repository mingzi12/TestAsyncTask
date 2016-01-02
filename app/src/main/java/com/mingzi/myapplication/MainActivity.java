package com.mingzi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mingzi.myapplication.asynctask.MyAsyncTask;
import com.mingzi.myapplication.gesture.AddGestureActivity;
import com.mingzi.myapplication.gesture.GestureActivity;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE_TXT=1;
    private TextView mTextView;
    private Button mButton;
    private TextView mText;
    private Button mProgressBtn;
    private ProgressBar mProgressBar;
    private Button mGestureBtn;
    private Button mAddgestureBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

   Handler mHandler = new Handler(){
       @Override
        public void handleMessage(Message msg){
           switch (msg.what){
               case UPDATE_TXT:
                   mTextView.setText("通过Handler来更新UI组件");
           }
       }
   };

}
