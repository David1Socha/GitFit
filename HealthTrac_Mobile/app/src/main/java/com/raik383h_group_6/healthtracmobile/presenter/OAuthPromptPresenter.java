package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthPromptActivity;

public class OAuthPromptPresenter {
    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;

    private OAuthPromptActivity view;
    private IResources resources;
    private IActivityNavigator nav;

    @Inject
    public OAuthPromptPresenter( @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted OAuthPromptActivity view) {
        this.resources = resources;
        this.view = view;
        this.nav = nav;
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (extras != null) {
            String accessToken = extras.getString(resources.getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(resources.getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(resources.getString(R.string.EXTRA_PROVIDER));
            nav.finishOAuthPromptWithInfo(accessToken, accessSecret, provider);
        } else {
            nav.finishOAuthPromptInShame();
        }
    }

    public void onClickLoginTwitter() {
        nav.openOAuthBrowser(resources.getString(R.string.PROVIDER_TWITTER), TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        nav.openOAuthBrowser(resources.getString(R.string.PROVIDER_FACEBOOK), FB_LOGIN_REQ);
    }

}
