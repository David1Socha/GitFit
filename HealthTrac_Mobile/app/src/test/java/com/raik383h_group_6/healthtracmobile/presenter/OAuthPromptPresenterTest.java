package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import org.junit.Before;
import org.junit.Test;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SAMPLE_SECRET;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SAMPLE_TOKEN;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SECRET_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TWITTER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OAuthPromptPresenterTest {

    private OAuthPromptPresenter presenter;
    private IActivityNavigator nav;
    private BaseView view;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        view = mock(BaseView.class);
        TestStubber.stubViewForResources(view);
        presenter = new OAuthPromptPresenter(nav, view);
    }

    @Test
    public void onClickLoginTwitterOpensOAuthBrowserWithTwitter() {
        presenter.onClickLoginTwitter();
        verify(nav).openOAuthBrowser(TWITTER, RequestCodes.TW_LOGIN_REQ);
    }

    @Test
    public void onClickLoginFacebookOpensOAuthBrowserWithFacebook() {
        presenter.onClickLoginFacebook();
        verify(nav).openOAuthBrowser(FACEBOOK, RequestCodes.FB_LOGIN_REQ);
    }

    @Test
    public void onActivityResultFinishesInShameWhenNoResultData() {
        presenter.onActivityResult(RequestCodes.FB_LOGIN_REQ, Activity.RESULT_CANCELED, null);
        verify(nav).finishOAuthPromptInShame();
    }

    @Test
    public void onActivityResultFinishesWithDataWhenValidData() {
        Bundle mockBundle = mock(Bundle.class);
        when(mockBundle.getString(TOKEN_KEY)).thenReturn(SAMPLE_TOKEN);
        when(mockBundle.getString(SECRET_KEY)).thenReturn(SAMPLE_SECRET);
        when(mockBundle.getString(PROVIDER_KEY)).thenReturn(FACEBOOK);
        presenter.onActivityResult(RequestCodes.FB_LOGIN_REQ, Activity.RESULT_OK, mockBundle);
        verify(nav).finishOAuthPromptWithInfo(SAMPLE_TOKEN, SAMPLE_SECRET, FACEBOOK);
    }
}
