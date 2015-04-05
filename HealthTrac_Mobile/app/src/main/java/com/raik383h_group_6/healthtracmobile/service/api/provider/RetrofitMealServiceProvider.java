package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.MealService;

import retrofit.RestAdapter;

public class RetrofitMealServiceProvider implements Provider<MealService> {

    @Override
    public MealService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(MealService.DATA_CONVERTER)
                .setEndpoint(MealService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(MealService.class);
    }
}
