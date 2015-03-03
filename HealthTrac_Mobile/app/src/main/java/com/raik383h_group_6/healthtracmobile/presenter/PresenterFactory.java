package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.activity.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.GitFitMainActivity;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.RegisterUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.UpdateUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewTeamActivity;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IOAuthService serviceAdapter, WebView web, Bundle extras, IResources resources, IActivityNavigator nav, OAuthBrowserActivity view);
    AuthenticationPresenter create(AccountService accountService, IResources resources, IActivityNavigator nav, AuthenticationActivity view);
    OAuthPromptPresenter create(IResources resources, IActivityNavigator nav, OAuthPromptActivity view);
    RegisterUserPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, RegisterUserActivity view);
    ListUsersPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ListUsersActivity view);
    ListTeamsPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ListTeamsActivity view);
    ViewUserPresenter create( Bundle extras, IResources resources, IActivityNavigator nav, ViewUserView view);
    UpdateUserPresenter create( Bundle extras, IResources resources, IActivityNavigator nav, UpdateUserActivity view);
    GitFitMainPresenter create(IResources resources, IActivityNavigator nav, GitFitMainActivity view);
    ViewTeamPresenter create(Bundle extras, IResources resources, IActivityNavigator nav, ViewTeamActivity view);
}
