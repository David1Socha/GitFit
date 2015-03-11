package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.webkit.WebView;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.OAuthBrowserPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.oauth.IAsyncOAuthService;

public class OAuthBrowserActivity extends BaseActivity {

    private WebView webView;
    @Inject
    @Named("FacebookAsync")
    private IAsyncOAuthService asyncFacebookOAuthService;
    @Inject
    @Named("TwitterAsync")
    private IAsyncOAuthService asyncTwitterOAuthService;
    @Inject
    private PresenterFactory presenterFactory;
    private OAuthBrowserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        Bundle extras = getIntent().getExtras();
        IAsyncOAuthService oAuthService;
        if (extras.getString(getString(R.string.EXTRA_PROVIDER)).equals(getString(R.string.PROVIDER_FACEBOOK))) {
            oAuthService = asyncFacebookOAuthService;
        } else {
            oAuthService = asyncTwitterOAuthService;
        }
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(oAuthService, webView, extras, nav, this);
        presenter.onCreate();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
