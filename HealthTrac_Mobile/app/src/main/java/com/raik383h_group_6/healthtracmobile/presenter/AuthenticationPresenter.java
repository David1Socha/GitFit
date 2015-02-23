package com.raik383h_group_6.healthtracmobile.presenter;

import android.content.Intent;

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
}
