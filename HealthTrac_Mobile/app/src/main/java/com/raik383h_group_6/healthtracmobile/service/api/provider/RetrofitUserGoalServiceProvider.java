package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.UserGoalService;

import retrofit.RestAdapter;

public class RetrofitUserGoalServiceProvider implements Provider<UserGoalService> {

    @Override
    public UserGoalService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(UserGoalService.DATA_CONVERTER)
                .setEndpoint(UserGoalService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(UserGoalService.class);
    }
}
