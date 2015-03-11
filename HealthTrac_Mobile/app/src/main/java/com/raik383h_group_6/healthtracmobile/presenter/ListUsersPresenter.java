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
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUsersPresenter extends BasePresenter{

    public static final int AUTH = 1;

    private IResources resources;
    private IActivityNavigator nav;
    private ListUsersView view;
    private IAsyncUserService userService;
    private AccessGrant grant;
    private Bundle extras;

    @Inject
    public ListUsersPresenter(IAsyncUserService userService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted ListUsersView view) {
        this.resources = resources;
        this.nav = nav;
        this.userService = userService;
        this.view = view;
        this.extras = extras;
        this.grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
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
            users = userService.getUsersAsync(grant.getAuthHeader());
            if (users != null) {
                view.setNoUsersMessageDisplay(false);
                view.setUsers(users);
            } else {
                view.setNoUsersMessageDisplay(true);
            }

        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
