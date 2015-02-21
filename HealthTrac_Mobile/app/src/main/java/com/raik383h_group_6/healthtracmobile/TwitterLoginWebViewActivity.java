package com.raik383h_group_6.healthtracmobile;

import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapterFactory;

public class TwitterLoginWebViewActivity extends LoginWebViewActivity {

    //TODO obfuscate secrets?
    private static final String API_KEY = "fHG53L9zDOTltJ77JPjFGzxf8",
            API_SECRET = "QbX7YXFiZb49HQP0jz0H72pKp5pBUEgJuJBswIroh29NjUrfXU",
            VERIFIER_NAME="oauth_verifier";

    @Override
    protected String getVerifierName() {
        return VERIFIER_NAME;
    }


    @Override
    protected IOAuthServiceAdapter buildOAuthServiceAdapter(IOAuthServiceAdapterFactory factory) {
        return factory.buildTwitterOAuthServiceAdapter(API_KEY, API_SECRET, DUMMY_CALLBACK);
    }
}
