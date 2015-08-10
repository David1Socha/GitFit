package com.raik383h_group_6.healthtracmobile.service.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.oauth.OAuthService;

public class FacebookScribeOAuthServiceAdapter extends ScribeOAuthServiceAdapter {
    private static final Class API_CLASS = FacebookApi.class;
    private static final String API_KEY = "REDACTED",
            API_SECRET = "REDACTED",
            VERIFIER_NAME = "code";

    public FacebookScribeOAuthServiceAdapter() {
        OAuthService oAuthService = oAuthService = new ServiceBuilder()
                .provider(API_CLASS)
                .apiKey(API_KEY)
                .apiSecret(API_SECRET)
                .callback(DUMMY_CALLBACK)
                .build();
        setService(oAuthService);
    }

    @Override
    public String getVerifierName() {
        return VERIFIER_NAME;
    }

    public void clearCache() { }
}
