package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUsersPresenter {

    private static final int AUTH = 1;

    private IResources resources;
    private IActivityNavigator nav;
    private ListUsersActivity view;
    private UserService userService;
    private AccessGrant grant;
    private Bundle extras;

    @Inject
    public ListUsersPresenter(UserService userService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted ListUsersActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.userService = userService;
        this.view = view;
        this.extras = extras;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        if (savedInstanceState != null) {
            this.grant = savedInstanceState.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT), grant);
    }

    public void onResume() {
        populateUsers();
    }

    public void populateUsers() {
        List<User> users = null;
        try {
            if (grant == null || grant.isExpired()) {
                view.setNoUsersMessageDisplay(true);
                return;
            }
            users = getUsersAsync(grant.getAuthHeader());
            if (users != null) {
                view.setNoUsersMessageDisplay(false);
                ListAdapter adapter = new UserAdapter(view, users);
                view.setListAdapter(adapter);
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
                    return null;
                }
            }
        }.execute(authHeader).get();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }
}
