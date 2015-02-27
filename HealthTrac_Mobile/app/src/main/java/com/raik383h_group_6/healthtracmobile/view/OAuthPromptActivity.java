package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.Navigator;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthPromptPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_oauth_prompt)
public class OAuthPromptActivity extends RoboActivity {

    private OAuthPromptPresenter presenter;
    @Inject
    private PresenterFactory presenterFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        Navigator nav = new Navigator(this);
        presenter = presenterFactory.create(resources, nav, this);
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



}
