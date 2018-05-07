package com.example.boybaby.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Boy Baby on 2018/4/28.
 *
 * 功能：动态加载一个布局文件
 * 解决的问题：每个页面都要加载同样的布局文件避免按钮监听事件的按键重复
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context);
        //参数一：布局文件的id
        //参数二：给加载好的布局再添加一个父容器
        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button titleBack = (Button) findViewById(R.id.button);
        Button titleEdit = (Button) findViewById(R.id.button1);
        //BACK按钮
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
        //EDIT按钮
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"你点击了Edit按钮！",Toast.LENGTH_LONG).show();
            }
        });
    }
}
