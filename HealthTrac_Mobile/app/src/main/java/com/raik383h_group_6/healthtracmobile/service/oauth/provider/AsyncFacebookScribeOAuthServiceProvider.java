package com.raik383h_group_6.healthtracmobile.service.oauth.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.oauth.AsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.FacebookScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;

public class AsyncFacebookScribeOAuthServiceProvider implements Provider<IAsyncOAuthService> {
    @Override
    public IAsyncOAuthService get() {
        return new AsyncOAuthService(new FacebookScribeOAuthServiceAdapter());
    }
}
