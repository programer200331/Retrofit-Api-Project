package com.example.secondapiproject.controllers;

import android.app.Application;

public class AppController extends Application {

    private static AppController instance;

    public static synchronized AppController getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
