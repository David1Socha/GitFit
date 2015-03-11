package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.AUTH_URL;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OAuthBrowserPresenterTest {

    private OAuthBrowserPresenter presenter;
    private IAsyncOAuthService service;
    private Bundle extras;
    private IResources resources;
    private WebView webView;
    private IActivityNavigator nav;
    private WebSettings settings;
    private Token reqToken;

    @Before
    public void setup() {
        service = mock(IAsyncOAuthService.class);
        extras = mock(Bundle.class);
        reqToken = ModelGenerator.genRequestToken();
        resources = ModelGenerator.genStubbedResources();
        webView = mock(WebView.class);
        nav = mock(IActivityNavigator.class);
        settings = mock(WebSettings.class);
        when(webView.getSettings()).thenReturn(settings);
        presenter = new OAuthBrowserPresenter(service, extras, resources, webView, nav);
    }

    @Test
    public void onCreatePreparesWebViewWithClient() {
        presenter.onCreate();
        verify(webView).setWebViewClient(any(WebViewClient.class));
    }

    @Test
    public void onCreateLoadsAuthUrlInWebClientWhenOAuth2() throws ExecutionException, InterruptedException {
        when(service.getRequestToken()).thenReturn(null); // oauth 2 has no request tokens
        when(service.getAuthorizationUrl(null)).thenReturn(AUTH_URL);
        presenter.onCreate();
        verify(webView).loadUrl(AUTH_URL);
    }

    @Test
    public void onCreateLoadsAuthUrlInWebClientWhenOAuth1() throws ExecutionException, InterruptedException {
        when(service.getRequestToken()).thenReturn(reqToken); // oauth 1 does use request tokens
        when(service.getAuthorizationUrl(reqToken)).thenReturn(AUTH_URL);
        presenter.onCreate();
        verify(webView).loadUrl(AUTH_URL);
    }

    //TODO consider adding tests of LoginWebViewClient (may require changing access level)

}
