package com.example.boybaby.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();//初始化

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //瀑布流布局
        StaggeredGridLayoutManager linearLayoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        //RecyclerView的布局方式  LinearLayoutManager是线性布局方式
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        //默认是纵向布局，设置为横向布局
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //为RecyclerView添加适配器
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }
    //初始化水果数据
    public void initFruit(){
        for(int i =1;i<=20;i++){
            Fruit fruit = new Fruit(getRandomLengthName("Apple"),R.mipmap.ic_launcher);
            fruitList.add(fruit);
        }
    }
    //产生随机数
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
