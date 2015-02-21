package com.raik383h_group_6.healthtracmobile.service;

public interface IOAuthServiceAdapterFactory {
    public IOAuthServiceAdapter buildFacebookOAuthServiceAdapter(String apiKey, String apiSecret, String callback);
    public IOAuthServiceAdapter buildTwitterOAuthServiceAdapter(String apiKey, String apiSecret, String callback);
}
