package com.example.chikara.go;

/**
 * Created by ubuntu on 17/11/17.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);
        ReadJson starts = new ReadJson(14);
        starts.rereadVolley();
        starts.setOnCallBack(new StartJson.CallBackTask() {
            @Override
            public void CallBack(String response) {
                TextView text = (TextView)findViewById(R.id.textView);
                text.setText(str);
            }

        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}