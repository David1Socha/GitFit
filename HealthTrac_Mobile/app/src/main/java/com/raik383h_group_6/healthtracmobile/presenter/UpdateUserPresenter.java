package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.activity.UpdateUserActivity;

import java.util.concurrent.ExecutionException;

public class UpdateUserPresenter {

    private final UserService userService;
    private final Bundle extras;
    private final IResources resources;
    private final UpdateUserActivity view;
    private final IActivityNavigator nav;
    private UserValidationPresenter userValidationPresenter;
    private AccessGrant grant;
    private User ogUser;

    @Inject
    public UpdateUserPresenter(UserService userService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted UpdateUserActivity view) {
        this.userService = userService;
        this.extras = extras;
        this.nav = nav;
        this.resources = resources;
        this.view = view;
    }

    public void onCreate() {
        userValidationPresenter = new UserValidationPresenter(userService, view, resources);
        this.grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        this.ogUser = extras.getParcelable(resources.getString(R.string.EXTRA_USER));
        populateFields();
    }

    private void populateFields() {
        if (ogUser != null) {
            view.setBirthDate(FormatUtils.format(ogUser.getBirthDate()));
            view.setEmail(ogUser.getEmail());
            view.setFirstName(ogUser.getFirstName());
            view.setHeight(FormatUtils.format(ogUser.getHeight()));
            view.setLastName(ogUser.getLastName());
            view.setLocation(ogUser.getLocation());
            view.setPrefName(ogUser.getPreferredName());
            view.setSex(ogUser.getSex());
            view.setUsername(ogUser.getUserName());
            view.setWeight(FormatUtils.format(ogUser.getWidth()));
        }
    }

    public void onClickUpdateUser() {
        User userToUpdate = userValidationPresenter.validateUser(view.getBirthDate(), view.getEmail(), view.getFirstName(), view.getHeight(), view.getLastName(), view.getLocation(), view.getPreferredName(), view.getSex(), view.getUsername(), view.getWeight());
        if (userToUpdate != null) {
            userToUpdate.setId(ogUser.getId());
            updateUser(userToUpdate);
        } else {
            view.displayMessage(resources.getString(R.string.invalid_field_message));
        }
    }

    private void updateUser(User u) {
        try {
            Exception e = updateUserAsync(u, grant.getAuthHeader());
            if (e != null) {
                throw e;
            }
            view.displayMessage(resources.getString(R.string.user_updated_message));
            nav.finishUpdateUserSuccess(u);
        } catch (Exception e) {
            view.displayMessage(resources.getString(R.string.update_user_error));
            nav.finishUpdateUserFailure();
        }
    }

    private Exception updateUserAsync(final User u, final String authHeader) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    userService.updateUser(ogUser.getId(), u, authHeader);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            }
        }.execute().get();
    }
}
