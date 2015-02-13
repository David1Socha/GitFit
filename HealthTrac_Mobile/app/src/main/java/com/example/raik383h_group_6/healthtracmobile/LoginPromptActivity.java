package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginPromptActivity extends Activity {

    public static final String API_CLASS_STR = "com.example.raik383h_group_6.healthtracmobile.API_CLASS_STR",
            API_KEY = "com.example.raik383h_group_6.healthtracmobile.API_KEY",
            API_SECRET = "com.example.raik383h_group_6.healthtracmobile.API_SECRET",
            CALLBACK_URL =  "com.example.raik383h_group_6.healthtracmobile.CALLBACK_URL";
    private Button fbLoginButton, twLoginButton;
    private String fbAccessToken, twAccessToken, twAccessSecret;

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
        //TODO monitor
    }

    public void loginTwitter(View v) {
        //TODO launch twitter login activity
        Log.i("davidsocha", "to be added");
    }

    public void loginFacebook(View v) {
        //TODO launch facebook login activity
        Log.i("davidsocha", "to be added");
    }

}