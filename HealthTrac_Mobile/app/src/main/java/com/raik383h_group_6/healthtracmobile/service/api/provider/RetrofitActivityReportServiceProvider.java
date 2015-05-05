package com.raik383h_group_6.healthtracmobile.service.api.provider;

import com.google.inject.Provider;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityReportService;

import retrofit.RestAdapter;

public class RetrofitActivityReportServiceProvider implements Provider<ActivityReportService> {
    @Override
    public ActivityReportService get() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setConverter(ActivityReportService.DATA_CONVERTER).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ActivityReportService.SERVICE_ENDPOINT)
                .build();
        return adapter.create(ActivityReportService.class);
    }
}
