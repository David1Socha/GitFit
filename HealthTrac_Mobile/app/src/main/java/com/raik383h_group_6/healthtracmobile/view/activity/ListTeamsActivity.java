package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.TeamAdapter;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.presenter.ListTeamsPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_list_teams)
public class ListTeamsActivity extends CustomRoboActionBarActivity implements ListTeamsView {
    @InjectView(R.id.team_list_view)
    ListView teamListView;
    @InjectView(R.id.no_teams_textview)
    TextView noTeamsTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ListTeamsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        IActivityNavigator nav = new ActivityNavigator(this);
        Bundle extras = getIntent().getExtras();
        presenter = presenterFactory.create(extras, resources, nav, this);
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        teamListView.setAdapter(adapter);
    }

    @Override
    public void setNoTeamsMessageDisplay(boolean enabled) {
        if (enabled) {
            noTeamsTextView.setVisibility(View.VISIBLE);
        } else {
            noTeamsTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTeamsList(List<Team> teams) {
        TeamAdapter adapter = new TeamAdapter(this, teams);
        teamListView.setAdapter(adapter);
    }

    @Override
    public void onMenuLogout() {

    }
}
