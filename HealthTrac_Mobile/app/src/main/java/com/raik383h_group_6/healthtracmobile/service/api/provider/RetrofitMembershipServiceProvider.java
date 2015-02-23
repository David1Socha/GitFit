package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;

import retrofit.RestAdapter;

public class RetrofitMembershipServiceProvider implements Provider<MembershipService> {
    @Override
    public MembershipService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(MembershipService.DATA_CONVERTER)
                .setEndpoint(MembershipService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(MembershipService.class);
    }
}
