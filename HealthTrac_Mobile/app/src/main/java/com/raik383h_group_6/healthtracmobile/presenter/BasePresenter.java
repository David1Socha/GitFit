package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;

public class BasePresenter<T> {
    private T activity;

    protected T getView() {
        return activity;
    }

    protected BasePresenter() {}

    public void initializeWith(T activity) {
        this.activity = activity;
    }
}
