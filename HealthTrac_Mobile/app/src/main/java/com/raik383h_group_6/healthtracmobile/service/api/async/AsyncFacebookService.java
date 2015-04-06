package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;

import java.util.concurrent.ExecutionException;

public class AsyncFacebookService implements IAsyncFacebookService {

    private final FacebookService facebookService;

    @Inject
    public AsyncFacebookService(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @Override
    public FacebookUser getFacebookUserAsync(final String accessToken) throws InterruptedException, ExecutionException {
        return new AsyncTask<String, Void, FacebookUser>() {
            @Override protected FacebookUser doInBackground(String... params) {
                try {
                    return facebookService.getUser(FacebookService.AUTH_PREFIX + accessToken);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(accessToken).get();
    }
}
