package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Intent;
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
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;
import com.raik383h_group_6.healthtracmobile.view.OAuthBrowserActivity;

import java.util.concurrent.ExecutionException;

public class OAuthBrowserPresenter {

    private IOAuthService oAuthService;
    private Token requestToken;
    private OAuthBrowserActivity view;
    private WebView webView;
    private IResources resources;
    private Bundle extras;

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
    public OAuthBrowserPresenter(@Assisted IOAuthService service, @Assisted Bundle extras, @Assisted IResources resources, @Assisted WebView web, @Assisted OAuthBrowserActivity view) {
        this.oAuthService = service;
        this.extras = extras;
        this.resources = resources;
        this.view = view;
        this.webView = web;
        setUpWebView();
    }

    public void onCreate() {
        setUpWebView();
        beginAuthorization();
    }

    private void beginAuthorization() {
        String authUrl = getAuthorizationUrl();
        webView.loadUrl(authUrl);
    }

    private String getAuthorizationUrl() {
        String authUrl = null;
        try {
            authUrl =
            (new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    try { //OAuth 2.0 doesn't use request tokens...
                        requestToken = oAuthService.getRequestToken();
                        return oAuthService.getAuthorizationUrl(requestToken);
                    } catch (UnsupportedOperationException e) {
                        return oAuthService.getAuthorizationUrl(null);
                    }
                }

            }).execute().get();
        } catch (InterruptedException | ExecutionException e) {
        }
        return authUrl;
    }

    private Token getToken(Uri uri) {
        final String verifier = uri.getQueryParameter(oAuthService.getVerifierName());
        Token token = null;
        try {
            token = (new AsyncTask<Void, Void, Token>() {
                @Override
                protected Token doInBackground(Void... params) {
                    return oAuthService.getAccessToken(requestToken, verifier);
                }
            }).execute().get();
        } catch (InterruptedException | ExecutionException ignored) {
        }

        return token;
    }

    private class LoginWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(IOAuthService.DUMMY_CALLBACK))) { // Don't open callback url
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(oAuthService.getVerifierName()) == null) { //Check if we're getting called back because of OAuth cancellation
                    finishInShame();
                } else {
                    Token token = getToken(uri);
                    finishWithToken(token);
                }
            } else {
                super.onPageStarted(webView, url, favicon);
            }
        }
    }

    private void finishWithToken(Token token) { //Should view or presenter do this?
        Intent data = new Intent();
        data.putExtra(resources.getString(R.string.EXTRA_ACCESS_SECRET), token.getSecret());
        data.putExtra(resources.getString(R.string.EXTRA_ACCESS_TOKEN), token.getToken());
        data.putExtra(resources.getString(R.string.EXTRA_PROVIDER), extras.getString(resources.getString(R.string.EXTRA_PROVIDER)));
        view.setResult(Activity.RESULT_OK, data);
        view.finish();
    }

    private void finishInShame() {
        view.setResult(Activity.RESULT_CANCELED);
        view.finish();
    }
}
