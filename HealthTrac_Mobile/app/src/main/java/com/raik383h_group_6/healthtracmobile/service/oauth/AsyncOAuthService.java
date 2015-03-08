package com.raik383h_group_6.healthtracmobile.service.oauth;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.model.Token;

public class AsyncOAuthService implements IAsyncOAuthService {

    private IOAuthService service;

    public AsyncOAuthService(IOAuthService service) {
        this.service = service;
    }

    @Override
    public String getAccessTokenAsync(final String requestToken, final String verifier) {
        (new AsyncTask<Void, Void, Token>() {
            @Override
            protected Token doInBackground(Void... params) {
                return service.getAccessToken(requestToken, verifier);
            }
        }).execute().get();
    }

    @Override
    public String getAuthorizationUrl() {
        return null;
    }
}
