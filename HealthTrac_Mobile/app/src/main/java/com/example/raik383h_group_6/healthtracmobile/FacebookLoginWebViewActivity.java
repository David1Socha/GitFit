package com.example.raik383h_group_6.healthtracmobile;

import org.scribe.builder.api.FacebookApi;

public class FacebookLoginWebViewActivity extends LoginWebViewActivity{

    //TODO obfuscate secrets?
    private static final Class API_CLASS = FacebookApi.class;
    private static final String CALLBACK_URL = "http://www.facebook.com",
            API_KEY = "512959195512759",
            API_SECRET = "e09e263961535252750c19eb77053438";

    @Override
    protected void setOAuthFields() {
        setApiClass(API_CLASS);
        setApiKey(API_KEY);
        setApiSecret(API_SECRET);
        setCallbackUrl(CALLBACK_URL);
    }
}
