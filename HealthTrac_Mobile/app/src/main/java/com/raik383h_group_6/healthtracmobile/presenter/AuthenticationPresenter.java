package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            OAUTH_SIGN_IN = 2,
            OAUTH_CREATE_ACCOUNT = 3;
    private AuthenticationActivity view;
    private AccountService accountService;

    public void initialize(AccountService accountService, AuthenticationActivity view) {
        this.accountService = accountService;
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
                    signInAndFinish(data);
                    break;
                case OAUTH_CREATE_ACCOUNT:
                    createAccount();
                    break;
                case CREATE_ACCOUNT:
                    signInAndFinish(data);
                    break;
                default:
                    break;
            }
        }
    }

    private void signInAndFinish(Intent data) {
        String accessToken = data.getStringExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN));
        String accessSecret = data.getStringExtra(view.getString(R.string.EXTRA_ACCESS_SECRET));
        String provider = data.getStringExtra(view.getString(R.string.EXTRA_PROVIDER));
        signIn(accessToken, accessSecret, provider);
        finishWithAccessGrant();
    }

    public void finishWithAccessGrant() {
        //TODO
    }

    public void signIn(String accessToken, String accessSecret, String provider) {
        //TODO
        view.toast("Access token is " + accessToken);
    }

    public void createAccount() {
        //TODO
    }
}
