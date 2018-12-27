package com.example.lianxizhoukao2;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * date:2018.12.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
