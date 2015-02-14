package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginPromptActivity extends Activity {

    private static final int FB_LOGIN_REQ = 1,
            TW_LOGIN_REQ = 2;
    private Button fbLoginButton, twLoginButton;
    private String fbAccessToken, twAccessToken, twAccessSecret, fbAccessSecret;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_prompt_activity);
        fbLoginButton = (Button) findViewById(R.id.fb_login_button);
        twLoginButton = (Button) findViewById(R.id.tw_login_button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        switch (requestCode) {
            case FB_LOGIN_REQ: fbAccessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                fbAccessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                Log.d("fb access token", fbAccessToken);
                Log.d("fb access secret", fbAccessSecret);
                break;
            case TW_LOGIN_REQ: twAccessToken = extras.getString(getString(R.string.EXTRA_ACCESS_TOKEN));
                twAccessSecret = extras.getString(getString(R.string.EXTRA_ACCESS_SECRET));
                Log.d("tw access token", twAccessToken);
                Log.d("tw access secret", twAccessSecret);
                break;
            default: break;
        }
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
