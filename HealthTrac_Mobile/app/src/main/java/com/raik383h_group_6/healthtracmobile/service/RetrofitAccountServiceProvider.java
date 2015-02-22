package com.raik383h_group_6.healthtracmobile.service;

import com.google.inject.Provider;

import retrofit.RestAdapter;
import retrofit.client.Client;

public class RetrofitAccountServiceProvider implements Provider<AccountService>{

    @Override
    public AccountService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(AccountService.DATA_CONVERTER)
                .setEndpoint(AccountService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(AccountService.class);
    }
}
