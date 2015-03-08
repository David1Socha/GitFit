package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

public class OAuthBrowserPresenterTest {

    private OAuthBrowserPresenter presenter;
    private IOAuthService service;
    private Bundle extras;
    private IResources resources;
    private WebView webView;
    private IActivityNavigator nav;
    private WebSettings settings;

    @Before
    public void setup() {
        service = mock(IOAuthService.class);
        extras = mock(Bundle.class);
        resources = mock(IResources.class);
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

}
