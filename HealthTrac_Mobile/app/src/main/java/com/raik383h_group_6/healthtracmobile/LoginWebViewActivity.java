package com.raik383h_group_6.healthtracmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.model.Token;
import com.raik383h_group_6.healthtracmobile.service.*;


public abstract class LoginWebViewActivity extends ActionBarActivity {

    private static String DUMMY_CALLBACK = "http://www.example.com/oauth_callback";
    private String apiKey, apiSecret;
    private Class apiClass;
    private WebView webView;
    private IOAuthServiceAdapter oAuthService;
    private Token requestToken;
    private WebViewClient webViewClient;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
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
                .callback(DUMMY_CALLBACK)
                .build();

        webView = new WebView(this);
        setUpWebView();

        setContentView(webView);

        beginAuthorization();
    }

    protected abstract void setOAuthFields();

    protected abstract String getVerifierName();

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

    private void setUpWebView() {
        webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(webViewClient);
    }

    private void saveTokenAndFinish(Token token) {
        Intent data = new Intent();
        data.putExtra(getString(R.string.EXTRA_ACCESS_SECRET), token.getSecret());
        data.putExtra(getString(R.string.EXTRA_ACCESS_TOKEN), token.getToken());
        setResult(RESULT_OK, data);
        finish();
    }

    private void finishInShame() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private class LoginWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(DUMMY_CALLBACK))) { // Don't open callback url
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(getVerifierName()) == null) { //Check if we're getting called back because of OAuth cancellation
                    finishInShame();
                } else {
                    saveToken(uri);
                }
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }
    }

    private void saveToken(Uri uri) {
        final Verifier verifier = new Verifier(uri.getQueryParameter(getVerifierName()));
        (new AsyncTask<Void, Void, Token>() {
            @Override
            protected Token doInBackground(Void... params) {
                return oAuthService.getAccessToken(requestToken, verifier);
            }

            @Override
            protected void onPostExecute(Token accessToken) {
                saveTokenAndFinish(accessToken);
            }
        }).execute();
    }

}
