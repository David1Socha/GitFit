package com.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AuthenticationActivity extends Activity {
    public static final int CREATE_ACCOUNT=1,
    SIGN_IN=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void signIn(View v) {
        Intent intent = new Intent(this, LoginPromptActivity.class);
        startActivityForResult(intent, SIGN_IN);
    }
}
