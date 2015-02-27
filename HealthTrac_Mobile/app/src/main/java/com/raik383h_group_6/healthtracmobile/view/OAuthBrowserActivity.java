package com.raik383h_group_6.healthtracmobile.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.Navigator;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthBrowserPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;

import roboguice.activity.RoboActionBarActivity;

public class OAuthBrowserActivity extends RoboActionBarActivity {

    private WebView webView;
    @Inject
    @Named("Facebook")
    private IOAuthService facebookOAuthService;
    @Inject
    @Named("Twitter")
    private IOAuthService twitterOAuthService;
    @Inject
    private PresenterFactory presenterFactory;
    private OAuthBrowserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        Bundle extras = getIntent().getExtras();
        IResources resources = new ResourcesAdapter(getResources());
        IOAuthService oAuthService;
        if (extras.getString(getString(R.string.EXTRA_PROVIDER)).equals(getString(R.string.PROVIDER_FACEBOOK))) {
            oAuthService = facebookOAuthService;
        } else {
            oAuthService = twitterOAuthService;
        }
        Navigator nav = new Navigator(this);
        presenter = presenterFactory.create(oAuthService, webView, extras, resources, nav, this);
        presenter.onCreate();
    }

}
