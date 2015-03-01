package com.raik383h_group_6.healthtracmobile.service;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.view.IUserEditView;

import java.util.Date;

public class UserValidationPresenter {

    private final IUserEditView view;
    private final AccountService accountService;
    private final IResources resources;

    public UserValidationPresenter(AccountService accountService, IUserEditView view, IResources resources) {
        this.view = view;
        this.accountService = accountService;
        this.resources = resources;
    }

    public User validateUser(String birthDateStr, String email, String firstName, String heightStr, String lastName, String location, String preferredName, String radioValue, String userName, String weightStr) {
        User userToCreate = null;
        if (fieldsValid(birthDateStr, email, firstName, heightStr, lastName, location, preferredName, userName, weightStr)) {
            Date birthDate = FormatUtils.parseDate(birthDateStr);
            double height = FormatUtils.parseDouble(heightStr);
            double weight = FormatUtils.parseDouble(weightStr);
            User.Sex sex  = radioValue.equals(resources.getString(R.string.male_label)) ? User.Sex.MALE : User.Sex.FEMALE;
            Date dateCreated = new Date();
            Date dateModified = new Date();
            userToCreate = new User(birthDate, dateCreated, dateModified, email, firstName, height, lastName, location, preferredName, sex, userName, weight); //TODO add location eventually
        }
        return userToCreate;
    }

    private boolean fieldsValid(String birthDate, String email, String firstName, String height, String lastName, String location, String prefName, String username, String weight) {
        boolean allGood = true;
        if (birthDate.equals("")) {
            allGood = false;
            view.setBirthDateError(resources.getString(R.string.empty_field_error));
        } else if (FormatUtils.parseDate(birthDate) == null) {
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

}
