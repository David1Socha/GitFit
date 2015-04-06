package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.service.api.ActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.BadgeService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncBadgeService implements IAsyncBadgeService{

    private BadgeService service;

    @Inject
    public AsyncBadgeService(BadgeService service) {
        this.service = service;
    }

    @Override
    public Badge getBadgeAsync(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Badge>() {
            @Override
            protected Badge doInBackground(Void... params) {
                try {
                    return service.getBadge(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Badge> getBadgesAsync( final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Badge>>() {
            @Override
            protected List<Badge> doInBackground(Void... params) {
                try {
                    return service.getBadges(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

}
