package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;

import java.util.concurrent.ExecutionException;

public interface IAsyncAccountService {
    void registerAccountAsync(UserLogin userLogin) throws Exception;
    AccessGrant loginAsync(Credentials credentials) throws InterruptedException, ExecutionException;
}
