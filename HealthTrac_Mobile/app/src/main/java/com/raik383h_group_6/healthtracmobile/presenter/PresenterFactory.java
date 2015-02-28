package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IOAuthService serviceAdapter, WebView web, Bundle extras, IResources resources, ActivityNavigator nav, OAuthBrowserActivity view);
    AuthenticationPresenter create(AccountService accountService, IResources resources, ActivityNavigator nav, AuthenticationActivity view);
    OAuthPromptPresenter create(IResources resources, ActivityNavigator nav, OAuthPromptActivity view);
    RegisterUserPresenter create(Bundle extras, IResources resources, ActivityNavigator nav, RegisterUserActivity view);
    ListUsersPresenter create(IResources resources, ActivityNavigator nav, ListUsersActivity view);
}
