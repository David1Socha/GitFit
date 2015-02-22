package com.raik383h_group_6.healthtracmobile.service;

import retrofit.RestAdapter;
import retrofit.client.Client;

public class AccountServiceProvider implements Client.Provider{

    @Override
    public Client get() {
        return new RestAdapter.Builder()
                .setConverter()
    }
}
