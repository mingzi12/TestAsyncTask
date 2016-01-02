package com.mingzi.myapplication.asynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/2.
 */
public class MyAsyncTask extends AsyncTask<Integer,Integer,String>{
    private ProgressBar mProgressBar;
    private TextView mTextView;

    public MyAsyncTask(ProgressBar mProgressBar, TextView mTextView) {
        super();
        this.mProgressBar = mProgressBar;
        this.mTextView = mTextView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        DelayOperator mDelayOperator = new DelayOperator();
        int i = 0;
        for (i=10;i<=100;i+=10){
            mDelayOperator.delay();
            publishProgress(i);
        }
        return i+params[0].intValue()+"";
    }

    @Override
    protected void onPreExecute() {
        mTextView.setText("开始执行异步线程~");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        mProgressBar.setProgress(value);
    }
}
