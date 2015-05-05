package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Badge;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncActivityReportService {
    ActivityReport getActivityReportAsync( long id, String token) throws ExecutionException, InterruptedException;

    List<ActivityReport> getActivityReportsAsync(final String token) throws ExecutionException, InterruptedException;
    List<ActivityReport> getActivityReportsAsync(final String userId, final String token) throws ExecutionException, InterruptedException;
}
