package com.example.boybaby.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：ArrayAdapter可以它可以通过泛型来指定适配器的数据类型
 */
public class MainActivity extends AppCompatActivity {
//    private String[] data = {
//      "Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple"
//            ,"Apple","Apple","Apple","Apple","Apple","Apple","Apple"
//            ,"Apple","Apple","Apple","Apple","Apple","Apple","Apple"
//    };

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
 * 参数一：上下文对象
 * 参数二：android.R.layout.simple_list_item_1 作为ListView子项布局的id
 *        这是一个Android内置布局文件，里边只有一个TextView
 *参数三：数组对象
 */
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this,android.R.layout.simple_list_item_1,data);
        //初始化
        initFruits();
        //调用适配器
        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        //实例化ListView
        ListView listView =(ListView) findViewById(R.id.list_view);
        //给ListView添加此适配器
        listView.setAdapter(fruitAdapter);
        //ListView的监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //获取用户监听
                //fruitList存放的都是fruit对象，获取某一个子项的对象
                Fruit fruit =fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
    //初始化水果类数据
    public void initFruits(){
        for (int i = 0;i < 20;i++){
            Fruit fruit = new Fruit("Apple",R.drawable.fruit);
            fruitList.add(fruit);
        }
    }
}
