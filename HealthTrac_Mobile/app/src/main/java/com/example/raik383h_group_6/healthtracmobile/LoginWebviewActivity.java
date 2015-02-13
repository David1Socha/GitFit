package com.example.raik383h_group_6.healthtracmobile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class LoginWebviewActivity extends ActionBarActivity {

    private String apiClassStr, apiKey, apiSecret, callbackUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClassStr = getIntent().getStringExtra(LoginPromptActivity.API_CLASS_STR);
        apiKey = getIntent().getStringExtra(LoginPromptActivity.API_KEY);
        apiSecret = getIntent().getStringExtra(LoginPromptActivity.API_SECRET);
        callbackUrl = getIntent().getStringExtra(LoginPromptActivity.CALLBACK_URL);
        WebView webview = new WebView(this);
        setContentView(webview);
    }

}
