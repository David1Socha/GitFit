package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.git_fit_main_layout)
public class GitFitMainActivity extends RoboActivity{

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = null;
        if (data != null) {
            extras = data.getExtras();
        }
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

}
