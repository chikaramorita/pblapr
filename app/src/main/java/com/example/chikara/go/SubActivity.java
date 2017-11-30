package com.example.chikara.go;

/**
 * Created by ubuntu on 17/11/17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
=======
>>>>>>> 62ab1cd00a92e8d71b29dc59f4d63e691a8d93b5
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
<<<<<<< HEAD
        starts.setOnCallBack(new StartJson.CallBackTask() {
            @Override
            public void CallBack(String response) {
                TextView text = (TextView)findViewById(R.id.textView);
                text.setText(str);
            }

        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
=======
        starts.setOnCallBack(new ReadJson.CallBackTask() {
>>>>>>> 62ab1cd00a92e8d71b29dc59f4d63e691a8d93b5
            @Override
            public void CallBack(String response) {
                TextView textx = (TextView) findViewById(R.id.textView);
                textx.setText("sefsdasadsadsadsadsafdsa");//(starts.obdata);

            }
        });


    }}
