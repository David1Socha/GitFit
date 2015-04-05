package com.raik383h_group_6.healthtracmobile.application;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.BadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.EnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.GoalService;
import com.raik383h_group_6.healthtracmobile.service.api.MealService;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.PointService;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.api.UserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.UserGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitActivityServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitBadgeServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitEnergyLevelServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitFacebookServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitGoalServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitMealServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitMembershipServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitPointServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitTeamServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitUserBadgeServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.api.provider.RetrofitUserGoalServiceProvider;
import com.raik383h_group_6.healthtracmobile.service.json.GsonJsonParserAdapter;
import com.raik383h_group_6.healthtracmobile.service.json.JsonParser;
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
        bind(JsonParser.class).to(GsonJsonParserAdapter.class);
        bind(ActivityService.class).toProvider(RetrofitActivityServiceProvider.class);
        bind(BadgeService.class).toProvider(RetrofitBadgeServiceProvider.class);
        bind(EnergyLevelService.class).toProvider(RetrofitEnergyLevelServiceProvider.class);
        bind(GoalService.class).toProvider(RetrofitGoalServiceProvider.class);
        bind(MealService.class).toProvider(RetrofitMealServiceProvider.class);
        bind(PointService.class).toProvider(RetrofitPointServiceProvider.class);
        bind(UserBadgeService.class).toProvider(RetrofitUserBadgeServiceProvider.class);
        bind(UserGoalService.class).toProvider(RetrofitUserGoalServiceProvider.class);
        bind(MembershipService.class).toProvider(RetrofitMembershipServiceProvider.class);
        bind(FacebookService.class).toProvider(RetrofitFacebookServiceProvider.class);
        bind(IAsyncOAuthService.class).annotatedWith(Names.named("FacebookAsync")).toProvider(AsyncFacebookScribeOAuthServiceProvider.class);
        bind(IAsyncOAuthService.class).annotatedWith(Names.named("TwitterAsync")).toProvider(AsyncTwitterScribeOAuthServiceProvider.class);
        bind(IAsyncFacebookService.class).to(AsyncFacebookService.class);
        bind(IAsyncTeamService.class).to(AsyncTeamService.class);
        bind(IAsyncAccountService.class).to(AsyncAccountService.class);
        bind(IAsyncMembershipService.class).to(AsyncMembershipService.class);
        bind(IAsyncUserService.class).to(AsyncUserService.class);
        install(new FactoryModuleBuilder().build(PresenterFactory.class));
    }
}
