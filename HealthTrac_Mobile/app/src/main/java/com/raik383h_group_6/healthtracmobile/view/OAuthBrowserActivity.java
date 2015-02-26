package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthBrowserPresenter;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthServiceAdapter;

import java.util.HashMap;
import java.util.Map;
import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

public class OAuthBrowserActivity extends RoboActionBarActivity {

    private WebView webView;
    private String provider;
    @Inject
    @Named("Facebook")
    private IOAuthServiceAdapter facebookOAuthService;
    @Inject
    @Named("Twitter")
    private IOAuthServiceAdapter twitterOAuthService;
    @Inject
    private OAuthBrowserPresenter presenter;
    private IOAuthServiceAdapter oAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        Bundle extras = getIntent().getExtras();
        IResources resources = new ResourcesAdapter(getResources());
        presenter.initialize(oAuthService, extras, resources, webView, this);
        presenter.onViewCreate();
    }

}
