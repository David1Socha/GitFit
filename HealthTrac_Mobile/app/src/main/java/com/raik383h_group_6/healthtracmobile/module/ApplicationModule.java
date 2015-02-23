package com.raik383h_group_6.healthtracmobile.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitTeamServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.oauth.FacebookScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitAccountServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitUserServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.oauth.TwitterScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOAuthServiceAdapter.class).annotatedWith(Names.named("Facebook")).to(FacebookScribeOAuthServiceAdapter.class);
        bind(IOAuthServiceAdapter.class).annotatedWith(Names.named("Twitter")).to(TwitterScribeOAuthServiceAdapter.class);
        bind(AccountService.class).toProvider(RetrofitAccountServiceProvider.class);
        bind(UserService.class).toProvider(RetrofitUserServiceProvider.class);
        bind(TeamService.class).toProvider(RetrofitTeamServiceProvider.class);
    }
}
