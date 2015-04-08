package com.raik383h_group_6.healthtracmobile.application;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.LocationRequestProvider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.AsyncMealService;
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
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncPointService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncUserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncUserGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.AsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMealService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncPointService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserGoalService;
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
        bind(LocationRequest.class).toProvider(LocationRequestProvider.class);
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
        bind(IAsyncActivityService.class).to(AsyncActivityService.class);
        bind(IAsyncBadgeService.class).to(AsyncBadgeService.class);
        bind(IAsyncEnergyLevelService.class).to(AsyncEnergyLevelService.class);
        bind(IAsyncMealService.class).to(AsyncMealService.class);
        bind(IAsyncGoalService.class).to(AsyncGoalService.class);
        bind(IAsyncPointService.class).to(AsyncPointService.class);
        bind(IAsyncUserBadgeService.class).to(AsyncUserBadgeService.class);
        bind(IAsyncUserGoalService.class).to(AsyncUserGoalService.class);
        install(new FactoryModuleBuilder().build(PresenterFactory.class));
    }
}
