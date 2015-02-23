package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.RegisterUserPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends RoboActivity {
    @Inject
    private RegisterUserPresenter presenter;
    @Inject private FacebookService facebookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize(facebookService, this);
        presenter.onCreate();
    }
}
