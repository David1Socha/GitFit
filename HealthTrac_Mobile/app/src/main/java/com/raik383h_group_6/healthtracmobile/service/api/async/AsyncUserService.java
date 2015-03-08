package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncUserService implements IAsyncUserService{

    private final UserService service;

    @Inject
    public AsyncUserService(UserService service) {
        this.service = service;
    }

    @Override
    public User getUserAsync(final String id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... params) {
                try {
                    return service.getUser(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<User> getUsersAsync(final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                try {
                    return service.getUsers(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public void updateUserAsync(final String id, final User user, final String token) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.updateUser(id, user, token);
                } catch (Exception ex) {
                    errs[0] = ex;
                }
                return null;
            }
        }.execute().get();
        if (errs[0] != null) {
            throw errs[0];
        }
    }

}
