package com.raik383h_group_6.healthtracmobile.view;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.RegisterUserPresenter;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends RoboActivity {
    @Inject
    private RegisterUserPresenter presenter;
}
