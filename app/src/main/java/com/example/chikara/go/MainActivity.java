package com.example.chikara.go;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
String key="sample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()

                                  {
                                      @Override
                                      public void onClick(View v) {
                                          final EditText editView = new EditText(MainActivity.this);
                                          new AlertDialog.Builder(MainActivity.this)
                                                  .setIcon(android.R.drawable.ic_dialog_info)
                                                  .setTitle("パスワード入力-->sample")
                                                  //setViewにてビューを設定します。
                                                  .setView(editView)
                                                  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int whichButton) {


                                                          if(key.equals(editView.getText().toString())) {
                                                              final EditText editView2 = new EditText(MainActivity.this);
                                                              new AlertDialog.Builder(MainActivity.this)
                                                                      .setIcon(android.R.drawable.ic_dialog_info)
                                                                      .setTitle("ポイント入力")
                                                                      .setView(editView2)
                                                                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                          public void onClick(DialogInterface dialog, int whichButton) {

                                                                                      String str =editView2.getText().toString();
                                                                                        int point=Integer.parseInt(str);
                                                                              SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);

                                                                             int points = preferences.getInt("point", 0)+point;





                                                                              //SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);

                                                                              SharedPreferences.Editor editor = preferences.edit();

                                                                              editor.putInt("point",points);
                                                                              editor.commit();

                                                                              String str2 =Integer.toString(points);
                                                                              TextView text = (TextView)findViewById(R.id.textView);
                                                                              text.setText(str2);


                                                                          }
                                                                      })
                                                                      .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                                                          public void onClick(DialogInterface dialog, int whichButton) {
                                                                          }
                                                                      })
                                                                      .show();
                                                          }



                                                      }
                                                  })
                                                  .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int whichButton) {
                                                      }
                                                  })
                                                  .show();

                                      }
                                  }
        );
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        int points = preferences.getInt("point", 0);
        String str =Integer.toString(points);
        TextView text = (TextView)findViewById(R.id.textView);
        text.setText(str);
    }
    public String errch (SharedPreferences preferences) {
        try {
            String user = preferences.getString("point", "");
            return user;
        } catch (Exception e) {
            return "0";
        }

    }
    public boolean isNumber(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }
}