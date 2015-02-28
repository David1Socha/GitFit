package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUsersPresenter {

    private IResources resources;
    private Navigator nav;
    private ListUsersActivity view;
    private UserService userService;
    private AccessGrant grant;

    @Inject
    public ListUsersPresenter(UserService userService, @Assisted IResources resources, @Assisted Navigator nav, @Assisted ListUsersActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.userService = userService;
        this.view = view;
        this.grant = null;
    }

    public void onCreate() {
        populateUsers();
    }

    public void populateUsers() {
        List<User> users = null;
        try {
            if (grant == null) {
                return;
            }
            users = getUsersAsync(grant.getAuthHeader());
        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
        view.setUserListView(users);
    }

    private List<User> getUsersAsync(String authHeader) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(String... params) {
                try {
                    return userService.getUsers(params[0]);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(authHeader).get();
    }
}
