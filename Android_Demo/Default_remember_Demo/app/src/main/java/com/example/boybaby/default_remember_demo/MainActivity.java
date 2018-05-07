package com.example.boybaby.default_remember_demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    //数据存储API(SharedPreferences)
    private SharedPreferences sp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    /**
     * userinfo :存放数据的文件名 （后缀.xml自动加上）
     * Context.MODE_PRIVATE：指定该SharedPreferences数据只能被本应用程序读、写
     */
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE); // 完成sp的初始化。
        String saved_username = sp.getString("USERNAME", ""); //获取sp里面存储的数据
        String saved_password = sp.getString("PASSWORD","");
        username.setText(saved_username);//将sp中存储的编号写入EditeText
        password.setText(saved_password);//将sp中存储的编号写入EditeText

    }
    //登陆
    public void Login(View view){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USERNAME", username.getText().toString());
        editor.putString("PASSWORD",password.getText().toString());
        editor.commit();
        Toast.makeText(MainActivity.this,"登陆成功！",Toast.LENGTH_LONG).show();
    }
}
