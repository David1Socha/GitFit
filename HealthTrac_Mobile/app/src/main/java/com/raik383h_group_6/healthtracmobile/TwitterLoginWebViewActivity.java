package com.raik383h_group_6.healthtracmobile;

import org.scribe.builder.api.TwitterApi;

public class TwitterLoginWebViewActivity extends LoginWebViewActivity {

    //TODO obfuscate secrets?
    private static final Class API_CLASS = TwitterApi.class;
    private static final String API_KEY = "fHG53L9zDOTltJ77JPjFGzxf8",
            API_SECRET = "QbX7YXFiZb49HQP0jz0H72pKp5pBUEgJuJBswIroh29NjUrfXU",
            VERIFIER_NAME="oauth_verifier";

    @Override
    protected void setOAuthFields() {
        setApiClass(API_CLASS);
        setApiKey(API_KEY);
        setApiSecret(API_SECRET);
    }

    @Override
    protected String getVerifierName() {
        return VERIFIER_NAME;
    }
}
