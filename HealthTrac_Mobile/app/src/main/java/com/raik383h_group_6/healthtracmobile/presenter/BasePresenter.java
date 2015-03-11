package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    private BaseView view;
    protected abstract IActivityNavigator getNav();

    public void onClickMenuLogout() {
        view.setLoginEnabled(true);
        view.clearPrefs();
        getNav().openMain();
    }

    public void onClickMenuLogin() {
        view.setLoginEnabled(false);
        getNav().openAuthentication(RequestCodes.AUTH);
    }
}
