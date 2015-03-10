package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.FacebookUser;

import java.util.concurrent.ExecutionException;

public interface IAsyncFacebookService {
    FacebookUser getFacebookUserAsync(final String accessToken) throws InterruptedException, ExecutionException;
}
