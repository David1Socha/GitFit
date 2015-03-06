package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthBrowserPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.oauth.IOAuthService;

import roboguice.activity.RoboActionBarActivity;

public class OAuthBrowserActivity extends CustomRoboActionBarActivity {

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
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(oAuthService, webView, extras, resources, nav);
        presenter.onCreate();
    }
}