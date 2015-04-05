package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Meal;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncMealService {
    Meal getMeal(long id, String token) throws InterruptedException, ExecutionException;
    List<Meal> getMeals(String token) throws InterruptedException, ExecutionException;
    List<Meal> getMeals(String userId, String token) throws InterruptedException, ExecutionException;
    Meal createMeal(Meal meal, String token) throws InterruptedException, ExecutionException;
}
