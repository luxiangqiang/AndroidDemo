package com.example.boybaby.activityliftcycledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Tag","onCreate执行了！");
    }
    //启动NormalActivity
    public void StartNor(View view){
        Intent intent = new Intent(MainActivity.this,NormalActivity.class);
        startActivity(intent);
    }
    //启动DialogActivity
    public void StartDialog(View view){
        Intent intent = new Intent(MainActivity.this,DialogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Tag","onStart方法执行了！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag","onResume方法执行了!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Tag","onPause执行了！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Tag","onStop方法执行了！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag","onDestroy方法执行了！");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Tag","onRestart执行了！");
    }
}
