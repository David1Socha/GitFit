package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;

public class ViewUserPresenter extends BasePresenter{
    private final IActivityNavigator nav;
    private final ViewUserView view;
    private User user;
    private AccessGrant grant;

    @Inject
    public ViewUserPresenter(@Assisted IActivityNavigator nav, @Assisted ViewUserView view) {
        this.nav = nav;
        this.view = view;
        user = (User) view.getParcelableExtra(view.getResource(R.string.EXTRA_USER));
        grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onClickEditUser() {
        nav.openEditUser(grant, user, RequestCodes.UPDATE_USER);
    }

    public void onResume() {
        updateFields();
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.UPDATE_USER:
                    user = extras.getParcelable(view.getResource(R.string.EXTRA_USER));
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
        view.setProfilePicture(user.getProfilePicture());
        view.setSex(user.getSex()== User.Sex.MALE ? view.getResource(R.string.male_label): view.getResource(R.string.label_female));
        view.setUserName(user.getUserName());
        view.setWeight(FormatUtils.format(user.getWeight()));
        boolean userViewingSelf = user.getId().equals(grant.getId());
        view.setShowEditUserButton(userViewingSelf);
    }

    public void onClickUserFeed() {
        nav.openFeed(user.getId(), grant);
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    @Override
    protected BaseView getView() {
        return view;
    }

}
