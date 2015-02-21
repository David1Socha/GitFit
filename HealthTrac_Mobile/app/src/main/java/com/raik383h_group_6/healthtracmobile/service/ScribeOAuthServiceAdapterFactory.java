package com.raik383h_group_6.healthtracmobile.service;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

public class ScribeOAuthServiceAdapterFactory implements IOAuthServiceAdapterFactory{

    @Override
    public IOAuthServiceAdapter buildFacebookOAuthServiceAdapter(String apiKey, String apiSecret, String callback) {
        Class apiClass = FacebookApi.class;
        OAuthService service = new ServiceBuilder()
                .provider(apiClass)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback(callback)
                .build();
        return new ScribeOAuthServiceAdapter(service);
    }

    @Override
    public IOAuthServiceAdapter buildTwitterOAuthServiceAdapter(String apiKey, String apiSecret, String callback) {
        Class apiClass = TwitterApi.class;
        OAuthService service = new ServiceBuilder()
                .provider(apiClass)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback(callback)
                .build();
        return new ScribeOAuthServiceAdapter(service);
    }
}
