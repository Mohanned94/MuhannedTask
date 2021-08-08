package com.example.muhanned_task.utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class MyApplication extends Application {

    private static MyApplication instance;
    private SharedPreferences preferences;
    private Gson gson;

    public static Context getAppContext() {
        return MyApplication.instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }



    public synchronized SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences;
    }


    public synchronized Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }

}
