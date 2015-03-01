package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainActivity;

import java.util.concurrent.ExecutionException;

public class GitFitMainPresenter {
    public static final int AUTH = 1;
    private AccessGrant grant;
    private IResources resources;
    private ActivityNavigator nav;
    private GitFitMainActivity view;
    private UserService userService;
    private User user;

    @Inject
    public GitFitMainPresenter(UserService userService, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted GitFitMainActivity view) {
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
        if (grantBad()) {
            nav.openAuthentication(AUTH);
        }
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
            user = getUserAsync(grant.getId(), grant);
        } catch (ExecutionException | InterruptedException ignored) {
        }
        if (user == null) {
            view.displayMessage(resources.getString(R.string.error_find_profile));
        }

    }

    private User getUserAsync(final String id, final AccessGrant accessGrant) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... params) {
                try {
                    return userService.getUser(id, accessGrant.getAuthHeader());
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
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
