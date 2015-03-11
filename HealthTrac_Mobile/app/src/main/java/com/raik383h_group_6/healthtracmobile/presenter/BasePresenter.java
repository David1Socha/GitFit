package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    private BaseView view;

    public void onClickLogout() {
        view.setLoginEnabled(true);
        view.setLogoutEnabled(false);
    }

    public void onClickLogin() {
        view.setLogoutEnabled(true);
    }
}
