package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.UpdateUserActivity;

public class UpdateUserPresenter {

    private final UserService userService;
    private final Bundle extras;
    private final IResources resources;
    private final UpdateUserActivity view;

    @Inject
    public UpdateUserPresenter(UserService userService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted UpdateUserActivity view) {
        this.userService = userService;
        this.extras = extras;
        this.resources = resources;
        this.view = view;
    }

    public void onCreate() {

    }

    public void onClickUpdateUser() {

    }
}
