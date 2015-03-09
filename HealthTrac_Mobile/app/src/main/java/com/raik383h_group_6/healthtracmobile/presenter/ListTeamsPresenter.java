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
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListTeamsPresenter {

    private static final int AUTH = 1;

    private IResources resources;
    private IActivityNavigator nav;
    private ListTeamsView view;
    private IAsyncTeamService teamService;
    private AccessGrant grant;
    private Bundle extras;

    @Inject
    public ListTeamsPresenter(IAsyncTeamService teamService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted ListTeamsView view) {
        this.resources = resources;
        this.nav = nav;
        this.teamService = teamService;
        this.view = view;
        this.extras = extras;
        this.grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
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
            teams = teamService.getTeamsAsync(grant.getAuthHeader());
            if (teams != null) {
                view.setNoTeamsMessageDisplay(false);
                view.setTeamsList(teams);
            } else {
                view.setNoTeamsMessageDisplay(true);
            }
        } catch (Exception e) {
            view.setNoTeamsMessageDisplay(true);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Team t = (Team) parent.getAdapter().getItem(position);
        nav.openViewTeam(t, grant);
    }
}
