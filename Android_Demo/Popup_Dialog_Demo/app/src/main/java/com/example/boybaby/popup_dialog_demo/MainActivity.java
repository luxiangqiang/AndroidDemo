package com.example.boybaby.popup_dialog_demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //显示对话框
    public void DisplayDialog(View view){
        switch(view.getId()){
            //显示对话框
            case R.id.btnDisplayDialog:
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)//对话框图片
                        .setTitle("Text")//对话框标题
                        .setMessage("这是一个提醒框")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //当点击按钮时给一个消息提示
                                Toast.makeText( MainActivity.this, "确定按钮被点击", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //当点击按钮时给一个消息提示
                                Toast.makeText( MainActivity.this, "取消按钮被点击", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            break;
        }
    }
}
