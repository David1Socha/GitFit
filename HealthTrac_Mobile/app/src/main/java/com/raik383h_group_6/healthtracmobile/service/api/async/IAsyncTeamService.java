package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Team;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncTeamService {
    List<Team> getTeamsAsync(String authHeader) throws ExecutionException, InterruptedException;

    Team createTeamAsync(Team team, String authHeader) throws ExecutionException, InterruptedException;

    void updateTeamAsync(long id, Team team, String token) throws Exception;

}
