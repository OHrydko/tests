package com.example.test;

import android.app.Application;

import com.example.test.di.Component;
import com.example.test.di.DaggerComponent;
import com.example.test.di.RetrofitModule;

public class App extends Application {

    private static Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerComponent.builder()
                .application(this)
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public static Component getComponent() {
        return component;
    }
}

