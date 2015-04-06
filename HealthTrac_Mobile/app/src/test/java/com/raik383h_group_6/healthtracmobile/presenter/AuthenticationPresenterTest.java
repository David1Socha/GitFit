package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SAMPLE_SECRET;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SAMPLE_TOKEN;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SECRET_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SIGNIN_ERR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationPresenterTest {

    private IAsyncAccountService accountService;
    private IActivityNavigator nav;
    private AuthenticationView view;
    private AccessGrant grant;
    private AuthenticationPresenter presenter;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        grant = ModelGenerator.genBasicGrant();
        view = mock(AuthenticationView.class);
        TestStubber.stubViewForResources(view);
        accountService = mock(IAsyncAccountService.class);
        presenter = new AuthenticationPresenter(accountService, nav, view);
    }

    @Test
    public void onClickSignInOpensOAuthPrompt() {
        presenter.onClickSignIn();
        verify(nav).openOAuthPrompt(RequestCodes.OAUTH_TO_SIGN_IN);
    }

    @Test
    public void onClickCreateAccountOpensOAuthPrompt() {
        presenter.onClickCreateAccount();
        verify(nav).openOAuthPrompt(RequestCodes.OAUTH_TO_CREATE_ACCOUNT);
    }

    @Test
    public void onActivityResultForCreateAccountSignsInAndFinishes() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenReturn(grant);
        presenter.onActivityResult(RequestCodes.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(nav).finishWithAccessGrant(grant);
    }

    @Test
    public void onActivityResultForCreateAccountDisplaysErrorWhenServiceFails() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenThrow(new ExecutionException(new Exception("api failure")));
        presenter.onActivityResult(RequestCodes.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(view).displayMessage(SIGNIN_ERR);
    }

    @Test
    public void onActivityResultForOAuthSignInSignsInAndFinishes() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenReturn(grant);
        presenter.onActivityResult(RequestCodes.OAUTH_TO_SIGN_IN, Activity.RESULT_OK, extras);
        verify(extras).getString(PROVIDER_KEY);
        verify(extras).getString(TOKEN_KEY);
        verify(extras).getString(SECRET_KEY);
        verify(nav).finishWithAccessGrant(grant);
    }

    @Test
    public void onActivityResultForOAuthSignInDisplaysErrorWhenServiceFails() throws ExecutionException, InterruptedException {
        Bundle extras = mock(Bundle.class);
        when(accountService.loginAsync(any(Credentials.class))).thenThrow(new ExecutionException(new Exception("api failure")));
        presenter.onActivityResult(RequestCodes.CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(view).displayMessage(SIGNIN_ERR);
    }

    @Test
    public void onActivityResultForOAuthCreateAccountOpensCreateUser() {
        Bundle extras = mock(Bundle.class);
        when(extras.getString(PROVIDER_KEY)).thenReturn(FACEBOOK);
        when(extras.getString(TOKEN_KEY)).thenReturn(SAMPLE_TOKEN);
        when(extras.getString(SECRET_KEY)).thenReturn(SAMPLE_SECRET);
        presenter.onActivityResult(RequestCodes.OAUTH_TO_CREATE_ACCOUNT, Activity.RESULT_OK, extras);
        verify(nav).openCreateUser(SAMPLE_TOKEN, SAMPLE_SECRET, FACEBOOK, RequestCodes.CREATE_ACCOUNT);
    }
}
