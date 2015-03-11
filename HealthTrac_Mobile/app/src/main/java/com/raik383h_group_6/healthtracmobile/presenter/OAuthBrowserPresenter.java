package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import java.util.concurrent.ExecutionException;

public class OAuthBrowserPresenter extends BasePresenter{

    private IAsyncOAuthService oAuthService;
    private Token requestToken;
    private WebView webView;
    private Bundle extras;
    private IActivityNavigator nav;
    private String authUrl;
    private BaseView view;

    private void setUpWebView() {
        WebViewClient webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);
    }

    @Inject
    public OAuthBrowserPresenter(@Assisted IAsyncOAuthService service, @Assisted Bundle extras, @Assisted WebView web, @Assisted IActivityNavigator nav, @Assisted BaseView view) {
        this.oAuthService = service;
        this.extras = extras;
        this.view = view;
        this.nav = nav;
        this.webView = web;
    }

    public void onCreate() {
        setUpWebView();
        beginAuthorization();
    }

    private void beginAuthorization() {
        loadRequestToken();
        loadAuthUrl();
        webView.loadUrl(authUrl);
    }

    private void loadRequestToken() {
        try {
            requestToken = oAuthService.getRequestToken();
        } catch (InterruptedException | ExecutionException ignored) {
        }
    }

    private void loadAuthUrl() {
        try {
            authUrl = oAuthService.getAuthorizationUrl(requestToken);
        } catch (InterruptedException | ExecutionException ignored) {
        }
    }

    private Token getToken(Uri uri) {
        String verifier = uri.getQueryParameter(oAuthService.getVerifierName());
        Token token = null;
        try {
            token = oAuthService.getAccessToken(requestToken, verifier);
        } catch (InterruptedException | ExecutionException ignored) {
        }
        return token;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    @Override
    protected BaseView getView() {
        return view;
    }


    private class LoginWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(IOAuthService.DUMMY_CALLBACK))) { // Don't open callback url
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(oAuthService.getVerifierName()) == null) { //Check if we're getting called back because of OAuth cancellation
                    nav.finishOAuthBrowserInShame();
                } else {
                    Token token = getToken(uri);
                    nav.finishOAuthBrowserWithToken(token, extras.getString(view.getResource(R.string.EXTRA_PROVIDER)));
                }
            } else {
                super.onPageStarted(webView, url, favicon);
            }
        }
    }

}
