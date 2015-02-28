package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;

import java.util.concurrent.ExecutionException;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            OAUTH_TO_SIGN_IN = 2,
            OAUTH_TO_CREATE_ACCOUNT = 3;
    private AuthenticationActivity view;
    private AccountService accountService;
    private String accessToken, accessSecret, provider;
    private IResources resources;
    private ActivityNavigator nav;

    @Inject
    public AuthenticationPresenter(@Assisted AccountService accountService, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted AuthenticationActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.accountService = accountService;
        this.view = view;
    }

    public void onClickSignIn() {
        nav.openOAuthPrompt(OAUTH_TO_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OAUTH_TO_SIGN_IN:
                    updateLoginInfo(extras);
                    signInAndFinish(accessToken, accessSecret, provider);
                    break;
                case OAUTH_TO_CREATE_ACCOUNT:
                    updateLoginInfo(extras);
                    createAccount(accessToken, accessSecret, provider);
                    break;
                case CREATE_ACCOUNT:
                    signInAndFinish(accessToken, accessSecret, provider);
                    break;
                default:
                    break;
            }
        }
    }

    private void updateLoginInfo(Bundle extras) {
        accessToken = extras.getString(resources.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = extras.getString(resources.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = extras.getString(resources.getString(R.string.EXTRA_PROVIDER));
    }

    private void signInAndFinish(String accessToken, String accessSecret, String provider) {

        AccessGrant grant = signIn(accessToken, accessSecret, provider);
        if (grant != null) {
            nav.finishWithAccessGrant(grant);
        }
    }

    public AccessGrant signIn(String accessToken, String accessSecret, String provider) {
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        AccessGrant grant = null;
        try {
            grant = loginAsync(credentials);
        } catch (InterruptedException | ExecutionException ignored) {}
        if (grant == null) {
            view.displayMessage(resources.getString(R.string.sign_in_error));
        } else {
            view.displayMessage(resources.getString(R.string.welcome_user, grant.getUserName()));
        }
        return grant;
    }

    private AccessGrant loginAsync(Credentials credentials) throws ExecutionException, InterruptedException {
        return (new AsyncTask<Credentials, Void, AccessGrant>() {

            @Override
            protected AccessGrant doInBackground(Credentials... params) {
                try {
                    return accountService.logIn(params[0]);
                } catch (Exception e) {
                    return null;
                }

            }
        }).execute(credentials).get();
    }

    public void onClickCreateAccount() {
        nav.openOAuthPrompt(OAUTH_TO_CREATE_ACCOUNT);
    }

    public void createAccount(String accessToken, String accessSecret, String provider) {
        nav.openRegisterUser(accessToken, accessSecret, provider, CREATE_ACCOUNT);
    }
}
