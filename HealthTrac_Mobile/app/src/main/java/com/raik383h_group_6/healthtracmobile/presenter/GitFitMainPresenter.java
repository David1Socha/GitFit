package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;

import java.util.concurrent.ExecutionException;

public class GitFitMainPresenter {
    public static final int AUTH = 1;
    private AccessGrant grant;
    private IResources resources;
    private IActivityNavigator nav;
    private GitFitMainView view;
    private IAsyncUserService userService;
    private User user;

    @Inject
    public GitFitMainPresenter(IAsyncUserService userService, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted GitFitMainView view) {
        this.userService = userService;
        this.resources = resources;
        this.nav = nav;
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.grant = savedInstanceState.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        }
    }

    public void onResume() {
        if (grant == null) {
            reconstructGrant();
        }
        if (grantBad()) {
            nav.openAuthentication(AUTH);
        }
    }

    private void reconstructGrant() {
        Gson gson = new Gson();
        String serializedGrant = view.getPref(resources.getString(R.string.pref_access_grant));
        if (serializedGrant != null) {
            grant = gson.fromJson(serializedGrant,  AccessGrant.class);
        }
    }

    public void onPause() {
        saveGrant();
    }

    private void saveGrant() {
        Gson gson = new Gson();
        String serializedGrant = gson.toJson(grant);
        view.setPref(resources.getString(R.string.pref_access_grant), serializedGrant);
    }

    public void onClickShowUsers() {
        if (!grantBad()) {
            nav.openListUsers(grant);
        } else {
            nav.openAuthentication(AUTH);
        }
    }

    private boolean grantBad() {
        return grant == null || grant.isExpired();
    }

    public void onClickShowProfile() {
        loadUser();
        if (user != null) {
            nav.openViewUser(user, grant);
        }
    }

    private void loadUser() {
        try {
            user = userService.getUserAsync(grant.getId(), grant.getAuthHeader());
        } catch (ExecutionException | InterruptedException ignored) {
        }
        if (user == null) {
            view.displayMessage(resources.getString(R.string.error_find_profile));
        }

    }

    public void onClickShowTeams() {
        if (!grantBad()) {
            nav.openListTeams(grant);
        } else {
            nav.openAuthentication(AUTH);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT), grant);
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case AUTH:
                    grant = (AccessGrant) data.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
                    break;
                default:
                    break;
            }

        }
    }

}
