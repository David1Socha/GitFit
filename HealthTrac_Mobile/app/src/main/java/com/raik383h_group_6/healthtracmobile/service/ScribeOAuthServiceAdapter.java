package com.raik383h_group_6.healthtracmobile.service;
import com.raik383h_group_6.healthtracmobile.model.Token;

import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public abstract class ScribeOAuthServiceAdapter implements IOAuthServiceAdapter {
    private OAuthService service;

    public Token getRequestToken() {
        org.scribe.model.Token scribeToken = service.getRequestToken();
        Token reqToken = scribeToken == null ? null : new Token(scribeToken.getToken(), scribeToken.getSecret());
        return reqToken;
    }

    public void setService(OAuthService service) {
        this.service = service;
    }

    public String getAuthorizationUrl(Token t) {
        org.scribe.model.Token scribeToken = t == null ? null
                : new org.scribe.model.Token(t.getToken(), t.getSecret());
        return service.getAuthorizationUrl(scribeToken);
    }

    public Token getAccessToken(Token requestToken, String verifier) {
        org.scribe.model.Verifier scribeVerifier = new Verifier(verifier);
        org.scribe.model.Token scribeReqToken = requestToken == null ? null
                : new org.scribe.model.Token(requestToken.getToken(), requestToken.getSecret());
        org.scribe.model.Token scribeAccToken = service.getAccessToken(scribeReqToken, scribeVerifier);
        Token accToken = new Token(scribeAccToken.getToken(), scribeAccToken.getSecret());
        return accToken;
    }
}
