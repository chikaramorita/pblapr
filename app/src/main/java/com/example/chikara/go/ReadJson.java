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
                        //今回は処理なし
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
                params.put("mynum","5");//Integer.toString(my_num));
                return params;
            }
        };

        getQueue.add(mRequest);

    }

    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }

    public void setData(JSONArray res_data){
        this.data = res_data;
        callbacktask.CallBack(this.data);
    }

    public static class CallBackTask {
        public void CallBack(JSONArray result) {
        }
    }


}