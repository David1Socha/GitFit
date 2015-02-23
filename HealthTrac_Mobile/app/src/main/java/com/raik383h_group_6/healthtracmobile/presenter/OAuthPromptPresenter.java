package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;

public class OAuthPromptPresenter {
    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;

    private OAuthPromptActivity view;

    public void initialize(OAuthPromptActivity view) {
        this.view = view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            String accessToken = extras.getString(view.getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(view.getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(view.getString(R.string.EXTRA_PROVIDER));
            view.finishWithOAuthInfos(accessToken, accessSecret, provider);
        } else {
            view.finishInShame();
        }
    }

    public void onClickLoginTwitter() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), view.getString(R.string.PROVIDER_TWITTER));
        view.finish();
        view.startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), view.getString(R.string.PROVIDER_FACEBOOK));
        view.startActivityForResult(intent, FB_LOGIN_REQ);
    }
}
