package com.raik383h_group_6.healthtracmobile.view;

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
import com.google.inject.Inject;
import com.google.inject.Key;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.service.*;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;


public abstract class LoginWebViewActivity extends ActionBarActivity implements RoboContext {

    private WebView webView;
    private IOAuthServiceAdapter oAuthService;
    private Token requestToken;
    @Inject
    IOAuthServiceAdapterFactory factory;
    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>, Object>();

    protected abstract IOAuthServiceAdapter buildOAuthServiceAdapter(IOAuthServiceAdapterFactory factory);

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(this).injectMembersWithoutViews(this);
        webView = new WebView(this);
        setUpWebView();
        oAuthService = buildOAuthServiceAdapter(factory);
        setContentView(webView);

        beginAuthorization();
    }

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
        //WebViewClient webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        //webView.setWebViewClient(webViewClient);
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



    private void saveToken(Uri uri) {
        final String verifier = uri.getQueryParameter(getVerifierName());

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
