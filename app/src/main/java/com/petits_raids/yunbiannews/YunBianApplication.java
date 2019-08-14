package com.petits_raids.yunbiannews;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class YunBianApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
