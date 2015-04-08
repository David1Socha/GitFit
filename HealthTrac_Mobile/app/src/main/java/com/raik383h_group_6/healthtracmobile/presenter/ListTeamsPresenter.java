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
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;

import java.util.List;

public class ListTeamsPresenter extends BasePresenter{

    private IActivityNavigator nav;
    private ListTeamsView view;
    private IAsyncTeamService teamService;
    private AccessGrant grant;

    @Inject
    public ListTeamsPresenter(IAsyncTeamService teamService, @Assisted IActivityNavigator nav, @Assisted ListTeamsView view) {
        this.nav = nav;
        this.teamService = teamService;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onResume() {
        populateTeams();
    }

    private void populateTeams() {
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

    @Override
    protected BaseView getView() {
        return view;
    }


    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
