package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.RegisterUserPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends RoboActivity {
    @Inject
    private RegisterUserPresenter presenter;
    @Inject
    FacebookService facebookService;
    @InjectView(R.id.button_create_account)
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize(facebookService, this);
        presenter.onCreate();
        createAccountButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickCreateAccount();
            }
        });
    }
}
