package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncActivityService implements IAsyncActivityService {

    private ActivityService service;

    @Inject
    public AsyncActivityService(ActivityService service) {
        this.service = service;
    }

    @Override
    public Activity getActivityAsync(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Activity>() {
            @Override
            protected Activity doInBackground(Void... params) {
                try {
                    return service.getActivity(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Activity> getActivitiesAsync(final String userId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Activity>>() {
            @Override
            protected List<Activity> doInBackground(Void... params) {
                try {
                    return service.getActivities(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Activity> getActivitiesAsync(final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Activity>>() {
            @Override
            protected List<Activity> doInBackground(Void... params) {
                try {
                    return service.getActivities(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public void updateActivityAsync(final long id, final Activity activity, final String token) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.updateActivity(id, activity, token);
                } catch (Exception ex) {
                    errs[0] = ex;
                }
                return null;
            }
        }.execute().get();
        if (errs[0] != null) {
            throw errs[0];
        }
    }

    @Override
    public Activity createActivityAsync(final Activity activity, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Activity>() {
            @Override
            protected Activity doInBackground(Void... params) {
                try {
                    return service.createActivity(activity, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public void deleteActivityAsync(final long id, final String token) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.deleteActivity(id, token);
                } catch (Exception ex) {
                    errs[0] = ex;
                }
                return null;
            }
        }.execute().get();
        if (errs[0] != null) {
            throw errs[0];
        }
    }

}
