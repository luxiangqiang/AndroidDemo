package com.example.boybaby.intent_passdatademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_Reesult_data);
    }

    //通过startActivity页面传值
    public void Intent_passdata(View view) {
        String data = "HelloWord!";
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("extra_data", data);
        startActivity(intent);
    }
    //通过startActivityForResult函数传值
    public void Pass_ForResult(View view){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent,1);
    }

    /**
     * 功能：接收销毁页面传递的数据
     * @param requestCode 活动的请求码 （即数据的来源）
     * @param resultCode 处理结果
     * @param data  接受的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String result_data = data.getStringExtra("result_data");
                    textView.setText(result_data);
                }
                break;
        }  
    }
}
