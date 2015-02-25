package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
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

    public void initialize(FacebookService facebookService, AccountService accountService, RegisterUserActivity view) {
        this.facebookService = facebookService;
        this.view = view;
        this.accountService = accountService;
        accessToken = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_ACCESS_TOKEN));
        accessSecret = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_ACCESS_SECRET));
        provider = view.getIntent().getStringExtra(view.getString(R.string.EXTRA_PROVIDER));
        Log.d("davidsocha", view.getIntent().getStringExtra(view.getString(R.string.EXTRA_PROVIDER)));
        Log.d("davidsocha", provider);
    }

    public void populateFields() {
        facebookUser = null;
        if (provider.equals(view.getString(R.string.PROVIDER_FACEBOOK))) {
            try {
                facebookUser = getFacebookUserAsync();
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }
        if (facebookUser != null) {
            //populate fields from facebook user
        }

    }

    private FacebookUser getFacebookUserAsync() throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, FacebookUser>() {
            @Override protected FacebookUser doInBackground(String... params) {
                try {
                    return facebookService.getUser(accessToken);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute(accessToken).get();
    }

    public void validateAccount(String birthDateStr, String email, String firstName, String heightStr, String lastName, String preferredName, String radioValue, String userName, String weightStr) {
        if (fieldsValid(birthDateStr, email, firstName, heightStr, lastName, preferredName, userName, weightStr)) {
            Date birthDate = parseDate(birthDateStr);
            double height = parseDouble(heightStr);
            double weight = parseDouble(weightStr);
            User.Sex sex  = radioValue.equals(view.getString(R.string.male_label)) ? User.Sex.MALE : User.Sex.FEMALE;
            Date dateCreated = new Date();
            Date dateModified = new Date();
            createUser(birthDate, dateCreated, dateModified, email, firstName, height, lastName, preferredName, sex, userName, weight);
        } else {
            view.displayMessage(view.getString(R.string.invalid_field_message));
        }
    }

    private void createUser(Date birthDate, Date dateCreated, Date dateModified, String email, String firstName, double height, String lastName, String preferredName, User.Sex sex, String userName, double weight) {
        User userToCreate = new User(birthDate, dateCreated, dateModified, email, firstName, height, lastName, preferredName, sex, userName, weight);
        Credentials credentials = new Credentials(accessToken, accessSecret, provider);
        UserLogin userLogin = new UserLogin(userToCreate, credentials);
        try {
            registerAccountAsync(userLogin);
            finishSuccess();
        } catch (ExecutionException | InterruptedException e) {
            view.displayMessage(view.getString(R.string.account_not_made));
            finishFailure();
        }

    }

    private void finishSuccess() {
        view.setResult(Activity.RESULT_OK);
        view.finish();
    }

    private void finishFailure() {
        view.setResult(Activity.RESULT_CANCELED);
        view.finish();
    }

    private static double parseDouble(String doubleStr) {
        double d = Double.parseDouble(doubleStr);
        return d;
    }

    private static Date parseDate(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date d = null;
        try {
            d = formatter.parse(dateStr);
        } catch (ParseException ignored) {
        }
        return d;
    }

    private boolean fieldsValid(String birthDate, String email, String firstName, String height, String lastName, String prefName, String username, String weight) {
        boolean allGood = true;
        if (birthDate.equals("")) {
            allGood = false;
            view.setBirthDateError(view.getString(R.string.empty_field_error));
        }
        if (email.equals("")) {
            allGood = false;
            view.setEmailError(view.getString(R.string.empty_field_error));
        }
        if (firstName.equals("")) {
            allGood = false;
            view.setFirstNameError(view.getString(R.string.empty_field_error));
        }
        if (height.equals("")) {
            allGood = false;
            view.setHeightError(view.getString(R.string.empty_field_error));
        }
        if (lastName.equals("")) {
            allGood = false;
            view.setLastNameError(view.getString(R.string.empty_field_error));
        }
        if (prefName.equals("")) {
            allGood = false;
            view.setPrefNameError(view.getString(R.string.empty_field_error));
        }
        if (username.equals("")) {
            allGood = false;
            view.setUsernameError(view.getString(R.string.empty_field_error));
        }
        //TODO validate username uniqueness once I can get that part of API deployed to azure
        if (weight.equals("")) {
            allGood = false;
            view.setWeightError(view.getString(R.string.empty_field_error));
        }
        return allGood;
    }

    private void registerAccountAsync(UserLogin userLogin) throws ExecutionException, InterruptedException {
            new AsyncTask<UserLogin, Void, Void>() {

               @Override
               protected Void doInBackground(UserLogin... params) {
                   accountService.register(params[0]);
                   return null;
               }
           }.execute(userLogin).get();
    }
}
