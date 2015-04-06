package com.raik383h_group_6.healthtracmobile.presenter;

import android.webkit.WebView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IAsyncOAuthService serviceAdapter, WebView web, IActivityNavigator nav, BaseView view);
    AuthenticationPresenter create(IActivityNavigator nav, AuthenticationView view);
    OAuthPromptPresenter create(IActivityNavigator nav, BaseView view);
    CreateUserPresenter create(UserValidationPresenter userValidationPresenter, IActivityNavigator nav, CreateUserView view);
    ListUsersPresenter create(IActivityNavigator nav, ListUsersView view);
    ListTeamsPresenter create(IActivityNavigator nav, ListTeamsView view);
    ViewUserPresenter create(IActivityNavigator nav, ViewUserView view);
    EditUserPresenter create(UserValidationPresenter presenter, IActivityNavigator nav, EditUserView view);
    GitFitMainPresenter create(IActivityNavigator nav, GitFitMainView view);
    ViewTeamPresenter create(IActivityNavigator nav, ViewTeamView view);
    ActivityPresenter create(IActivityNavigator nav, GoogleApiClient gClient, ActivityView view);
    UserValidationPresenter create(UserValidationView view);
}
