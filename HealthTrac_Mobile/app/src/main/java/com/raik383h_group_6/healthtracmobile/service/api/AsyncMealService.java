package com.raik383h_group_6.healthtracmobile.service.api;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMealService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncMealService implements IAsyncMealService {

    private MealService service;

    @Inject
    public AsyncMealService(MealService service) {
        this.service = service;
    }

    @Override
    public Meal getMeal(final long id, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, Meal>() {
            @Override
            protected Meal doInBackground(Void... params) {
                try {
                    return service.getMeal(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Meal> getMeals(final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<Meal>>() {
            @Override
            protected List<Meal> doInBackground(Void... params) {
                try {
                    return service.getMeals(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Meal> getMeals(final String userId, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, List<Meal>>() {
            @Override
            protected List<Meal> doInBackground(Void... params) {
                try {
                    return service.getMeals(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public Meal createMeal(final Meal meal, final String token) throws InterruptedException, ExecutionException {
        return new AsyncTask<Void, Void, Meal>() {
            @Override
            protected Meal doInBackground(Void... params) {
                try {
                    return service.createMeal(meal, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
