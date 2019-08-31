package com.petits_raids.yunbiannews;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.petits_raids.yunbiannews.support.utils.NameUtils;
import com.squareup.leakcanary.LeakCanary;

public class YunBianApplication extends Application {

    private static Context mContext;
    private static boolean NightMode;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        SharedPreferences preferences = getContext().getSharedPreferences(NameUtils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        preferences.getBoolean(NameUtils.NIGHT_MODE_NAME, false);
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setNightMode(boolean mode) {
        NightMode = mode;
    }

    public static boolean isNightMode() {
        return NightMode;
    }
}
