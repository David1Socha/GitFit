package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IAsyncOAuthService serviceAdapter, WebView web, Bundle extras, IResources resources, IActivityNavigator nav);
    AuthenticationPresenter create(AccountService accountService, IResources resources, IActivityNavigator nav, AuthenticationView view);
    OAuthPromptPresenter create(IResources resources, IActivityNavigator nav);
    CreateUserPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, CreateUserView view);
    ListUsersPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ListUsersView view);
    ListTeamsPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ListTeamsView view);
    ViewUserPresenter create( Bundle extras, IResources resources, IActivityNavigator nav, ViewUserView view);
    EditUserPresenter create( Bundle extras, IResources resources, IActivityNavigator nav, EditUserView view);
    GitFitMainPresenter create(IResources resources, IActivityNavigator nav, GitFitMainView view);
    ViewTeamPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ViewTeamView view);
}
