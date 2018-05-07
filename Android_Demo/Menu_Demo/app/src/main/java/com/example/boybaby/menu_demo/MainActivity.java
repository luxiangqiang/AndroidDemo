package com.example.boybaby.menu_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 功能：隐式跳转页面
     * @param view
     */
    public void Intent_Main2(View view){
        Intent intent =new  Intent("1");
        intent.addCategory("add.category");
        startActivity(intent);
    }

    /**
     *   功能：显示菜单
     *   参数一：用于指定我们通过哪一个资源文件来创建菜单（菜单xml）
     * @param menu 用于指定我们的菜单项将添加到哪一个Menu对象中
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * 功能：判断用户点击了菜单的哪一个选项
     * item.getItemId() 通过id来判断
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"You click Add",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this,"You click Remove",Toast.LENGTH_LONG).show();
                //销毁活动
                finish();
                break;
            default:
        }
        return true;
    }
}
