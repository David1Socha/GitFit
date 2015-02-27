package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;

import roboguice.inject.ContentView;

public class Navigator {
    private final Activity activity;

    @Inject
    public Navigator(@Assisted Activity activity) {
        this.activity = activity;
    }

    public void openOAuthBrowser(final String provider, final int reqCode) {
        Intent intent = new Intent(activity, OAuthBrowserActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
        activity.startActivityForResult(intent, reqCode);
    }
}
