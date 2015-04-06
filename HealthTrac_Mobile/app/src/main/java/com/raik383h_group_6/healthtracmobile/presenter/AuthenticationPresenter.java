package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import java.util.concurrent.ExecutionException;

public class AuthenticationPresenter extends BasePresenter{
    private AuthenticationView view;
    private IAsyncAccountService accountService;
    private String accessToken, accessSecret, provider;
    private IActivityNavigator nav;

    @Inject
    public AuthenticationPresenter(IAsyncAccountService accountService, @Assisted IActivityNavigator nav, @Assisted AuthenticationView view) {
        this.nav = nav;
        this.accountService = accountService;
        this.view = view;
    }

    public void onClickSignIn() {
        nav.openOAuthPrompt(RequestCodes.OAUTH_TO_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.OAUTH_TO_SIGN_IN:
                    updateLoginInfo(extras);
                    signInAndFinish(accessToken, accessSecret, provider);
                    break;
                case RequestCodes.OAUTH_TO_CREATE_ACCOUNT:
                    updateLoginInfo(extras);
                    createAccount(accessToken, accessSecret, provider);
                    break;
                case RequestCodes.CREATE_ACCOUNT:
                    signInAndFinish(accessToken, accessSecret, provider);
                    break;
                default:
                    break;
            }
        }
    }

    private void updateLoginInfo(Bundle extras) {
        accessToken = extras.getString(view.getResource(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = extras.getString(view.getResource(R.string.EXTRA_ACCESS_SECRET));
        provider = extras.getString(view.getResource(R.string.EXTRA_PROVIDER));
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
            view.displayMessage(view.getResource(R.string.sign_in_error));
        } else {
            view.displayMessage(view.getResource(R.string.welcome_user, grant.getUserName()));
        }
        return grant;
    }

    public void onClickCreateAccount() {
        nav.openOAuthPrompt(RequestCodes.OAUTH_TO_CREATE_ACCOUNT);
    }

    private void createAccount(String accessToken, String accessSecret, String provider) {
        nav.openCreateUser(accessToken, accessSecret, provider, RequestCodes.CREATE_ACCOUNT);
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
