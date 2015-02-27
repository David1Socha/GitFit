package com.raik383h_group_6.healthtracmobile.application;

import android.app.Application;

import roboguice.RoboGuice;

public class CustomApplication extends Application {
    public void onCreate() {
        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);
    }
}