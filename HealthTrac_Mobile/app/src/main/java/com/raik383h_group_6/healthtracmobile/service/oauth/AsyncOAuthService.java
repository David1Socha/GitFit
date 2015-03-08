package com.raik383h_group_6.healthtracmobile.service.oauth;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.model.Token;

import java.util.concurrent.ExecutionException;

public class AsyncOAuthService implements IAsyncOAuthService {

    private IOAuthService service;

    public AsyncOAuthService(IOAuthService service) {
        this.service = service;
    }

    @Override
    public Token getAccessToken(final Token requestToken, final String verifier) throws ExecutionException, InterruptedException {
        return (new AsyncTask<Void, Void, Token>() {
            @Override
            protected Token doInBackground(Void... params) {
                return service.getAccessToken(requestToken, verifier);
            }
        }).execute().get();
    }

    @Override
    public String getAuthorizationUrl(final Token requestToken) throws ExecutionException, InterruptedException {
        return (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return service.getAuthorizationUrl(requestToken);
            }

        }).execute().get();
    }

    @Override
    public String getVerifierName() {
        return service.getVerifierName();
    }

    @Override
    public Token getRequestToken() throws InterruptedException, ExecutionException {
        return (new AsyncTask<Void, Void, Token>() {
            @Override
            protected Token doInBackground(Void... params) {
                try { //OAuth 2.0 doesn't use request tokens...
                    return service.getRequestToken();
                } catch (UnsupportedOperationException e) {
                    return null;
                }
            }
        }).execute().get();
    }
}
