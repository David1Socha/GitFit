package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void onClickLoginFacebook(View v) {
        presenter.onClickLoginFacebook();
    }

    public void onClickLoginTwitter(View v) {
        presenter.onClickLoginTwitter();
    }



}
