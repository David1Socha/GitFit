package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            OAUTH_SIGN_IN = 2,
            OAUTH_CREATE_ACCOUNT = 3;
    private AuthenticationActivity view;

    public void initialize(AuthenticationActivity view) {
        this.view = view;
    }

    public void onClickSignIn() {
        Intent intent = new Intent(view, OAuthPromptActivity.class);
        view.startActivityForResult(intent, OAUTH_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OAUTH_SIGN_IN:
                    signIn();
                    finishWithUser();
                    break;
                case OAUTH_CREATE_ACCOUNT:
                    createAccount();
                    break;
                case CREATE_ACCOUNT:
                    finishWithUser();
                    break;
                default:
                    break;
            }
        }
    }

    public void finishWithUser() {
        //TODO
    }

    public void signIn() {
        //TODO
    }

    public void createAccount() {
        //TODO
    }
}
