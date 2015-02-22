package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.raik383h_group_6.healthtracmobile.R;

public class LoginPromptActivity extends Activity {

    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_prompt);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();
            String accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
            String accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
            String provider = extras.getString(getString(R.string.EXTRA_PROVIDER));
            Log.d("accessToken", accessToken);
            Log.d("accessSecret", accessSecret);
            Log.d("provider", provider);
            loginServer(accessToken, accessSecret, provider);
        }
    }

    public void loginServer(String accessToken, String accessSecret, String provider) {
        //TODO
    }

    public void loginTwitter(View v) {
        Intent intent = new Intent(this, BrowserLoginActivity.class);
        intent.putExtra(getString(R.string.EXTRA_PROVIDER), getString(R.string.PROVIDER_TWITTER));
        startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void loginFacebook(View v) {
        Intent intent = new Intent(this, BrowserLoginActivity.class);
        intent.putExtra(getString(R.string.EXTRA_PROVIDER), getString(R.string.PROVIDER_FACEBOOK));
        startActivityForResult(intent, FB_LOGIN_REQ);
    }
}
