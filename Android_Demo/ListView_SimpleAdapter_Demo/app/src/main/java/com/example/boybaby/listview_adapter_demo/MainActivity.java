package com.example.boybaby.listview_adapter_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();

        listView =(ListView) findViewById(R.id.listview);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                                                         data,
                                                        R.layout.list_message,
                                                        new String[]{"id","project"},
                                                        new int[]{R.id.text1,R.id.text2});
        listView.setAdapter(simpleAdapter);
    }
    public void fillData(){

        data = new ArrayList<Map<String, String>>();
        Map<String,String> map = new HashMap<String,String>();
        //第一条数据
        map.put("id","1");
        map.put("project","螺丝帽");
        data.add(map);
        //第二条数据
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("id","2");
        map2.put("project","螺丝顶");
        data.add(map2);

    }
}
