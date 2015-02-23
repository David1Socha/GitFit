package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;

import retrofit.RestAdapter;

public class RetrofitFacebookServiceProvider implements Provider<FacebookService>{
    @Override
    public FacebookService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(FacebookService.DATA_CONVERTER)
                .setEndpoint(FacebookService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(FacebookService.class);
    }
}
