package com.raik383h_group_6.healthtracmobile.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.GitFitMainPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.git_fit_main_layout)
public class GitFitMainActivity extends RoboActionBarActivity {

    @InjectView(R.id.show_users_button)
    Button showUsersButton;
    @InjectView(R.id.show_teams_button)
    Button showTeamsButton;
    @InjectView(R.id.show_profile_button)
    Button showProfileButton;
    @Inject
    PresenterFactory presenterFactory;
    private SharedPreferences prefs;
    private GitFitMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        IActivityNavigator nav = new ActivityNavigator(this);
        prefs = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE);
        presenter = presenterFactory.create(resources, nav, this);
        presenter.onCreate(savedInstanceState);
    }

    public String getPref(String key) {
        return prefs.getString(key, null);
    }

    public void setPref(String key, String val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, val);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = null;
        if (data != null) {
            extras = data.getExtras();
        }
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    public void onClickShowProfile(View view) {
        presenter.onClickShowProfile();
    }

    public void onClickShowUsers(View view) {
        presenter.onClickShowUsers();
    }

    public void onClickShowTeams(View view) {
        presenter.onClickShowTeams();
    }

    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
