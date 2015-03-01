package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainActivity;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsActivity;
import com.raik383h_group_6.healthtracmobile.view.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;
import com.raik383h_group_6.healthtracmobile.view.UpdateUserActivity;
import com.raik383h_group_6.healthtracmobile.view.ViewUserActivity;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IOAuthService serviceAdapter, WebView web, Bundle extras, IResources resources, ActivityNavigator nav, OAuthBrowserActivity view);
    AuthenticationPresenter create(AccountService accountService, IResources resources, ActivityNavigator nav, AuthenticationActivity view);
    OAuthPromptPresenter create(IResources resources, ActivityNavigator nav, OAuthPromptActivity view);
    RegisterUserPresenter create(Bundle extras, IResources resources, ActivityNavigator nav, RegisterUserActivity view);
    ListUsersPresenter create(Bundle extras, IResources resources, ActivityNavigator nav, ListUsersActivity view);
    ListTeamsPresenter create(Bundle extras, IResources resources, ActivityNavigator nav, ListTeamsActivity view);
    ViewUserPresenter create( Bundle extras, IResources resources, ActivityNavigator nav, ViewUserActivity view);
    UpdateUserPresenter create( Bundle extras, IResources resources, ActivityNavigator nav, UpdateUserActivity view);
    GitFitMainPresenter create(IResources resources, ActivityNavigator nav, GitFitMainActivity view);
}
