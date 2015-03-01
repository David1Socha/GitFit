package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.TeamAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListTeamsPresenter {

    private static final int AUTH = 1;

    private IResources resources;
    private ActivityNavigator nav;
    private ListTeamsActivity view;
    private TeamService teamService;
    private AccessGrant grant;
    private Bundle extras;

    @Inject
    public ListTeamsPresenter(TeamService teamService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted ListTeamsActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.teamService = teamService;
        this.view = view;
        this.extras = extras;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        if (savedInstanceState != null) {
            this.grant = savedInstanceState.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT), grant);
    }

    public void onResume() {
        populateTeams();
    }

    public void populateTeams() {
        List<Team> teams = null;
        try {
            if (grant == null || grant.isExpired()) {
                view.setNoTeamsMessageDisplay(true);
                return;
            }
            teams = getTeamsAsync(grant.getAuthHeader());
            if (teams != null) {
                view.setNoTeamsMessageDisplay(false);
                ListAdapter adapter = new TeamAdapter(view, teams);
                view.setListAdapter(adapter);
            } else {
                view.setNoTeamsMessageDisplay(true);
            }
        } catch (ExecutionException | InterruptedException e) {
            view.setNoTeamsMessageDisplay(true);
        }
    }

    private List<Team> getTeamsAsync(String authHeader) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, List<Team>>() {
            @Override
            protected List<Team> doInBackground(String... params) {
                try {
                    return teamService.getTeams(params[0]);
                } catch (Exception e) {
                    throw e;
                }
            }
        }.execute(authHeader).get();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Team t = (Team) parent.getAdapter().getItem(position);
        //nav.openViewTeam(Team, grant); TODO
    }
}
