package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.ViewUserActivity;

public class ViewUserPresenter {
    private final Bundle extras;
    private final IResources resources;
    private final ActivityNavigator nav;
    private final ViewUserActivity view;
    private User user;
    private AccessGrant grant;
    public static final int UPDATE = 1;

    @Inject
    public ViewUserPresenter(@Assisted Bundle extras, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted ViewUserActivity view) {
        this.extras = extras;
        this.resources = resources;
        this.nav = nav;
        this.view = view;
    }

    public void onCreate() {
        user = extras.getParcelable(resources.getString(R.string.EXTRA_USER));
        grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onClickEditUser() {
        nav.openUpdateUser(grant, user, UPDATE);
    }

    public void onResume() {
        updateFields();
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case UPDATE:
                    user = extras.getParcelable(resources.getString(R.string.EXTRA_USER));
                    updateFields();
                    break;
                default:
                    break;
            }
        }
    }

    private void updateFields() {
        view.setBirthDate(FormatUtils.format(user.getBirthDate()));
        view.setEmail(user.getEmail());
        view.setFirstName(user.getFirstName());
        view.setHeight(FormatUtils.format(user.getHeight()));
        view.setLastName(user.getLastName());
        view.setLocation(user.getLocation());
        view.setPrefName(user.getPreferredName());
        view.setSex(user.getSex()== User.Sex.MALE ? resources.getString(R.string.male_label): resources.getString(R.string.label_female));
        view.setUserName(user.getUserName());
        view.setWeight(FormatUtils.format(user.getWidth()));
        boolean userViewingSelf = user.getId().equals(grant.getId());
        view.setShowEditUserButton(userViewingSelf);
    }
}
