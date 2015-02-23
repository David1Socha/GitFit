package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;

import retrofit.RestAdapter;

public class RetrofitUserServiceProvider implements Provider<UserService> {

    @Override
    public UserService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(UserService.DATA_CONVERTER)
                .setEndpoint(UserService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(UserService.class);
    }
}
