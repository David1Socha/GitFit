package com.example.raik383h_group_6.healthtracmobile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;


public abstract class LoginWebViewActivity extends ActionBarActivity {

    private String apiKey, apiSecret, callbackUrl;
    private Class apiClass;
    private WebView webView;
    private OAuthService oAuthService;

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

    }

    public abstract void setOAuthFields();

}
