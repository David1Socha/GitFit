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

public class LoginActivity extends Activity {

    private TextView textInstructionsOrLink;
    private Button fbLoginButton;
    private Session.StatusCallback fbStatusCallback = new SessionStatusCallback();
    private String fbAccessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        fbLoginButton = (Button)findViewById(R.id.fb_login_button);

        setUpSession(savedInstanceState);
        updateView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(fbStatusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(fbStatusCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }

    private void setUpSession(Bundle savedInstanceState) {
        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, fbStatusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this);
            }
            Session.setActiveSession(session);
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this).setCallback(fbStatusCallback));
            }
        }
    }

    private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {
            fbLoginButton.setText(R.string.facebook_logout);
            fbLoginButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    onClickLogout();
                }
            });
        } else {
            fbLoginButton.setText(R.string.facebook_login);
            fbLoginButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    onClickLogin();
                }
            });
        }
    }

    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(fbStatusCallback));
        } else {
            Session.openActiveSession(this, true, fbStatusCallback);
        }
    }

    private void onClickLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
        }
    }

    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            fbAccessToken = session.getAccessToken();
            Log.d("access_token", fbAccessToken);
            updateView();
        }
    }
}