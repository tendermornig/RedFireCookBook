package com.hnqcgc.redfirecookbook;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class RedFireCookBookApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
