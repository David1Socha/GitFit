package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;

import retrofit.RestAdapter;

public class RetrofitTeamServiceProvider implements Provider<TeamService>{

    @Override
    public TeamService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(TeamService.DATA_CONVERTER)
                .setEndpoint(TeamService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(TeamService.class);
    }
}
