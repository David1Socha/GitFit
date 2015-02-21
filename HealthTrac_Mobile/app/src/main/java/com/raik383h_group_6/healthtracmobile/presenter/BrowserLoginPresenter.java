package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.view.BrowserLoginActivity;

import java.util.concurrent.ExecutionException;

public class BrowserLoginPresenter {

    private IOAuthServiceAdapter oAuthService;
    private Token requestToken;
    private BrowserLoginActivity view;
    private WebView webView;

    public void setUpWebView(WebView web) {
        webView = web;
        WebViewClient webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //TODO see if can be disabled
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(webViewClient);
    }

    public void initialize( IOAuthServiceAdapter serviceAdapter, BrowserLoginActivity view) {
        oAuthService = serviceAdapter;
        this.view = view;
    }

    public void beginAuthorization() {
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
            if ((url != null) && (url.startsWith(IOAuthServiceAdapter.DUMMY_CALLBACK))) { // Don't open callback url
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(oAuthService.getVerifierName()) == null) { //Check if we're getting called back because of OAuth cancellation
                    view.finishInShame();
                } else {
                    Token token = getToken(uri);
                    view.finishWithToken(token);
                }
            } else {
                super.onPageStarted(webView, url, favicon);
            }
        }
    }
}
