package com.example.apple.demo43;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //显示食品列表控件
    ListView listView;
    //列表绑定数据集合
    ArrayList<ListData> list=new ArrayList<>();
    //列表绑定适配器类
    ListAdapter listAdapter;
    //获取订单信息按钮
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用初始化方法
        initView();
    }
    //初始化界面相关信息
    public void initView()
    {
        //初始化列表控件
        listView=(ListView) findViewById(R.id.main_activity_listview);
        //初始化获取订单信息按钮
        sendButton=(Button)findViewById(R.id.main_activity_btnsend);
        //创建列表中第一行显示信息对象
        ListData data1=new ListData();
        //设置第一行食品图片使用的id
        data1.setImageID(R.mipmap.gjf);
        //设置第一行食品名称
        data1.setName("盖浇饭");
        //设置第一行食品价格
        data1.setPrice(12.0);
        //将第一行食品相关信息对象添加到数据列表中
        list.add(data1);
        //创建列表中第二行显示信息对象
        ListData data2=new ListData();
        //设置第二行食品图片使用的id
        data2.setImageID(R.mipmap.nrm);
        //设置第二行食品名称
        data2.setName("牛肉面");
        //设置第二行食品价格
        data2.setPrice(10.0);
        //将第二行食品相关信息对象添加到数据列表中
        list.add(data2);
        //创建列表中第三行显示信息对象
        ListData data3=new ListData();
        //设置第三行食品图片使用的id
        data3.setImageID(R.mipmap.rjm);
        //设置第三行食品名称
        data3.setName("肉夹馍");
        //设置第三行食品价格
        data3.setPrice(11.0);
        //将第三行食品相关信息对象添加到数据列表中
        list.add(data3);
        //初始化litview适配器类对象
        listAdapter=new ListAdapter(MainActivity.this,list);
        //listview绑定适配器类
        listView.setAdapter(listAdapter);
        //回收适配器类更新数据集合
        list=(ArrayList<ListData>) listAdapter.getlistdata();
        //设置获取订单信息按钮事件监听，通过Toast显示订单信息
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示订单信息字符串
                String s="订餐信息为：\n";
                //订单总价格
                double totalPrice=0;
                //历遍litview绑定信息列表集合
                for (int i=0;i<list.size();i++)
                {
                    //如果该行订单数量大于零，获取该行相关订单信息
                    if (list.get(i).getCount()>0)
                    {
                        //追加订单信息：食品名称和份数
                        s+=list.get(i).getName()+": "+list.get(i).getCount()+"份\n";
                        //累加食品价格
                        totalPrice+=list.get(i).getPrice()*list.get(i).getCount();
                    }
                }
                //判断总价格是否大于0，如果大于0表示有食品订购信息，显示总价格
                if (totalPrice>0)
                {
                    //追加总金额到显示字符串
                    s+="总金额："+totalPrice+"元";
                }
                else
                {
                    s="没有订购食品";
                }
                //使用Toast显示订单信息
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });

    }
}
