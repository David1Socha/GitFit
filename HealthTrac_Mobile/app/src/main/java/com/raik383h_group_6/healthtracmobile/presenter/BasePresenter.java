package com.raik383h_group_6.healthtracmobile.presenter;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public abstract class BasePresenter {

    protected abstract BaseView getView();
    protected abstract IActivityNavigator getNav();
    protected abstract AccessGrant getGrant();

    public void onClickMenuLogout(Context context) {
        getView().clearPrefs();
        getNav().openMain();
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(false);
    }


}
