package com.example.chikara.go;

/**
 * Created by chikara on 17/11/17.
 */
import android.app.Application;
import android.content.Context;

/**
 * Created by shigashan on 17/11/14.
 */

public class go extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        go.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return go.context;
    }
}