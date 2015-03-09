package com.raik383h_group_6.healthtracmobile.application;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitFacebookServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitMembershipServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitTeamServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.oauth.FacebookScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitAccountServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitUserServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.oauth.TwitterScribeOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.service.oauth.provider.AsyncFacebookScribeOAuthServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.oauth.provider.AsyncTwitterScribeOAuthServiceProvider;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOAuthService.class).annotatedWith(Names.named("Facebook")).to(FacebookScribeOAuthServiceAdapter.class);
        bind(IOAuthService.class).annotatedWith(Names.named("Twitter")).to(TwitterScribeOAuthServiceAdapter.class);
        bind(AccountService.class).toProvider(RetrofitAccountServiceProvider.class);
        bind(UserService.class).toProvider(RetrofitUserServiceProvider.class);
        bind(TeamService.class).toProvider(RetrofitTeamServiceProvider.class);
        bind(MembershipService.class).toProvider(RetrofitMembershipServiceProvider.class);
        bind(FacebookService.class).toProvider(RetrofitFacebookServiceProvider.class);
        bind(IAsyncOAuthService.class).annotatedWith(Names.named("FacebookAsync")).toProvider(AsyncFacebookScribeOAuthServiceProvider.class);
        bind(IAsyncOAuthService.class).annotatedWith(Names.named("TwitterAsync")).toProvider(AsyncTwitterScribeOAuthServiceProvider.class);
        bind(Gson.class);
        bind(IAsyncTeamService.class).to(AsyncTeamService.class);
        bind(IAsyncMembershipService.class).to(AsyncMembershipService.class);
        bind(IAsyncUserService.class).to(AsyncUserService.class);
        install(new FactoryModuleBuilder().build(PresenterFactory.class));
    }
}
