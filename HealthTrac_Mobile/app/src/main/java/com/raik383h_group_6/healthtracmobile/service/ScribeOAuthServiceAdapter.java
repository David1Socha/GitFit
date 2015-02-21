package com.raik383h_group_6.healthtracmobile.service;

import com.raik383h_group_6.healthtracmobile.model.Token;

import org.scribe.oauth.OAuthService;

public class ScribeOAuthServiceAdapter implements IOAuthServiceAdapter {
    private OAuthService service;

    public ScribeOAuthServiceAdapter(OAuthService service) {
        this.service = service;
    }

    public Token getRequestToken() {
        org.scribe.model.Token scribeToken = service.getRequestToken();
        Token reqToken = new Token(scribeToken.getToken(), scribeToken.getSecret());
        return  reqToken;
    }
}
