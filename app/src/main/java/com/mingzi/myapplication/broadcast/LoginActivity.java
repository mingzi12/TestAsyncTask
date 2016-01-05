package com.mingzi.myapplication.broadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mingzi.myapplication.BaseAvtivity;
import com.mingzi.myapplication.R;

public class LoginActivity extends BaseAvtivity  implements View.OnClickListener{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Button mLoginBtn;
    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
       if (!mSharedPreferences.getString("username","").equals("")){
           mUsernameEdit.setText(mSharedPreferences.getString("username",""));
           mPasswordEdit.setText(mSharedPreferences.getString("password",""));
       }

    }

    public void initView(){
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mUsernameEdit = (EditText) findViewById(R.id.username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = mUsernameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        if (username.equals("mingzi")&&password.equals("sam")){
            mEditor = mSharedPreferences.edit();
            mEditor.putString("username",username);
            mEditor.putString("password", password);
            mEditor.commit();
            Intent mIntent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(mIntent);
            Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(LoginActivity.this,"Login fail",Toast.LENGTH_SHORT).show();

    }
}
