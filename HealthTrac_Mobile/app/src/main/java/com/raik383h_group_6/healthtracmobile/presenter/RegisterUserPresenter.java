package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

import java.util.concurrent.ExecutionException;

public class RegisterUserPresenter {
    private RegisterUserActivity view;
    private FacebookService facebookService;
    private FacebookUser facebookUser;
    private String accessToken, accessSecret, provider;
    private AccountService accountService;

    public void initialize(FacebookService facebookService, AccountService accountService, RegisterUserActivity view) {
        this.facebookService = facebookService;
        this.view = view;
        this.accountService = accountService;
        accessToken = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_PROVIDER));
    }

    public void onCreate() {
        facebookUser = null;
        if (provider.equals(view.getString(R.string.PROVIDER_FACEBOOK))) {
            try {
                facebookUser = getFacebookUserAsync();
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }
        if (facebookUser != null) {
            //populate fields from facebook user
        }

    }

    private FacebookUser getFacebookUserAsync() throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, FacebookUser>() {
            @Override protected FacebookUser doInBackground(String... params) {
                try {
                    return facebookService.getUser(accessToken);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(accessToken).get();
    }

    public void onClickCreateAccount() {
        if (validateFields()) {
            //create account...
        } else {
            //send error msg
        }
    }

    private boolean validateFields() {
        return true;
    }

    private void registerAccountAsync(UserLogin userLogin) throws ExecutionException, InterruptedException {
            new AsyncTask<UserLogin, Void, Void>() {

               @Override
               protected Void doInBackground(UserLogin... params) {
                   accountService.register(params[0]);
                   return null;
               }
           }.execute(userLogin).get();
    }
}
