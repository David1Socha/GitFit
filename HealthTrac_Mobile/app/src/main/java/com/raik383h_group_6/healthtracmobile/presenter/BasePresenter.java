package com.raik383h_group_6.healthtracmobile.presenter;

public class BasePresenter<T> {
    private T view;

    protected T getView() {
        return view;
    }

    protected BasePresenter() {}

    public void initializeWith(T view) {
        this.view = view;
    }
}
