package com.raik383h_group_6.healthtracmobile.service.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

/**
 * Created by David on 2/21/2015.
 */
public class TwitterScribeOAuthServiceAdapter extends ScribeOAuthServiceAdapter {
    private static final Class API_CLASS = TwitterApi.SSL.class;
    private static final String API_KEY = "REDACTED",
            API_SECRET = "REDACTED",
            VERIFIER_NAME="oauth_verifier";

    public TwitterScribeOAuthServiceAdapter() {
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
}
