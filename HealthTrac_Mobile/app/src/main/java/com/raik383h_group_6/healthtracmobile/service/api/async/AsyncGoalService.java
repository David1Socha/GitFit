package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.service.api.GoalService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncGoalService implements IAsyncGoalService {

    private GoalService service;

    public AsyncGoalService(GoalService service) {
        this.service = service;
    }

    @Override
    public Goal getGoal(final long id, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, Goal>() {
            @Override
            protected Goal doInBackground(Void... params) {
                try {
                    return service.getGoal(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Goal> getGoals(final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<Goal>>() {
            @Override
            protected List<Goal> doInBackground(Void... params) {
                try {
                    return service.getGoals(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public Goal createGoal(final Goal goal, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, Goal>() {
            @Override
            protected Goal doInBackground(Void... params) {
                try {
                    return service.createGoal(goal, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
