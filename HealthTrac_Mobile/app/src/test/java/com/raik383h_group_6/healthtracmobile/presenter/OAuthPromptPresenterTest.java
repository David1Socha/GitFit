package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;

import org.junit.Before;
import org.junit.Test;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SECRET_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TWITTER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OAuthPromptPresenterTest {

    private OAuthPromptPresenter presenter;
    private IActivityNavigator nav;
    private IResources resources;
    private String token = "123", secret = "abc", provider = FACEBOOK;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        resources = ModelGenerator.genStubbedResources();
        presenter = new OAuthPromptPresenter(resources, nav);
    }

    @Test
    public void onClickLoginTwitterOpensOAuthBrowserWithTwitter() {
        presenter.onClickLoginTwitter();
        verify(nav).openOAuthBrowser(TWITTER, OAuthPromptPresenter.TW_LOGIN_REQ);
    }

    @Test
    public void onClickLoginFacebookOpensOAuthBrowserWithFacebook() {
        presenter.onClickLoginFacebook();
        verify(nav).openOAuthBrowser(FACEBOOK, OAuthPromptPresenter.FB_LOGIN_REQ);
    }

    @Test
    public void onActivityResultFinishesInShameWhenNoResultData() {
        presenter.onActivityResult(OAuthPromptPresenter.FB_LOGIN_REQ, Activity.RESULT_CANCELED, null);
        verify(nav).finishOAuthPromptInShame();
    }

    @Test
    public void onActivityResultFinishesWithDataWhenValidData() {
        Bundle mockBundle = mock(Bundle.class);
        when(mockBundle.getString(TOKEN_KEY)).thenReturn(token);
        when(mockBundle.getString(SECRET_KEY)).thenReturn(secret);
        when(mockBundle.getString(PROVIDER_KEY)).thenReturn(provider);
        presenter.onActivityResult(OAuthPromptPresenter.FB_LOGIN_REQ, Activity.RESULT_OK, mockBundle);
        verify(nav).finishOAuthPromptWithInfo(token, secret, provider);
    }
}
