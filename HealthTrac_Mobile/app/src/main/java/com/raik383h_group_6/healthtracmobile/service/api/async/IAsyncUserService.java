package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncUserService {
    User getUserAsync(String id, String token) throws ExecutionException, InterruptedException;

    List<User> getUsersAsync(String token) throws ExecutionException, InterruptedException;

    void updateUserAsync(String id, User user, String token) throws Exception;

    boolean isAvailable(String username) throws Exception;
}
