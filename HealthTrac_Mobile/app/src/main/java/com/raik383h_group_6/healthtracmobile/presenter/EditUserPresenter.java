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
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;

import java.util.concurrent.ExecutionException;

public class EditUserPresenter extends BasePresenter{

    private final IAsyncUserService userService;
    private final Bundle extras;
    private final EditUserView view;
    private final IActivityNavigator nav;
    private UserValidationPresenter userValidationPresenter;
    private AccessGrant grant;
    private User ogUser;

    @Inject
    public EditUserPresenter(IAsyncUserService userService, @Assisted UserValidationPresenter userValidationPresenter, @Assisted Bundle extras, @Assisted IActivityNavigator nav, @Assisted EditUserView view) {
        this.userService = userService;
        this.extras = extras;
        this.nav = nav;
        this.view = view;
        this.userValidationPresenter = userValidationPresenter;
        this.grant = extras.getParcelable(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.ogUser = extras.getParcelable(view.getResource(R.string.EXTRA_USER));
    }

    public void onCreate() {
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
            view.setWeight(FormatUtils.format(ogUser.getWeight()));
        }
    }

    public void onClickUpdateUser() {
        User userToUpdate = userValidationPresenter.validateUser(view.getBirthDate(), view.getEmail(), view.getFirstName(), view.getHeight(), view.getLastName(), view.getLocation(), view.getPreferredName(), view.getSex(), view.getUsername(), view.getWeight(), grant.getUserName());
        if (userToUpdate != null) {
            userToUpdate.setId(ogUser.getId());
            updateUser(userToUpdate);
        } else {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
    }

    private void updateUser(User u) {
        try {
            userService.updateUserAsync(u.getId(), u, grant.getAuthHeader());
            view.displayMessage(view.getResource(R.string.user_updated_message));
            nav.finishEditUserSuccess(u);
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.update_user_error));
            nav.finishEditUserFailure();
        }
    }


    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
