package com.example.apple.demo72;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
//主窗体类
public class MainActivity extends AppCompatActivity {
    //定义绘图控件对象
    MyView myView;
    //定义统计数据列表
    ArrayList<Double> yList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化统计图控件对象
        myView = (MyView) findViewById(R.id.activity_man_myview);
        //初始化数据列表并赋值
        yList = new ArrayList<Double>();
        yList.add((double) 2.103);
        yList.add(4.05);
        yList.add(6.60);
        yList.add(3.08);
        yList.add(4.32);
        yList.add(2.0);
        yList.add(5.0);
        //定义统计图表中底部所展示的数据集合
        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("05-19");
        xRawDatas.add("05-20");
        xRawDatas.add("05-21");
        xRawDatas.add("05-22");
        xRawDatas.add("05-23");
        xRawDatas.add("05-24");
        xRawDatas.add("05-25");
        xRawDatas.add("05-26");
        //为统计图对象传值
        myView.setData(yList, xRawDatas, 8, 2);
    }
}
