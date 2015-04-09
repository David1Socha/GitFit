package com.raik383h_group_6.healthtracmobile.presenter;

import android.webkit.WebView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateTeamView;
import com.raik383h_group_6.healthtracmobile.view.CreateEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.CreateGoalView;
import com.raik383h_group_6.healthtracmobile.view.CreateMealView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.EditTeamView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;
import com.raik383h_group_6.healthtracmobile.view.FeedView;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;
import com.raik383h_group_6.healthtracmobile.view.TeamValidationView;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityReportView;
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IAsyncOAuthService serviceAdapter, WebView web, IActivityNavigator nav, BaseView view);
    AuthenticationPresenter create(IActivityNavigator nav, AuthenticationView view);
    OAuthPromptPresenter create(IActivityNavigator nav, BaseView view);
    CreateUserPresenter create(UserValidationPresenter userValidationPresenter, IActivityNavigator nav, CreateUserView view);
    CreateTeamPresenter create(TeamValidationPresenter teamValidationPresenter, IActivityNavigator nav, CreateTeamView view);
    ListUsersPresenter create(IActivityNavigator nav, ListUsersView view);
    ListTeamsPresenter create(IActivityNavigator nav, ListTeamsView view);
    ViewUserPresenter create(IActivityNavigator nav, ViewUserView view);
    EditUserPresenter create(UserValidationPresenter presenter, IActivityNavigator nav, EditUserView view);
    EditTeamPresenter create(TeamValidationPresenter presenter, IActivityNavigator nav, EditTeamView view);
    GitFitMainPresenter create(IActivityNavigator nav, GitFitMainView view);
    ViewTeamPresenter create(IActivityNavigator nav, ViewTeamView view);
    ActivityPresenter create(IActivityNavigator nav, GoogleApiClient gClient, ActivityView view);
    UserValidationPresenter create(UserValidationView view);
    TeamValidationPresenter create(TeamValidationView view);
    CreateGoalPresenter create(IActivityNavigator nav, CreateGoalView view);
    CreateEnergyLevelPresenter create(IActivityNavigator nav, CreateEnergyLevelView view);
    CreateMealPresenter create(IActivityNavigator nav, CreateMealView view);
    FeedPresenter create(IActivityNavigator nav, FeedView view);
    ViewActivityReportPresenter create(IActivityNavigator nav, ViewActivityReportView view);
    ViewEnergyLevelPresenter create(IActivityNavigator nav, ViewEnergyLevelView view);
}
