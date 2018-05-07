package com.example.boybaby.intent_passdatademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.text_data);
        //接收MainActivity页面传递的数据
        Intent intent =getIntent();
        String data = intent.getStringExtra("extra_data");
        textView.setText(data);

    }
//    //返回数据
//    public void Result_Data(View view){
//        Intent intent = new Intent();
//        intent.putExtra("result_data","我是返回数据");
//        setResult(RESULT_OK,intent);
//        finish();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("result_data","我是Back返回数据");
        setResult(RESULT_OK,intent);
        finish();
    }
}
