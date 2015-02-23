package com.raik383h_group_6.healthtracmobile.service.oauth;

import com.raik383h_group_6.healthtracmobile.model.Token;

public interface IOAuthServiceAdapter {
    public Token getRequestToken();
    public String getAuthorizationUrl(Token t);
    public Token getAccessToken(Token requestToken, String verifier);
    public String getVerifierName();
    public static final String DUMMY_CALLBACK = "http://www.example.com/oauth_callback";
}
