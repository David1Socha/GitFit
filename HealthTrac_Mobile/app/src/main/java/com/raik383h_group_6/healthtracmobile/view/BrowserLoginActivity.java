package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
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
    BrowserLoginPresenter browserLoginPresenter; //TODO dynamically inject based on REQ_CODE

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
        browserLoginPresenter.setUpWebView(webView);
        browserLoginPresenter.initialize(factory, this);
        browserLoginPresenter.beginAuthorization();
    }

    public void setView(View v) {
        setContentView(v);
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
