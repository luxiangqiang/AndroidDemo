package com.example.boybaby.select_date_demo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mtimebutton;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtimebutton = (Button) findViewById(R.id.time_button);
        textView = (TextView) findViewById(R.id.textView);
        mtimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        System.out.println(String.format("%d-%d-%d",i,i1+1,i2));
                        textView.setText(String.format("%d-%d-%d",i,i1+1,i2));
                    }
                },2016,1,1).show();
            }
        });
    }

}
