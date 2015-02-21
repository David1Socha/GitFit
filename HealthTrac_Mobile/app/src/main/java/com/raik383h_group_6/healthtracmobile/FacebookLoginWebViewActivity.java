package com.raik383h_group_6.healthtracmobile;

import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapterFactory;

public class FacebookLoginWebViewActivity extends LoginWebViewActivity {

    private static final String API_KEY = "512959195512759",
            API_SECRET = "e09e263961535252750c19eb77053438",
            VERIFIER_NAME = "code";

    @Override
    protected IOAuthServiceAdapter buildOAuthServiceAdapter(IOAuthServiceAdapterFactory factory) {
        return factory.buildFacebookOAuthServiceAdapter(API_KEY, API_SECRET, DUMMY_CALLBACK);
    }

    @Override
    protected String getVerifierName() {
        return VERIFIER_NAME;
    }
}
