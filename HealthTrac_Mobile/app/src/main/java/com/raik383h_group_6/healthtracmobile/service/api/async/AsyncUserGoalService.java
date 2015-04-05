package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;
import com.raik383h_group_6.healthtracmobile.service.api.UserGoalService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.client.Response;

public class AsyncUserGoalService implements IAsyncUserGoalService {

    private UserGoalService service;

    public AsyncUserGoalService(UserGoalService service) {
        this.service = service;
    }

    @Override
    public UserGoal getUserGoal(final long id, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserGoal>() {
            @Override
            protected UserGoal doInBackground(Void... params) {
                try {
                    return service.getUserGoal(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public UserGoal getUserGoal(final long goalId, final String userId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserGoal>() {
            @Override
            protected UserGoal doInBackground(Void... params) {
                try {
                    return service.getUserGoal(userId, goalId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserGoal> getUserGoals(final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserGoal>>() {
            @Override
            protected List<UserGoal> doInBackground(Void... params) {
                try {
                    return service.getUserGoals(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserGoal> getUserGoals(final long goalId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserGoal>>() {
            @Override
            protected List<UserGoal> doInBackground(Void... params) {
                try {
                    return service.getUserGoals(goalId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<UserGoal> getUserGoals(final String userId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<UserGoal>>() {
            @Override
            protected List<UserGoal> doInBackground(Void... params) {
                try {
                    return service.getUserGoals(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public UserGoal createUserGoal(final UserGoal userGoal, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, UserGoal>() {
            @Override
            protected UserGoal doInBackground(Void... params) {
                try {
                    return service.createUserGoal(userGoal, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public void updateUserGoal(final long id, final UserGoal userGoal, final String token) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.updateUserGoal(id, userGoal, token);
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
