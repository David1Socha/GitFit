package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    protected abstract BaseView getView();
    protected abstract IActivityNavigator getNav();

    public void onClickMenuLogout() {
        getView().clearPrefs();
        getNav().openMain();
    }
}
