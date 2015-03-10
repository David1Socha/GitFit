package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;

import org.apache.http.auth.AUTH;
import org.junit.Before;
import org.junit.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationPresenterTest {

    private IAsyncAccountService accountService;
    private IResources resources;
    private IActivityNavigator nav;
    private AuthenticationView view;
    private AccessGrant grant;
    private AuthenticationPresenter presenter;

    @Before
    public void setup() {
        resources = ModelGenerator.genStubbedResources();
        nav = mock(IActivityNavigator.class);
        grant = ModelGenerator.genBasicGrant();
        view = mock(AuthenticationView.class);
        accountService = mock(IAsyncAccountService.class);
        presenter = new AuthenticationPresenter(accountService, resources, nav, view);
    }

    @Test
    public void onClickSignInOpensOAuthPrompt() {
        presenter.onClickSignIn();
        verify(nav).openOAuthPrompt(AuthenticationPresenter.OAUTH_TO_SIGN_IN);
    }

    @Test
    public void onClickCreateAccountOpensOAuthPrompt() {
        presenter.onClickCreateAccount();
        verify(nav).openOAuthPrompt(AuthenticationPresenter.OAUTH_TO_CREATE_ACCOUNT);
    }

    @Test
    public void onActivityResultForCreateAccountSignsInAndFinishes() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenReturn(grant);
        presenter.onActivityResult(AuthenticationPresenter.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(nav).finishWithAccessGrant(grant);
    }

    @Test
    public void onActivityResultForCreateAccountDisplaysErrorWhenServiceFails() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenThrow(new ExecutionException(new Exception("api failure")));
        presenter.onActivityResult(AuthenticationPresenter.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(view).displayMessage(SIGNIN_ERR);
    }

    @Test
    public void onActivityResultForOAuthSignInSignsInAndFinishes() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenReturn(grant);
        presenter.onActivityResult(AuthenticationPresenter.OAUTH_TO_SIGN_IN, Activity.RESULT_OK, extras);
        verify(extras).getString(PROVIDER_KEY);
        verify(extras).getString(TOKEN_KEY);
        verify(extras).getString(SECRET_KEY);
        verify(nav).finishWithAccessGrant(grant);
    }

    @Test
    public void onActivityResultForOAuthSignInDisplaysErrorWhenServiceFails() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenThrow(new ExecutionException(new Exception("api failure")));
        presenter.onActivityResult(AuthenticationPresenter.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(view).displayMessage(SIGNIN_ERR);
    }
}
