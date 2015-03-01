package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.UpdateUserActivity;

public class UpdateUserPresenter {

    private final UserService userService;
    private final Bundle extras;
    private final IResources resources;
    private final UpdateUserActivity view;
    private UserValidationPresenter userValidationPresenter;

    @Inject
    public UpdateUserPresenter(UserService userService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted UpdateUserActivity view) {
        this.userService = userService;
        this.extras = extras;
        this.resources = resources;
        this.view = view;
    }

    public void onCreate() {
        userValidationPresenter = new UserValidationPresenter(userService, view, resources);
    }

    public void onClickUpdateUser() {
        User userToUpdate = userValidationPresenter.validateUser(view.getBirthDate(), view.getEmail(), view.getFirstName(), view.getHeight(), view.getLastName(), view.getLocation(), view.getPreferredName(), view.getSex(), view.getUsername(), view.getWeight());
        if (userToUpdate != null) {
            //UPDATE
        } else {
            view.displayMessage(resources.getString(R.string.invalid_field_message));
        }
    }
}
