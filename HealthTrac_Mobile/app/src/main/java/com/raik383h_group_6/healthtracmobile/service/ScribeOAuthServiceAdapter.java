package com.raik383h_group_6.healthtracmobile.service;

import org.scribe.oauth.OAuthService;

public class ScribeOAuthServiceAdapter implements IOAuthServiceAdapter {
    private OAuthService service;
    
    public ScribeOAuthServiceAdapter(OAuthService service) {
        this.service = service;
    }
}
