package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthPromptPresenter;

public class OAuthPromptActivity extends Activity {


    @Inject
    private OAuthPromptPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_prompt);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();
            String accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(getString(R.string.EXTRA_PROVIDER));
            //presenter.login
        }
    }

    public void loginServer(String accessToken, String accessSecret, String provider) {
        //TODO
    }

    public void onClickLoginFacebook() {
        presenter.onClickLoginFacebook();
    }

    public void onClickLoginTwitter() {
        presenter.onClickLoginTwitter();
    }

}
