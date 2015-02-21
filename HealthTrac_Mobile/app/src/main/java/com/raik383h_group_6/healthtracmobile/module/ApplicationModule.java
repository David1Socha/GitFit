package com.raik383h_group_6.healthtracmobile.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.presenter.BrowserLoginPresenter;
import com.raik383h_group_6.healthtracmobile.service.FacebookScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.TwitterScribeOAuthServiceAdapter;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOAuthServiceAdapter.class).annotatedWith(Names.named("Facebook")).to(FacebookScribeOAuthServiceAdapter.class);
        bind(IOAuthServiceAdapter.class).annotatedWith(Names.named("Twitter")).to(TwitterScribeOAuthServiceAdapter.class);
    }
}
