package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.client.Response;

public interface IAsyncUserGoalService {
    UserGoal getUserGoal(long id, String token) throws InterruptedException, ExecutionException;
    UserGoal getUserGoal(long goalId, String userId, String token) throws InterruptedException, ExecutionException;
    List<UserGoal> getUserGoals(String token)  throws InterruptedException, ExecutionException;
    List<UserGoal> getUserGoals(long goalId, String token)  throws InterruptedException, ExecutionException;
    List<UserGoal> getUserGoals(String userId, String token) throws InterruptedException, ExecutionException;
    UserGoal createUserGoal(UserGoal userGoal, String token) throws InterruptedException, ExecutionException;
    void updateUserGoal(long id, UserGoal userGoal, String token) throws Exception;
}
