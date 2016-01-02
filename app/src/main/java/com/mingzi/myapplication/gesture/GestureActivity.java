package com.mingzi.myapplication.gesture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.mingzi.myapplication.MainActivity;
import com.mingzi.myapplication.R;


public class GestureActivity extends AppCompatActivity {
    private GestureDetector mGestureDetector;
    private MyGestureListener mGestureListener;
    private final static int MIN_MOVE = 200;   //最小距离
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mGestureListener = new MyGestureListener();
        mGestureDetector = new GestureDetector(GestureActivity.this,mGestureListener);
    }
    public boolean onTouchEvent(MotionEvent event){
        mGestureDetector.onTouchEvent(event);
        return true;
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getY() - e2.getY() > MIN_MOVE){
                startActivity(new Intent(GestureActivity.this, MainActivity.class));
                Toast.makeText(GestureActivity.this, "通过手势启动Activity", Toast.LENGTH_SHORT).show();
            }
            else if(e1.getY() - e2.getY()  < MIN_MOVE){
                finish();
                Toast.makeText(GestureActivity.this,"通过手势关闭Activity",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

}
