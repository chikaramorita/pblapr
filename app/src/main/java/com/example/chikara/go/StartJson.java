package com.example.chikara.go;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ubuntu on 17/11/17.
 */

public class StartJson {
    String logdata = "";
   public int my_num;
    //String explanation = "";
    JSONObject json_data = null;
    //String latitude;
    //String longitude;

    private CallBackTask callbacktask;

    StartJson(String log){//,String exp,String lat,String lng){
        //this.name = n;
        //this.explanation = exp;
        //this.latitude = lat;
        //this.longitude = lng;
        this.logdata=log;
    }

    public void rereadVolley() {

        //サーバーのアドレス任意
        String POST_URL="http://edu3.te.kumamoto-nct.ac.jp:8088/~te14morita/pbl/connection_write2.php";

        //queue
        RequestQueue getQueue= Volley.newRequestQueue(go.getAppContext());
        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        StringRequest mRequest = new StringRequest(Request.Method.POST,POST_URL,

                // 通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      my_num=Integer.parseInt(response);
                        Log.i("success","通信成功！返答:"+response);
                        callbacktask.CallBack(response);

                    }
                },

                // 通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("erroooor","通信に失敗しました2。"+error);
                    }
                }
        ){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("log",logdata);
                return params;
            }
        };

        getQueue.add(mRequest);


    }

    public void setOnCallBack(StartJson.CallBackTask _cbj) {
        callbacktask = _cbj;
    }

    public static class CallBackTask {
        public void CallBack(String response) {
        }
    }

}
