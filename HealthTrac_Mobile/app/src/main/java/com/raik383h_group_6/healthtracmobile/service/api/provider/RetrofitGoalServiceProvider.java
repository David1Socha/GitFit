package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.GoalService;

import retrofit.RestAdapter;

public class RetrofitGoalServiceProvider implements Provider<GoalService> {

    @Override
    public GoalService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(GoalService.DATA_CONVERTER)
                .setEndpoint(GoalService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(GoalService.class);
    }
}
