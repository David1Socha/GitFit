package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Activity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncActivityService {
    Activity getActivityAsync( long id, String token) throws ExecutionException, InterruptedException;

    List<Activity> getActivitiesAsync(String userId, String token) throws ExecutionException, InterruptedException;

    void updateActivityAsync(long id, Activity activity, String token) throws Exception;

    Activity createActivityAsync( Activity activity, String token) throws ExecutionException, InterruptedException;

    void deleteActivityAsync(long id, String token) throws Exception;
}
