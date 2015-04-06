package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.UserBadgeService;

import retrofit.RestAdapter;

public class RetrofitUserBadgeServiceProvider implements Provider<UserBadgeService> {

    @Override
    public UserBadgeService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(UserBadgeService.DATA_CONVERTER)
                .setEndpoint(UserBadgeService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(UserBadgeService.class);
    }
}
