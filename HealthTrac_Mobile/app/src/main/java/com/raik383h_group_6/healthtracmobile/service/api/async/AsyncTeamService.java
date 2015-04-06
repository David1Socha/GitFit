package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.service.oauth.AsyncOAuthService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncTeamService implements IAsyncTeamService {

    TeamService teamService;

    @Inject
    public AsyncTeamService(TeamService service) {
        teamService = service;
    }

    @Override
    public List<Team> getTeamsAsync(String authHeader) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, List<Team>>() {
            @Override
            protected List<Team> doInBackground(String... params) {
                return teamService.getTeams(params[0]);
            }
        }.execute(authHeader).get();
    }
}
