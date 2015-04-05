package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.PointService;

import retrofit.RestAdapter;

public class RetrofitPointServiceProvider implements Provider<PointService> {

    @Override
    public PointService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(PointService.DATA_CONVERTER)
                .setEndpoint(PointService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(PointService.class);
    }
}
