package com.example.boybaby.uicustomviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TitleBtnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_btn);
        //隐藏标题栏
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }
}
