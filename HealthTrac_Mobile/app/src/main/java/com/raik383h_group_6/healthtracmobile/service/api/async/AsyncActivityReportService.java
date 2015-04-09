package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityReportService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncActivityReportService implements IAsyncActivityReportService {

    private ActivityReportService svc;

    @Inject
    public AsyncActivityReportService(ActivityReportService svc) {
        this.svc = svc;
    }

    @Override
    public ActivityReport getActivityReportAsync(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, ActivityReport>() {
            @Override
            protected ActivityReport doInBackground(Void... params) {
                try {
                    return svc.getActivityReport(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<ActivityReport> getActivityReportsAsync(final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<ActivityReport>>() {
            @Override
            protected List<ActivityReport> doInBackground(Void... params) {
                try {
                    return svc.getActivityReports(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<ActivityReport> getActivityReportsAsync(final String userId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<ActivityReport>>() {
            @Override
            protected List<ActivityReport> doInBackground(Void... params) {
                try {
                    return svc.getActivityReports(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
