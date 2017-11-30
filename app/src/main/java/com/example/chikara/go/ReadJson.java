package com.example.chikara.go;

/**
 * Created by ubuntu on 17/11/17.
 */

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class ReadJson{


    public ReadJson(int n){
    my_num=n;
    }
    String obdata;
    static final String BR = System.getProperty("line.separator");


    JSONArray data;
    private CallBackTask callbacktask;
    public int my_num;

    public void rereadVolley() {

        //サーバーのアドレス任意
        String GET_URL="http://edu3.te.kumamoto-nct.ac.jp:8088/~te14morita/pbl/connection.php";

        //queue
        RequestQueue getQueue= Volley.newRequestQueue(go.getAppContext());

        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        StringRequest mRequest = new StringRequest(Request.Method.GET,GET_URL,

                // 通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        obdata=Cutter(response);

                        Log.i("success","通信成功！返答:"+response);
                    }
                },

                // 通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("erroooor","通信に失敗しました。"+error);
                    }
                }
        ){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
<<<<<<< HEAD
                params.put("mynum","1");//Integer.toString(my_num));
=======
                params.put("mynum",Integer.toString(my_num));
>>>>>>> 62ab1cd00a92e8d71b29dc59f4d63e691a8d93b5
                return params;
            }
        };

        getQueue.add(mRequest);

    }
public String cutter (String responce){
    String[] x=responce.split(",",0);
    String momo="null";
    for(int i=0;i<x.length;i++){
        momo=x[i]+BR;
    }
}
    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }


    public static class CallBackTask {
        public void CallBack(String responce) {
        }
    }
    public String Cutter(String ob){
        String w = null;
        String [] x = ob.split(",",0);
        for(int i=0;i<x.length;i++){
            w=w+x[i]+BR;
        }

        return w;
    }


}