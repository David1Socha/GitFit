package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;

public class OAuthPromptPresenter {
    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;

    private OAuthPromptActivity view;
    private IResources resources;

    @Inject
    public OAuthPromptPresenter( @Assisted IResources resources, @Assisted OAuthPromptActivity view) {
        this.resources = resources;
        this.view = view;
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (extras != null) {
            String accessToken = extras.getString(resources.getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(resources.getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(resources.getString(R.string.EXTRA_PROVIDER));
            finishWithOAuthInfos(accessToken, accessSecret, provider);
        } else {
            finishInShame();
        }
    }

    public void onClickLoginTwitter() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(resources.getString(R.string.EXTRA_PROVIDER), resources.getString(R.string.PROVIDER_TWITTER));
        view.startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void onClickLoginFacebook() {
        Intent intent = new Intent(view, OAuthBrowserActivity.class);
        intent.putExtra(resources.getString(R.string.EXTRA_PROVIDER), resources.getString(R.string.PROVIDER_FACEBOOK));
        view.startActivityForResult(intent, FB_LOGIN_REQ);
    }

    private void finishWithOAuthInfos(String accessToken, String accessSecret, String provider) {
        Intent data = new Intent();
        data.putExtra(resources.getString(R.string.EXTRA_ACCESS_SECRET), accessSecret);
        data.putExtra(resources.getString(R.string.EXTRA_ACCESS_TOKEN), accessToken);
        data.putExtra(resources.getString(R.string.EXTRA_PROVIDER), provider);
        view.setResult(Activity.RESULT_OK, data);
        view.finish();
    }

    private void finishInShame() {
        view.setResult(Activity.RESULT_CANCELED);
        view.finish();
    }
}
