package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.UserBadge;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncUserBadgeService {

    UserBadge getUserBadge(long id, String token) throws InterruptedException, ExecutionException;
    UserBadge getUserBadge(long badgeId, String userId, String token) throws InterruptedException, ExecutionException;
    List<UserBadge> getUserBadges(String token)  throws InterruptedException, ExecutionException;
    List<UserBadge> getUserBadges(long badgeId, String token)  throws InterruptedException, ExecutionException;
    List<UserBadge> getUserBadges(String userId, String token) throws InterruptedException, ExecutionException;
    UserBadge createUserBadge(UserBadge userBadge, String token) throws InterruptedException, ExecutionException;
}
