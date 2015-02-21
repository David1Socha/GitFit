package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapter;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapterFactory;
import com.raik383h_group_6.healthtracmobile.view.BrowserLoginActivity;

import java.util.concurrent.ExecutionException;

public abstract class BrowserLoginPresenter extends BasePresenter<BrowserLoginActivity> {

    private IOAuthServiceAdapter oAuthService;
    private Token requestToken;
    private WebView webView;
    public static String DUMMY_CALLBACK = "http://www.example.com/oauth_callback";

    protected abstract String getVerifierName();

    protected abstract IOAuthServiceAdapter buildOAuthServiceAdapter(IOAuthServiceAdapterFactory factory);

    @Inject
    public BrowserLoginPresenter(IOAuthServiceAdapterFactory factory) {
        oAuthService = buildOAuthServiceAdapter(factory);
    }

    public void setUpWebView(WebView webView) {
        WebViewClient webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //TODO see if can be disabled
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(webViewClient);
        getView().setView(webView);
    }

    private void beginAuthorization() {
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

            @Override
            protected void onPostExecute(String url) {
                webView.loadUrl(url);
            }
        }).execute();
    }

    private Token getToken(Uri uri) {
        final String verifier = uri.getQueryParameter(getVerifierName());
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
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(DUMMY_CALLBACK))) { // Don't open callback url
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(getVerifierName()) == null) { //Check if we're getting called back because of OAuth cancellation
                    getView().finishInShame();
                } else {
                    Token token = getToken(uri);
                    getView().finishWithToken(token);
                }
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }
    }
}
