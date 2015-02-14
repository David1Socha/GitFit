package com.example.raik383h_group_6.healthtracmobile;

import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.model.Token;


public abstract class LoginWebViewActivity extends ActionBarActivity {

    private String apiKey, apiSecret, callbackUrl;
    private Class apiClass;
    private WebView webView;
    private OAuthService oAuthService;
    private Token requestToken;
    private WebViewClient webViewClient;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public void setApiClass(Class apiClass) {
        this.apiClass = apiClass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setOAuthFields();
        oAuthService = new ServiceBuilder()
                .provider(apiClass)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback(callbackUrl)
                .build();

        webView = new WebView(this);

        setContentView(webView);

        beginAuthorization();
    }

    protected abstract void setOAuthFields();

    private void beginAuthorization() {
        (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                requestToken = oAuthService.getRequestToken();
                return oAuthService.getAuthorizationUrl(requestToken);
            }

            @Override
            protected void onPostExecute(String url) {
                webView.loadUrl(url);
            }
        }).execute();
    }

    private void setUpWebView() {
        webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(new WebChromeClient());
    }

    private class LoginWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(callbackUrl))) { // Override webview when goes to callbackUrl
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                final Verifier verifier = new Verifier(uri.getQueryParameter("oauth_verifier"));
                (new AsyncTask<Void, Void, Token>() {
                    @Override
                    protected Token doInBackground(Void... params) {
                        return oAuthService.getAccessToken(requestToken, verifier);
                    }

                    @Override
                    protected void onPostExecute(Token accessToken) {
                        Log.d("secret", accessToken.getSecret());
                        Log.d("token", accessToken.getToken());
                        finish();
                    }
                }).execute();
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }

    }

}
