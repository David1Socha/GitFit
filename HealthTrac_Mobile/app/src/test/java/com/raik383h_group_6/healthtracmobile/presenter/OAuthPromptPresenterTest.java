package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

public class OAuthPromptPresenterTest {

    private OAuthPromptPresenter presenter;
    private IActivityNavigator nav;
    private IResources resources;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        resources = mock(IResources.class);
        when(resources.getString(R.string.PROVIDER_FACEBOOK)).thenReturn(FACEBOOK);
        when(resources.getString(R.string.PROVIDER_TWITTER)).thenReturn(TWITTER);
        when(resources.getString(R.string.EXTRA_ACCESS_TOKEN)).thenReturn(TOKEN_KEY);
        when(resources.getString(R.string.EXTRA_ACCESS_SECRET)).thenReturn(SECRET_KEY);
        when(resources.getString(R.string.EXTRA_PROVIDER)).thenReturn(PROVIDER_KEY);
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
}
