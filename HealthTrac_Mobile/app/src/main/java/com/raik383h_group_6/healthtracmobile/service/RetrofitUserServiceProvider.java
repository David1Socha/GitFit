package com.raik383h_group_6.healthtracmobile.service;

import com.google.inject.Provider;

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
