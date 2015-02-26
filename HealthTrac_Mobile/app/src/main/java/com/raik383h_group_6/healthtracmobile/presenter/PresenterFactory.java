package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebView;

import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

public interface PresenterFactory {
    OAuthBrowserPresenter create(IOAuthServiceAdapter serviceAdapter, Bundle extras, IResources resources, WebView web, OAuthBrowserActivity view);
    AuthenticationPresenter create(AccountService accountService, AuthenticationActivity view);
    OAuthPromptPresenter create(Bundle extras, OAuthPromptActivity view);
    RegisterUserPresenter create(FacebookService facebookService, AccountService accountService, Bundle extras, IResources resources, RegisterUserActivity registerUserActivity);
}
