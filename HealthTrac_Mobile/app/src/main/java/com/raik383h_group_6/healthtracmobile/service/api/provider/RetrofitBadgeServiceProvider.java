package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.BadgeService;

import retrofit.RestAdapter;

public class RetrofitBadgeServiceProvider implements Provider<BadgeService> {

    @Override
    public BadgeService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(BadgeService.DATA_CONVERTER)
                .setEndpoint(BadgeService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(BadgeService.class);
    }
}
