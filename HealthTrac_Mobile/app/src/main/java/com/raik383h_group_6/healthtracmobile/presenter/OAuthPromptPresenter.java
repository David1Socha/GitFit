package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public class OAuthPromptPresenter extends BasePresenter{

    private IActivityNavigator nav;
    private BaseView view;

    @Inject
    public OAuthPromptPresenter( @Assisted IActivityNavigator nav, @Assisted BaseView view) {
        this.nav = nav;
        this.view = view;
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (extras != null) {
            String accessToken = extras.getString(view.getResource(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(view.getResource(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(view.getResource(R.string.EXTRA_PROVIDER));
            nav.finishOAuthPromptWithInfo(accessToken, accessSecret, provider);
        } else {
            nav.finishOAuthPromptInShame();
        }
    }

    @Override
    protected AccessGrant getGrant() {
        return null;
    }

    public void onClickLoginTwitter() {
        nav.openOAuthBrowser(view.getResource(R.string.PROVIDER_TWITTER), RequestCodes.TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        nav.openOAuthBrowser(view.getResource(R.string.PROVIDER_FACEBOOK), RequestCodes.FB_LOGIN_REQ);
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    @Override
    protected BaseView getView() {
        return view;
    }

}
