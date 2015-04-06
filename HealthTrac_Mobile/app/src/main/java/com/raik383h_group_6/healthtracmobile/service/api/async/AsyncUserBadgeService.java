package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.service.api.UserBadgeService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncUserBadgeService implements IAsyncUserBadgeService {

    private UserBadgeService service;

    @Inject
    public AsyncUserBadgeService(UserBadgeService service) {
        this.service = service;
    }

    @Override
    public UserBadge getUserBadge(final long id, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserBadge>() {
            @Override
            protected UserBadge doInBackground(Void... params) {
                try {
                    return service.getUserBadge(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public UserBadge getUserBadge(final long badgeId, final String userId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserBadge>() {
            @Override
            protected UserBadge doInBackground(Void... params) {
                try {
                    return service.getUserBadge(userId, badgeId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserBadge> getUserBadges(final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserBadge>>() {
            @Override
            protected List<UserBadge> doInBackground(Void... params) {
                try {
                    return service.getUserBadges(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserBadge> getUserBadges(final long badgeId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserBadge>>() {
            @Override
            protected List<UserBadge> doInBackground(Void... params) {
                try {
                    return service.getUserBadges(badgeId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserBadge> getUserBadges(final String userId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserBadge>>() {
            @Override
            protected List<UserBadge> doInBackground(Void... params) {
                try {
                    return service.getUserBadges(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public UserBadge createUserBadge(final UserBadge userBadge, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserBadge>() {
            @Override
            protected UserBadge doInBackground(Void... params) {
                try {
                    return service.createUserBadge(userBadge, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
