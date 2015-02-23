package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.AuthenticationPresenter;

import roboguice.inject.ContentView;

@ContentView(R.layout.activity_authentication)
public class AuthenticationActivity extends Activity {

    @Inject
    AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AuthenticationPresenter.SIGN_IN:
                    Log.d("Result", "Signed in");
                    break;
                case AuthenticationPresenter.CREATE_ACCOUNT:
                    Log.d("Result", "Created account");
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickSignIn(View v) {
        presenter.onClickSignIn();
    }

    public void onClickCreateAccount(View v) {
        //TODO
    }
}
