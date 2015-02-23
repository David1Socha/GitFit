package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.raik383h_group_6.healthtracmobile.R;


public class AuthenticationActivity extends Activity {
    public static final int CREATE_ACCOUNT = 1,
            SIGN_IN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SIGN_IN:
                    Log.d("Result", "Signed in");
                    break;
                case CREATE_ACCOUNT:
                    Log.d("Result", "Created account");
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickSignIn(View v) {
        Intent intent = new Intent(this, OAuthPromptActivity.class);
        startActivityForResult(intent, SIGN_IN);
    }

    public void onClickCreateAccount(View v) {
        //TODO
    }
}
