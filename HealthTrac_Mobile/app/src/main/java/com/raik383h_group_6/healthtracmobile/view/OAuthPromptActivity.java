package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthPromptPresenter;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

public class OAuthPromptActivity extends RoboActivity {

    @Inject
    private OAuthPromptPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_prompt);
        presenter.initialize(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickLoginFacebook() {
        presenter.onClickLoginFacebook();
    }

    public void onClickLoginTwitter() {
        presenter.onClickLoginTwitter();
    }

    public void finishWithOAuthInfos(String accessToken, String accessSecret, String provider) {
        Intent data = new Intent();
        data.putExtra(getString(R.string.EXTRA_ACCESS_SECRET), accessSecret);
        data.putExtra(getString(R.string.EXTRA_ACCESS_TOKEN), accessToken);
        data.putExtra(getString(R.string.EXTRA_PROVIDER), provider);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    public void finishInShame() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
