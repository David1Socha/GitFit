package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    private BaseView view;
    protected abstract IActivityNavigator getNav();

    public void onClickLogout() { // Any presenter that also stores access grant should override this to additionally wipe grant
        view.setLoginEnabled(true);
        view.setLogoutEnabled(false);
        view.clearPrefs();
    }

    public void onClickLogin() {
        view.setLogoutEnabled(true);
        getNav().openAuthentication(RequestCodes.AUTH);
    }
}
