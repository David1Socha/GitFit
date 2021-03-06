package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;

import java.util.concurrent.ExecutionException;

public class AsyncAccountService implements IAsyncAccountService {

    private final AccountService service;

    @Inject
    public AsyncAccountService(AccountService service) {
        this.service = service;
    }

    @Override
    public void registerAccountAsync(final UserLogin userLogin) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.register(userLogin);
                } catch (Exception ex) {
                    errs[0] = ex;
                }
                return null;
            }
        }.execute().get();
        if (errs[0] != null) {
            throw errs[0];
        }
    }

    @Override
    public AccessGrant loginAsync(Credentials credentials) throws InterruptedException, ExecutionException {
        return (new AsyncTask<Credentials, Void, AccessGrant>() {

            @Override
            protected AccessGrant doInBackground(Credentials... params) {
                try {
                    return service.logIn(params[0]);
                } catch (Exception e) {
                    return null;
                }

            }
        }).execute(credentials).get();
    }
}
