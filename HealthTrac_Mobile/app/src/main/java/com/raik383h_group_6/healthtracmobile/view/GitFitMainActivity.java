package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.GitFitMainPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.git_fit_main_layout)
public class GitFitMainActivity extends RoboActionBarActivity{
    private GitFitMainPresenter presenter;
    @Inject
    PresenterFactory presenterFactory;
    @InjectView(R.id.show_users_button)
    Button showUsersButton;
    @InjectView(R.id.show_teams_button)
    Button showTeamsButton;
    @InjectView(R.id.view_profile_button)
    Button viewProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IResources resources = new ResourcesAdapter(getResources());
        ActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(resources, nav, this);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
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

}
