package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            SIGN_IN = 2;
    private AuthenticationActivity view;

    public void initialize(AuthenticationActivity view) {
        this.view = view;
    }

    public void onClickSignIn() {
        Intent intent = new Intent(view, OAuthPromptActivity.class);
        view.startActivityForResult(intent, SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SIGN_IN:
                    Log.d("Result", "Signed in");
                    break;
                case CREATE_ACCOUNT:
                    Log.d("Result", "Created account");
                    break;
                default:
                    break;
            }
        }
    }
}
