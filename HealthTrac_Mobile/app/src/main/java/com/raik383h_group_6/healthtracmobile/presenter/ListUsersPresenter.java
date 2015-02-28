package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUsersPresenter {

    private static final int AUTH = 1;

    private IResources resources;
    private ActivityNavigator nav;
    private ListUsersActivity view;
    private UserService userService;
    private AccessGrant grant;

    @Inject
    public ListUsersPresenter(UserService userService, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted ListUsersActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.userService = userService;
        this.view = view;
        this.grant = null;
    }

    public void onCreate() {
        nav.openAuthentication(AUTH);
    }

    public void onResume() {
        populateUsers();
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case AUTH:
                    grant = (AccessGrant) data.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
                    populateUsers();
                    break;
                default:
                    break;
            }

        }
    }

    public void populateUsers() {
        List<User> users = null;
        try {
            if (grant == null) {
                view.setNoUsersMessageDisplay(true);
                return;
            }
            users = getUsersAsync(grant.getAuthHeader());
            if (users != null) {
                view.setNoUsersMessageDisplay(false);
                view.setUserListView(users);
            } else {
                view.setNoUsersMessageDisplay(true);
            }

        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
    }

    private List<User> getUsersAsync(String authHeader) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(String... params) {
                try {
                    return userService.getUsers(params[0]);
                } catch (Exception e) {
                    throw e;
                }
            }
        }.execute(authHeader).get();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }
}
