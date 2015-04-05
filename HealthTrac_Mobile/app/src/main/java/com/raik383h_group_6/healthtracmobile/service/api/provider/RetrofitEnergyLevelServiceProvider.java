package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.EnergyLevelService;

import retrofit.RestAdapter;

public class RetrofitEnergyLevelServiceProvider implements Provider<EnergyLevelService> {

    @Override
    public EnergyLevelService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(EnergyLevelService.DATA_CONVERTER)
                .setEndpoint(EnergyLevelService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(EnergyLevelService.class);
    }
}
