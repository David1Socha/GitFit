package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Goal;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncGoalService {
    Goal getGoal(long id, String token) throws InterruptedException, ExecutionException;
    List<Goal> getGoals(String token) throws InterruptedException, ExecutionException;
    Goal createGoal(Goal goal, String token) throws InterruptedException, ExecutionException;
}
