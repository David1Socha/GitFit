package com.raik383h_group_6.healthtracmobile.service.oauth;

import com.raik383h_group_6.healthtracmobile.model.Token;

import java.util.concurrent.ExecutionException;

public interface IAsyncOAuthService {
    Token getAccessToken(Token requestToken, String verifier) throws InterruptedException, ExecutionException;
    String getAuthorizationUrl(final Token requestToken) throws InterruptedException, ExecutionException;
    String getVerifierName();
    Token getRequestToken() throws InterruptedException, ExecutionException;
}
