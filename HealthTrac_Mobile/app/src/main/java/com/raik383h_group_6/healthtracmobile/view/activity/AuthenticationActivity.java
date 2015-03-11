package com.raik383h_group_6.healthtracmobile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.AuthenticationPresenter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_authentication)
public class AuthenticationActivity extends BaseActivity implements AuthenticationView {

    AuthenticationPresenter presenter;
    @Inject
    PresenterFactory presenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data == null ? null : data.getExtras();
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    public void onClickSignIn(View v) {
        presenter.onClickSignIn();
    }

    public void onClickCreateAccount(View v) {
        presenter.onClickCreateAccount();
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
