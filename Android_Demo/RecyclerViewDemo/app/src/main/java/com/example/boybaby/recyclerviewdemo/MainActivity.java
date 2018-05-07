package com.example.boybaby.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();//初始化

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //RecyclerView的布局方式  LinearLayoutManager是线性布局方式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //为RecyclerView添加适配器
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }
    //初始化水果数据
    public void initFruit(){
        for(int i =1;i<=20;i++){
            Fruit fruit = new Fruit("Apple",R.mipmap.ic_launcher);
            fruitList.add(fruit);
        }
    }
}
