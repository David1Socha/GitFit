package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class RegisterUserPresenter {
    private RegisterUserActivity view;
    private FacebookService facebookService;
    private FacebookUser facebookUser;
    private String accessToken, accessSecret, provider;
    private AccountService accountService;
    private IResources resources;
    private Bundle extras;
    private Navigator nav;

    @Inject
    public RegisterUserPresenter(FacebookService facebookService, AccountService accountService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted Navigator nav, @Assisted RegisterUserActivity view) {
        this.facebookService = facebookService;
        this.view = view;
        this.accountService = accountService;
        this.extras = extras;
        this.resources = resources;
        this.nav = nav;
        accessToken = extras.getString(resources.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = extras.getString(resources.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = extras.getString(resources.getString(R.string.EXTRA_PROVIDER));
    }

    public void populateFields() {
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

    public void validateAccount(String birthDateStr, String email, String firstName, String heightStr, String lastName, String location, String preferredName, String radioValue, String userName, String weightStr) {
        if (fieldsValid(birthDateStr, email, firstName, heightStr, lastName, location, preferredName, userName, weightStr)) {
            Date birthDate = parseDate(birthDateStr);
            double height = parseDouble(heightStr);
            double weight = parseDouble(weightStr);
            User.Sex sex  = radioValue.equals(resources.getString(R.string.male_label)) ? User.Sex.MALE : User.Sex.FEMALE;
            Date dateCreated = new Date();
            Date dateModified = new Date();
            createUser(birthDate, dateCreated, dateModified, email, firstName, height, lastName, location, preferredName, sex, userName, weight); //TODO add location eventually
        } else {
            view.displayMessage(resources.getString(R.string.invalid_field_message));
        }
    }

    private void createUser(Date birthDate, Date dateCreated, Date dateModified, String email, String firstName, double height, String lastName, String location, String preferredName, User.Sex sex, String userName, double weight) {
        User userToCreate = new User(birthDate, dateCreated, dateModified, email, firstName, height, lastName, location, preferredName, sex, userName, weight);
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        UserLogin userLogin = new UserLogin(userToCreate, credentials);
        try {
            Exception registerException = registerAccountAsync(userLogin);
            if (registerException != null) {
                throw registerException;
            }
            nav.finishRegisterUserSuccess();
        } catch (Exception e) {
            view.displayMessage(resources.getString(R.string.account_not_made));
            nav.finishRegisterUserFailure();
        }

    }

    private static double parseDouble(String doubleStr) {
        double d = Double.parseDouble(doubleStr);
        return d;
    }

    private static Date parseDate(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        formatter.setLenient(false);
        Date d = null;
        try {
            d = formatter.parse(dateStr);
        } catch (ParseException ignored) {
        }
        return d;
    }

    private boolean fieldsValid(String birthDate, String email, String firstName, String height, String lastName, String location, String prefName, String username, String weight) {
        boolean allGood = true;
        if (birthDate.equals("")) {
            allGood = false;
            view.setBirthDateError(resources.getString(R.string.empty_field_error));
        } else if (parseDate(birthDate) == null) {
            allGood = false;
            view.setBirthDateError(resources.getString(R.string.invalid_date_error));
        }
        if (email.equals("")) {
            allGood = false;
            view.setEmailError(resources.getString(R.string.empty_field_error));
        }
        if (firstName.equals("")) {
            allGood = false;
            view.setFirstNameError(resources.getString(R.string.empty_field_error));
        }
        if (height.equals("")) {
            allGood = false;
            view.setHeightError(resources.getString(R.string.empty_field_error));
        }
        if (lastName.equals("")) {
            allGood = false;
            view.setLastNameError(resources.getString(R.string.empty_field_error));
        }
        if (location.equals("")) {
            allGood = false;
            view.setLocationError(resources.getString(R.string.empty_field_error));
        }
        if (prefName.equals("")) {
            allGood = false;
            view.setPrefNameError(resources.getString(R.string.empty_field_error));
        }
        if (username.equals("")) {
            allGood = false;
            view.setUsernameError(resources.getString(R.string.empty_field_error));
        }
        //TODO validate username uniqueness once I can get that part of API deployed to azure
        if (weight.equals("")) {
            allGood = false;
            view.setWeightError(resources.getString(R.string.empty_field_error));
        }
        return allGood;
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
