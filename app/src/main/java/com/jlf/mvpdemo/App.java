package com.jlf.mvpdemo;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "b51f7e9327829a896ede818cb0c81b45");
    }
}
