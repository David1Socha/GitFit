package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity
        .TwitterLoginButton;

import com.facebook.Session;
import com.facebook.SessionState;

public class LoginPromptActivity extends Activity {

    private Button fbLoginButton, twLoginButton;
    private String fbAccessToken, twAccessToken, twAccessSecret;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        fbLoginButton = (Button) findViewById(R.id.fb_login_button);
        twLoginButton = (Button) findViewById(R.id.tw_login_button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO monitor
    }

}