package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IAsyncOAuthService serviceAdapter, WebView web, Bundle extras, IActivityNavigator nav, BaseView view);
    AuthenticationPresenter create(IActivityNavigator nav, AuthenticationView view);
    OAuthPromptPresenter create(IActivityNavigator nav, BaseView view);
    CreateUserPresenter create(UserValidationPresenter userValidationPresenter, Bundle extras, IActivityNavigator nav, CreateUserView view);
    ListUsersPresenter create(Bundle extras, IActivityNavigator nav, ListUsersView view);
    ListTeamsPresenter create(Bundle extras, IActivityNavigator nav, ListTeamsView view);
    ViewUserPresenter create( Bundle extras, IActivityNavigator nav, ViewUserView view);
    EditUserPresenter create( Bundle extras, UserValidationPresenter presenter, IActivityNavigator nav, EditUserView view);
    GitFitMainPresenter create(IActivityNavigator nav, GitFitMainView view);
    ViewTeamPresenter create(Bundle extras, IActivityNavigator nav, ViewTeamView view);
    UserValidationPresenter create(UserValidationView view);
}
