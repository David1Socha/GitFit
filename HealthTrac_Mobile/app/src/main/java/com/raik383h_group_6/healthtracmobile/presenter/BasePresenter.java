package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    protected abstract BaseView getView();
    protected abstract IActivityNavigator getNav();

    public void onClickMenuLogout() {
        getView().setLoginEnabled(true);
        getView().clearPrefs();
        getNav().openMain();
    }

    public void onClickMenuLogin() {
        getView().setLoginEnabled(false);
        getNav().openAuthentication(RequestCodes.AUTH);
    }
}
