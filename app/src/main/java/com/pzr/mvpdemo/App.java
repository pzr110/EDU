package com.pzr.mvpdemo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "b51f7e9327829a896ede818cb0c81b45");
        CrashReport.initCrashReport(getApplicationContext(), "22a80f3ade", true);
    }
}
