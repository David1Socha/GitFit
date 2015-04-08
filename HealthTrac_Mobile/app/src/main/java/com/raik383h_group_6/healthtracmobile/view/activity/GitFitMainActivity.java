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
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.GitFitMainPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.git_fit_main_layout)
public class GitFitMainActivity extends BaseActivity implements com.raik383h_group_6.healthtracmobile.view.GitFitMainView {

    @Inject
    PresenterFactory presenterFactory;
    private GitFitMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
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

    public void onClickPedometerDemo(View view) { presenter.onClickPedometer(); }

    public void onClickUserFeed(View view) {
        presenter.onClickUserFeed();
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
