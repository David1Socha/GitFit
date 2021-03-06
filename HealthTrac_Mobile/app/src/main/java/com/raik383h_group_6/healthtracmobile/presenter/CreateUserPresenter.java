package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;

import java.util.concurrent.ExecutionException;

public class CreateUserPresenter extends BasePresenter {
    private CreateUserView view;
    private IAsyncFacebookService facebookService;
    private FacebookUser facebookUser;
    private String accessToken, accessSecret, provider;
    private IAsyncAccountService accountService;
    private IActivityNavigator nav;
    private IAsyncUserService userService;
    private UserValidationPresenter userValidationPresenter;

    @Inject
    public CreateUserPresenter(IAsyncFacebookService facebookService, IAsyncUserService userService, IAsyncAccountService accountService, @Assisted UserValidationPresenter userValidationPresenter, @Assisted IActivityNavigator nav, @Assisted CreateUserView view) {
        this.facebookService = facebookService;
        this.view = view;
        this.accountService = accountService;
        this.userService = userService;
        this.nav = nav;
        this.userValidationPresenter = userValidationPresenter;
        accessToken = view.getStringExtra(view.getResource(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = view.getStringExtra(view.getResource(R.string.EXTRA_ACCESS_SECRET));
        provider = view.getStringExtra(view.getResource(R.string.EXTRA_PROVIDER));
    }

    public void onCreate() {
        getAndPopulateFacebookUser();
    }

    private void getAndPopulateFacebookUser() {
        facebookUser = null;
        if (view.getResource(R.string.PROVIDER_FACEBOOK).equals(provider)) {
            try {
                facebookUser = facebookService.getFacebookUserAsync(accessToken);
            } catch (InterruptedException | ExecutionException ignored) {
            }
            if (facebookUser != null) {
                populateFacebookFields(facebookUser);
            }
        }
    }

    private void populateFacebookFields(FacebookUser facebookUser) {
        User.Sex sex = facebookUser.getGender().equals(view.getResource(R.string.facebook_male_label)) ? User.Sex.MALE : User.Sex.FEMALE;
        String email = facebookUser.getEmail();
        String firstName = facebookUser.getFirstName();
        String lastName = facebookUser.getLastName();
        String prefName = facebookUser.getName();
        String location = facebookUser.getLocation() == null ? null : facebookUser.getLocation().getName();
        if (email != null) {
            view.setEmail(email);
        }
        if (firstName != null) {
            view.setFirstName(firstName);
        }
        if (lastName != null) {
            view.setLastName(lastName);
        }
        if (prefName != null) {
            view.setPrefName(prefName);
        }
        if (location != null) {
            view.setLocation(location);
        }
        view.setSex(sex);
    }

    public void onClickCreateAccount() {
        User userToCreate = userValidationPresenter.validateUser(view.getBirthDate(), view.getEmail(), view.getFirstName(), view.getHeight(), view.getLastName(), view.getLocation(), view.getPreferredName(), view.getSex(), view.getUsername(), view.getWeight(), null);
        if (userToCreate != null) {
            createUser(userToCreate);
        } else {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
    }

    @Override
    protected AccessGrant getGrant() {
        return null;
    }

    private void createUser(User userToCreate) {
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        UserLogin userLogin = new UserLogin(userToCreate, credentials);
        try {
            accountService.registerAccountAsync(userLogin);
            nav.finishCreateUserSuccess();
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.account_not_made));
            nav.finishCreateUserFailure();
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
