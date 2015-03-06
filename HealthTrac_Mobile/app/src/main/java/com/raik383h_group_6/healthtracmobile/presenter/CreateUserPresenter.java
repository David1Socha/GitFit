package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;

import java.util.concurrent.ExecutionException;

public class CreateUserPresenter {
    private CreateUserView view;
    private FacebookService facebookService;
    private FacebookUser facebookUser;
    private String accessToken, accessSecret, provider;
    private AccountService accountService;
    private IResources resources;
    private Bundle extras;
    private IActivityNavigator nav;
    private UserService userService;
    private UserValidationPresenter userValidationPresenter;

    @Inject
    public CreateUserPresenter(FacebookService facebookService, UserService userService, AccountService accountService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted CreateUserView view) {
        this.facebookService = facebookService;
        this.view = view;
        this.accountService = accountService;
        this.extras = extras;
        this.resources = resources;
        this.userService = userService;
        this.nav = nav;
    }

    public void onCreate() {
        this.userValidationPresenter = new UserValidationPresenter(userService, view, resources);
        accessToken = extras.getString(resources.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = extras.getString(resources.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = extras.getString(resources.getString(R.string.EXTRA_PROVIDER));
        getAndPopulateFacebookUser();
    }

    private void getAndPopulateFacebookUser() {
        facebookUser = null;
        if (provider.equals(resources.getString(R.string.PROVIDER_FACEBOOK))) {
            try {
                facebookUser = getFacebookUserAsync();
            } catch (InterruptedException | ExecutionException ignored) {
            }
            if (facebookUser != null) {
                populateFacebookFields(facebookUser);
            }
        }
    }

    private void populateFacebookFields(FacebookUser facebookUser) {
        User.Sex sex = facebookUser.getGender().equals("male") ? User.Sex.MALE : User.Sex.FEMALE;
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

    private FacebookUser getFacebookUserAsync() throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, FacebookUser>() {
            @Override protected FacebookUser doInBackground(String... params) {
                try {
                    return facebookService.getUser(FacebookService.AUTH_PREFIX + accessToken);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(accessToken).get();
    }

    public void onClickCreateAccount() {
        User userToCreate = userValidationPresenter.validateUser(view.getBirthDate(), view.getEmail(), view.getFirstName(), view.getHeight(), view.getLastName(), view.getLocation(), view.getPreferredName(), view.getSex(), view.getUsername(), view.getWeight());
        if (userToCreate != null) {
            createUser(userToCreate);
        } else {
            view.displayMessage(resources.getString(R.string.invalid_field_message));
        }
    }

    private void createUser(User userToCreate) {
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        UserLogin userLogin = new UserLogin(userToCreate, credentials);
        try {
            Exception registerException = registerAccountAsync(userLogin);
            if (registerException != null) {
                throw registerException;
            }
            nav.finishCreateUserSuccess();
        } catch (Exception e) {
            view.displayMessage(resources.getString(R.string.account_not_made));
            nav.finishCreateUserFailure();
        }

    }

    private Exception registerAccountAsync(UserLogin userLogin) throws ExecutionException, InterruptedException {
            return new AsyncTask<UserLogin, Void, Exception>() {

               @Override
               protected Exception doInBackground(UserLogin... params){
                   try {
                       accountService.register(params[0]);
                   } catch (Exception e) {
                       return e;
                   }
                   return null;
               }
           }.execute(userLogin).get();
    }
}