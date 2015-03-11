package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import java.util.Date;

public class UserValidationPresenter {

    private final UserValidationView view;
    private final IAsyncUserService userService;
    private final IResources resources;

    @Inject
    public UserValidationPresenter(IAsyncUserService userService, @Assisted UserValidationView view, @Assisted IResources resources) {
        this.view = view;
        this.userService = userService;
        this.resources = resources;
    }

    public User validateUser(String birthDateStr, String email, String firstName, String heightStr, String lastName, String location, String preferredName, String radioValue, String userName, String weightStr, String currUserName) {
        User userToCreate = null;
        if (fieldsValid(birthDateStr, email, firstName, heightStr, lastName, location, preferredName, userName, weightStr, currUserName)) {
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

    private boolean fieldsValid(String birthDate, String email, String firstName, String height, String lastName, String location, String prefName, String username, String weight, String currUserName) {
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
        try {
            if ( (currUserName == null || !username.equals(currUserName) ) &&  !userService.isAvailable(username) ) {
                allGood = false;
                view.setUsernameError("Username already exists"); //TODO use xml nerd
            }
        } catch (Exception ignored) {
            //If some error occurs, assume username is unique and let user continue (server validates later anyways)
        }
        if (weight.equals("")) {
            allGood = false;
            view.setWeightError(resources.getString(R.string.empty_field_error));
        }
        return allGood;
    }

}
