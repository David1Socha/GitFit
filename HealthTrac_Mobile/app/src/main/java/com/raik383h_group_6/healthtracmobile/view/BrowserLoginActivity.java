package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.presenter.BrowserLoginPresenter;
import com.raik383h_group_6.healthtracmobile.service.*;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;


public class BrowserLoginActivity extends ActionBarActivity implements RoboContext {

    private WebView webView;

    @Inject
    BrowserLoginPresenter browserLoginPresenter;

    @Inject
    IOAuthServiceAdapterFactory factory;
    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>, Object>();

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
        setContentView(webView);

        //beginAuthorization();
    }

    public void stopWebView() {
        webView.setVisibility(View.INVISIBLE);
        webView.stopLoading();
    }

    private void setUpWebView() {
        //WebViewClient webViewClient = new LoginWebViewClient();
        webView.clearCache(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //TODO see if can be disabled
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        //webView.setWebViewClient(webViewClient);
    }

    public void finishWithToken(Token token) {
        Intent data = new Intent();
        data.putExtra(getString(R.string.EXTRA_ACCESS_SECRET), token.getSecret());
        data.putExtra(getString(R.string.EXTRA_ACCESS_TOKEN), token.getToken());
        setResult(RESULT_OK, data);
        finish();
    }

    public void finishInShame() {
        setResult(RESULT_CANCELED);
        finish();
    }

}
