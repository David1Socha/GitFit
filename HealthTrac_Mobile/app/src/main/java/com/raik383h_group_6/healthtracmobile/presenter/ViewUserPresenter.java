package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.ViewUserActivity;

public class ViewUserPresenter {
    private final Bundle extras;
    private final IResources resources;
    private final ActivityNavigator nav;
    private final ViewUserActivity view;
    private User user;

    @Inject
    public ViewUserPresenter(@Assisted Bundle extras, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted ViewUserActivity view) {
        this.extras = extras;
        this.resources = resources;
        this.nav = nav;
        this.view = view;
    }

    public void onCreate() {
        user = extras.getParcelable(resources.getString(R.string.EXTRA_USER));
        updateFields();
    }

    private void updateFields() {
        view.setBirthDate(user.getBirthDate().toLocaleString());
    }
}
