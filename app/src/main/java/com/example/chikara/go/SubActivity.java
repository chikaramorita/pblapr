package com.example.chikara.go;

/**
 * Created by ubuntu on 17/11/17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        int my_num = preferences.getInt("mynum",0);
        final ReadJson starts = new ReadJson(my_num);
        starts.rereadVolley();
        starts.setOnCallBack(new ReadJson.CallBackTask() {
            @Override
            public void CallBack(String response) {
                TextView textx = (TextView) findViewById(R.id.textView);
                textx.setText("sefsdasadsadsadsadsafdsa");//(starts.obdata);

            }
        });


    }}
