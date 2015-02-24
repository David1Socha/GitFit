package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.AuthenticationPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_authentication)
public class AuthenticationActivity extends RoboActivity {

    @Inject
    AuthenticationPresenter presenter;
    @Inject
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize(accountService, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickSignIn(View v) {
        presenter.onClickSignIn();
    }

    public void onClickCreateAccount(View v) {
        presenter.onClickCreateAccount();
    }

    public void toastShort(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
