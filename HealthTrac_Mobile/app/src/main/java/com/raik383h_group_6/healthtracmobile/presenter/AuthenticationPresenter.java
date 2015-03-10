package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;

import java.util.concurrent.ExecutionException;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            OAUTH_TO_SIGN_IN = 2,
            OAUTH_TO_CREATE_ACCOUNT = 3;
    private AuthenticationView view;
    private IAsyncAccountService accountService;
    private String accessToken, accessSecret, provider;
    private IResources resources;
    private IActivityNavigator nav;

    @Inject
    public AuthenticationPresenter(IAsyncAccountService accountService, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted AuthenticationView view) {
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

    private AccessGrant signIn(String accessToken, String accessSecret, String provider) {
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        AccessGrant grant = null;
        try {
            grant = accountService.loginAsync(credentials);
        } catch (InterruptedException | ExecutionException ignored) {}
        if (grant == null) {
            view.displayMessage(resources.getString(R.string.sign_in_error));
        } else {
            view.displayMessage(resources.getString(R.string.welcome_user, grant.getUserName()));
        }
        return grant;
    }

    public void onClickCreateAccount() {
        nav.openOAuthPrompt(OAUTH_TO_CREATE_ACCOUNT);
    }

    private void createAccount(String accessToken, String accessSecret, String provider) {
        nav.openCreateUser(accessToken, accessSecret, provider, CREATE_ACCOUNT);
    }
}
