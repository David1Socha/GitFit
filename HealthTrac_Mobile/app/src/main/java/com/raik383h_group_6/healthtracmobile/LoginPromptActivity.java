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
            Bundle extras = data.getExtras();
            String provider;
            switch (requestCode) {
                case FB_LOGIN_REQ:
                    provider = getString(R.string.PROVIDER_FACEBOOK);
                    extras.putString(getString(R.string.EXTRA_PROVIDER), provider);
                    break;
                case TW_LOGIN_REQ:
                    provider = getString(R.string.PROVIDER_TWITTER);
                    extras.putString(getString(R.string.EXTRA_PROVIDER), provider);
                    break;
                default: break;
            }
        }
        finish(data);
    }

    private void finish(Intent data) {
        setResult(RESULT_OK, data);
        finish();
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
