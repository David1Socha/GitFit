package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.content.IResources;

public class OAuthPromptPresenter {

    private IResources resources;
    private IActivityNavigator nav;

    @Inject
    public OAuthPromptPresenter( @Assisted IResources resources, @Assisted IActivityNavigator nav) {
        this.resources = resources;
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
        nav.openOAuthBrowser(resources.getString(R.string.PROVIDER_TWITTER), RequestCodes.TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        nav.openOAuthBrowser(resources.getString(R.string.PROVIDER_FACEBOOK), RequestCodes.FB_LOGIN_REQ);
    }

}
