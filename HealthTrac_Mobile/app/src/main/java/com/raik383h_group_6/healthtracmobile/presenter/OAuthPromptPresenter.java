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

    private Bundle extras;
    private OAuthPromptActivity view;

    public void initialize(Bundle extras, OAuthPromptActivity view) {
        this.extras = extras;
        this.view = view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            String accessToken = extras.getString(view.getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(view.getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(view.getString(R.string.EXTRA_PROVIDER));
            finishWithOAuthInfos(accessToken, accessSecret, provider);
        } else {
            finishInShame();
        }
    }

    public void onClickLoginTwitter() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), view.getString(R.string.PROVIDER_TWITTER));
        view.startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), view.getString(R.string.PROVIDER_FACEBOOK));
        view.startActivityForResult(intent, FB_LOGIN_REQ);
    }

    private void finishWithOAuthInfos(String accessToken, String accessSecret, String provider) {
        Intent data = new Intent();
        data.putExtra(view.getString(R.string.EXTRA_ACCESS_SECRET), accessSecret);
        data.putExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN), accessToken);
        data.putExtra(view.getString(R.string.EXTRA_PROVIDER), provider);
        view.setResult(Activity.RESULT_OK, data);
        view.finish();
    }

    private void finishInShame() {
        view.setResult(Activity.RESULT_CANCELED);
        view.finish();
    }
}
