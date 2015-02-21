package com.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
            String accessToken = "", accessSecret = "", provider = "";
            Bundle extras = data.getExtras();
            switch (requestCode) {
                case FB_LOGIN_REQ: accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                    accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                    provider = getString(R.string.PROVIDER_FACEBOOK);
                    break;
                case TW_LOGIN_REQ: accessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                    accessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                    provider = getString(R.string.PROVIDER_TWITTER);
                    break;
                default: break;
            }
            Log.d("accessToken", accessToken);
            Log.d("accessSecret", accessSecret);
            Log.d("provider", provider);
            loginServer(accessToken, accessSecret, provider);
        }
    }

    public void loginServer(String accessToken, String accessSecret, String provider) {

    }

    public void loginTwitter(View v) {
        Intent intent = new Intent(this, TwitterLoginWebViewActivity.class);
        startActivityForResult(intent, TW_LOGIN_REQ);
    }

    public void loginFacebook(View v) {
        Intent intent = new Intent(this, FacebookLoginWebViewActivity.class);
        startActivityForResult(intent, FB_LOGIN_REQ);
    }
}
