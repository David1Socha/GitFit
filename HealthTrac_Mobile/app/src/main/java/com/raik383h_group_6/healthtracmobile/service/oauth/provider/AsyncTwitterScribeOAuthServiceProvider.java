package com.raik383h_group_6.healthtracmobile.service.oauth.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.TwitterScribeOAuthServiceAdapter;

public class AsyncTwitterScribeOAuthServiceProvider implements Provider<IAsyncOAuthService> {
    @Override
    public IAsyncOAuthService get() {
        return new AsyncOAuthService(new TwitterScribeOAuthServiceAdapter());
    }
}
