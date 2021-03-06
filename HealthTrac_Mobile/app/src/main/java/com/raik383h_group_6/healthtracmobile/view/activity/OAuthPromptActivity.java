package com.raik383h_group_6.healthtracmobile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthPromptPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_oauth_prompt)
public class OAuthPromptActivity extends BaseActivity {

    private OAuthPromptPresenter presenter;
    @Inject
    private PresenterFactory presenterFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = (data != null ? data.getExtras() : null);
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    public void onClickLoginFacebook(View v) {
        presenter.onClickLoginFacebook();
    }

    public void onClickLoginTwitter(View v) {
        presenter.onClickLoginTwitter();
    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

}
