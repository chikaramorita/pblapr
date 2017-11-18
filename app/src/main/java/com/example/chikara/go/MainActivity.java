package com.example.chikara.go;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;


public class MainActivity extends AppCompatActivity implements LocationListener{
String key=" ";
    int x=8;
    int my_num;
    double  lat;
    double  lon;
    long logseconds;

    StartJson ss= new StartJson("");

    String pagedate[]= {"https://www.jrkyushu-timetable.jp/cgi-bin/jr-k_time/tt_dep.cgi?c=28602",
            "http://www.kumamoto-nct.ac.jp/"

    };
    double box[] = {
            32.693048, 32.694312, 130.667810, 130.669934,//uto

            32.87544,32.890890,130.746771,130.750775  //kosen
    };
  int points;
    LocationManager locationManager;
    private LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        my_num = preferences.getInt("mynum",0);
       if(my_num==0){
           if ( Util.netWorkCheck(this.getApplicationContext() )){
               WriteJson starts = new WriteJson("新規登録をしました");


               ss.setOnCallBack(new StartJson.CallBackTask() {
                   @Override
                   public void CallBack(String response) {
                       my_num = ss.my_num;

                       new AlertDialog.Builder(MainActivity.this)
                               .setIcon(android.R.drawable.ic_dialog_info)
                               .setTitle("新規登録しました")
                               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int whichButton) {
                                   }
                               }).show();

                       TextView textx = (TextView) findViewById(R.id.textx);
                       textx.setText("会員番号" + my_num);
                       SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
                       SharedPreferences.Editor editor2 = preferences.edit();

                       editor2.putInt("mynum", my_num);
                       editor2.commit();

                   }
               });
               ss.rereadVolley();
           }




       }else {


           TextView textx = (TextView) findViewById(R.id.textx);
           textx.setText("会員番号" + my_num);
       }
        preferences = getSharedPreferences("settings", MODE_PRIVATE);
        points = preferences.getInt("point", 0);
        String str =Integer.toString(points);
        TextView text = (TextView)findViewById(R.id.textView);
        text.setText(str);

        SharedPreferences preferences2 = getSharedPreferences("settings", MODE_PRIVATE);
        logseconds= preferences.getLong("minu", 0);

        if ( Util.netWorkCheck(this.getApplicationContext() )) {
            // 非同期通信などの処理をかく


            LocationManager mLocationManager =
                    (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // Criteriaオブジェクトを生成
            Criteria criteria = new Criteria();

            // Accuracyを指定(低精度)
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);

            // PowerRequirementを指定(低消費電力)
            criteria.setPowerRequirement(Criteria.POWER_LOW);

            // ロケーションプロバイダの取得
            String provider = mLocationManager.getBestProvider(criteria, true);

            // 取得したロケーションプロバイダを表示
            // TextView tv_provider = (TextView) findViewById(R.id.Provider);
            // tv_provider.setText("Provider: " + provider);

            // LocationListenerを登録
            if (provider != null) {
                mLocationManager.requestLocationUpdates(provider, 0, 0, this);
                ProgressBar ntext = (ProgressBar) findViewById(R.id.nons);
                ntext.setVisibility(View.INVISIBLE);
            } else {
                ProgressBar ntext = (ProgressBar) findViewById(R.id.nons);
                ntext.setVisibility(View.VISIBLE);
            }

        }

       Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);

                points = preferences.getInt("point", 0);



                if (check() == true) {
                    points = points + 100;
                    SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);

                    SharedPreferences.Editor editor = pref.edit();

                    editor.putInt("point", points);
                    String str =Integer.toString(points);
                    TextView text = (TextView)findViewById(R.id.textView);
                    text.setText(str);
                    Toast.makeText(MainActivity.this,
                            "ポイント追加",
                            Toast.LENGTH_SHORT).show();

                } else {
                    //TextView pointv = (TextView) findViewById(point);
                    Toast.makeText(MainActivity.this,
                            "条件を満たしていません",
                            Toast.LENGTH_SHORT).show();
                }


                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("point",points);
                editor.commit();

            }
        });








        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()

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

                                                                              String str = editView2.getText().toString();
                                                                             if(cheeek(str)==true) {
                                                                                 int point = Integer.parseInt(str);
                                                                                 if (point >= 100000000) {
                                                                                     Toast.makeText(MainActivity.this,
                                                                                             "これ以上はできません",
                                                                                             Toast.LENGTH_SHORT).show();
                                                                                 } else if (point < 0) {
                                                                                     Toast.makeText(MainActivity.this,
                                                                                             "マイナス値は入力できません",
                                                                                             Toast.LENGTH_SHORT).show();


                                                                                 } else {
                                                                                     SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);

                                                                                     points = preferences.getInt("point", 0) + point;
                                                                                     WriteJson writer = new WriteJson(point+"ポイント追加しました");
                                                                                             writer.rereadVolley();
                                                                                 }


                                                                                 SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);

                                                                                 SharedPreferences.Editor editor = pref.edit();

                                                                                 editor.putInt("point", points);
                                                                                 editor.commit();

                                                                                 String str2 = Integer.toString(points);
                                                                                 TextView text = (TextView) findViewById(R.id.textView);
                                                                                 text.setText(str2);
                                                                             }
                                                                             else{
                                                                                 Toast.makeText(MainActivity.this,
                                                                                         "数字以外入力できません",
                                                                                         Toast.LENGTH_SHORT).show();
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
                                                  })
                                                  .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int whichButton) {
                                                      }
                                                  })
                                                  .show();

                                      }
                                  }
        );

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener()

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
                                                                       .setTitle("いくら分使いますか？")
                                                                       .setView(editView2)
                                                                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                           public void onClick(DialogInterface dialog, int whichButton) {
                                                                               SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
                                                                               points = preferences.getInt("point", 0);
                                                                               String str = editView2.getText().toString();
                                                                               if(cheeek(str)==true) {
                                                                                   int point = Integer.parseInt(str);
                                                                                   if (point < 0) {
                                                                                       Toast.makeText(MainActivity.this,
                                                                                               "マイナス値は入力できません",
                                                                                               Toast.LENGTH_SHORT).show();
                                                                                   } else if (point > points) {
                                                                                       Toast.makeText(MainActivity.this,
                                                                                               "ポイント残高を上回っての使用はできません",
                                                                                               Toast.LENGTH_SHORT).show();
                                                                                   } else {
                                                                                       points = points - point;
                                                                                   }


                                                                                   SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);

                                                                                   SharedPreferences.Editor editor = pref.edit();

                                                                                   editor.putInt("point", points);
                                                                                   editor.commit();

                                                                                   String str2 = Integer.toString(points);
                                                                                   TextView text = (TextView) findViewById(R.id.textView);
                                                                                   text.setText(str2);

                                                                               }else{
                                                                                   Toast.makeText(MainActivity.this,
                                                                                           "数字以外入力できません",
                                                                                           Toast.LENGTH_SHORT).show();
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
                                                   })
                                                   .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                                       public void onClick(DialogInterface dialog, int whichButton) {
                                                       }
                                                   })
                                                   .show();

                                       }
                                   }
        );






        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {



               // Uri uri = Uri.parse("http://edu3.te.kumamoto-nct.ac.jp:8088/~te14morita/APIpra/map.html");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
            }});


        Button button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }});
















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
    public boolean check(){


        int i;
        for (i = 0; i < x; i = i + 4) {
            if ((box[i] <= lat) && (box[i + 1] >= lat) &&
                    (box[i + 2] <= lon) && (box[i + 3] >= lon)) {
                if(((Long)System.currentTimeMillis()/1000-logseconds)>=5){
                    logseconds=System.currentTimeMillis()/1000;
                    SharedPreferences preferences2 = getSharedPreferences("settings", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putLong("minu",logseconds);
                    editor.commit();
                    return true;
                }
                else {
                    return  false;
                }
            }
        }
        return false;
    }

public boolean cheeek(String str){
    try{
        int point = Integer.parseInt(str);
    }catch (NumberFormatException e){
        return false;
    }
    return true;
}



    @Override
    public void onLocationChanged(Location location) {
        // 緯度の表示
      // TextView tv_lat = (TextView) findViewById(R.id.text_viewb);
       // tv_lat.setText("Latitude:"+location.getLatitude());

        // 経度の表示
       //TextView tv_lng = (TextView) findViewById(R.id.text_viewc);
        //tv_lng.setText("Latitude:"+location.getLongitude());

        lat =location.getLatitude();
        lon=location.getLongitude();


    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}