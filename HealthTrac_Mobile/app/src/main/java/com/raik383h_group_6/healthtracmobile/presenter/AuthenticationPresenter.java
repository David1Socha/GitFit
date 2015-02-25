package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

import java.util.concurrent.ExecutionException;

import retrofit.RetrofitError;

public class AuthenticationPresenter {
    public static final int CREATE_ACCOUNT = 1,
            OAUTH_TO_SIGN_IN = 2,
            OAUTH_TO_CREATE_ACCOUNT = 3;
    private AuthenticationActivity view;
    private AccountService accountService;
    private String accessToken, accessSecret, provider;

    public void initialize(AccountService accountService, AuthenticationActivity view) {
        this.accountService = accountService;
        this.view = view;
    }

    public void onClickSignIn() {
        Intent intent = new Intent(view, OAuthPromptActivity.class);
        view.startActivityForResult(intent, OAUTH_TO_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OAUTH_TO_SIGN_IN:
                    saveLoginInfo(data);
                    signInAndFinish(accessToken, accessSecret, provider);
                    break;
                case OAUTH_TO_CREATE_ACCOUNT:
                    saveLoginInfo(data);
                    createAccount(accessToken, accessSecret, provider);
                    break;
                default:
                    break;
            }
        }
    }

    private void saveLoginInfo(Intent data) {
        accessToken = data.getStringExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = data.getStringExtra(view.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = data.getStringExtra(view.getString(R.string.EXTRA_PROVIDER));
    }

    private void signInAndFinish(String accessToken, String accessSecret, String provider) {

        AccessGrant grant = signIn(accessToken, accessSecret, provider);
        if (grant != null) {
            finishWithAccessGrant(grant);
        }
    }

    public void finishWithAccessGrant(AccessGrant g) {
        Intent data = new Intent(view, RegisterUserActivity.class);
        data.putExtra(view.getString(R.string.EXTRA_ACCESS_GRANT), g);
        Log.d("davidsocha", g.getAccessToken());
        //view.finish();
    }

    public AccessGrant signIn(String accessToken, String accessSecret, String provider) {
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        AccessGrant grant = null;
        try {
            grant = loginAsync(credentials);
        } catch (InterruptedException | ExecutionException ignored) {}
        if (grant == null) {
            view.displayMessage("Error signing in.");
        } else {
            view.displayMessage("Welcome, " + grant.getUserName());
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
        Intent intent = new Intent(view, OAuthPromptActivity.class);
        view.startActivityForResult(intent, OAUTH_TO_CREATE_ACCOUNT);
    }

    public void createAccount(String accessToken, String accessSecret, String provider) {
        Intent intent = new Intent(view, RegisterUserActivity.class);
        intent.putExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN), accessToken);
        intent.putExtra(view.getString(R.string.EXTRA_ACCESS_SECRET), accessSecret);
        intent.putExtra(view.getString(R.string.EXTRA_PROVIDER), provider);
        view.startActivity(intent);
        signInAndFinish(accessToken, accessSecret, provider);
    }
}
