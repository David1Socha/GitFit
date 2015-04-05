package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityService;

import retrofit.RestAdapter;

public class RetrofitActivityServiceProvider implements Provider<ActivityService> {

    @Override
    public ActivityService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(ActivityService.DATA_CONVERTER)
                .setEndpoint(ActivityService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(ActivityService.class);
    }
}
