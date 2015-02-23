package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthBrowserPresenter;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthServiceAdapter;

import java.util.HashMap;
import java.util.Map;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

public class OAuthBrowserActivity extends ActionBarActivity implements RoboContext {

    private WebView webView;
    private String provider;
    @Inject private OAuthBrowserPresenter presenter;
    private IOAuthServiceAdapter oAuthService;

    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>, Object>();

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        provider = getIntent().getStringExtra(getString(R.string.EXTRA_PROVIDER));
        injectMembers();
        webView = new WebView(this);
        setContentView(webView);
        presenter.initialize(oAuthService, provider, webView, this);
        presenter.onViewCreate();
    }

    private void injectMembers() {
        RoboInjector injector = RoboGuice.getInjector(this);
        oAuthService = resolveOAuthService(injector);
        injector.injectMembersWithoutViews(this);
    }

    private IOAuthServiceAdapter resolveOAuthService(RoboInjector injector) {

        String facebookStr = getString(R.string.PROVIDER_FACEBOOK);
        String twitterStr = getString(R.string.PROVIDER_TWITTER);

        if (provider.equals(facebookStr)) {
            return injector.getInstance(Key.get(IOAuthServiceAdapter.class, Names.named(facebookStr)));
        } else {
            return injector.getInstance(Key.get(IOAuthServiceAdapter.class, Names.named(twitterStr)));
        }
    }

}
