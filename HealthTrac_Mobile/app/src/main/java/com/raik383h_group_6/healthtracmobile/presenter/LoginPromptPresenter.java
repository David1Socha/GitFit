package com.raik383h_group_6.healthtracmobile.presenter;

import android.content.Intent;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.LoginPromptActivity;

public class LoginPromptPresenter {

    private LoginPromptActivity view;

    public void initialize(LoginPromptActivity view) {
        this.view = view;
    }

    public void oAuthCompleted(String token, String secret, String provider) {
        view.finishWithOAuthInfos(token, secret, provider);
    }

    public void loginTwitter() {
        Intent intent = new Intent(this, OAuthBrowserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), view.getString(R.string.PROVIDER_TWITTER));
        view.finish(); //TODO fixme
    }
}
