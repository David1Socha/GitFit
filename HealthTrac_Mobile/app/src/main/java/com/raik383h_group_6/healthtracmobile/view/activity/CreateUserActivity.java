package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.CreateUserPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.UserValidationPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_register_user)
public class CreateUserActivity extends BaseActivity implements CreateUserView {
    @Inject
    private PresenterFactory presenterFactory;
    private CreateUserPresenter presenter;
    @Inject
    FacebookService facebookService;
    @Inject
    AccountService accountService;
    @InjectView(R.id.button_create_account)
    Button createAccountButton;
    @InjectView(R.id.last_name_edittext)
    EditText lastNameEditText;
    @InjectView(R.id.first_name_edittext)
    EditText firstNameEditText;
    @InjectView(R.id.pref_name_edittext)
    EditText prefNameEditText;
    @InjectView(R.id.birthdate_edittext)
    EditText birthDateEditText;
    @InjectView(R.id.location_edittext)
    EditText locationEditText;
    @InjectView(R.id.email_edittext)
    EditText emailEditText;
    @InjectView(R.id.height_edittext)
    EditText heightEditText;
    @InjectView(R.id.username_edittext)
    EditText usernameEditText;
    @InjectView(R.id.weight_edittext)
    EditText weightEditText;
    @InjectView(R.id.radio_sex)
    RadioGroup sexRadioGroup;
    @InjectView(R.id.radio_sex_female)
    RadioButton sexRadioFemale;
    @InjectView(R.id.radio_sex_male)
    RadioButton sexRadioMale;

    @Override
    public String getBirthDate() {
        return birthDateEditText.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    @Override
    public String getFirstName() {
        return firstNameEditText.getText().toString();
    }

    @Override
    public String getHeight() {
        return heightEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastNameEditText.getText().toString();
    }

    @Override
    public String getLocation() {
        return locationEditText.getText().toString();
    }

    @Override
    public String getPreferredName() {
        return prefNameEditText.getText().toString();
    }

    @Override
    public String getSex() {
        return ((RadioButton)findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();
    }

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getWeight() {
        return weightEditText.getText().toString();
    }

    public void setLastName(String val) {
        lastNameEditText.setText(val);
    }

    public void setFirstName(String val) {
        firstNameEditText.setText(val);
    }

    public void setPrefName(String val) {
        prefNameEditText.setText(val);
    }

    public void setBirthDate(String val) {
        birthDateEditText.setText(val);
    }

    public void setLocation(String val) {
        locationEditText.setText(val);
    }

    public void setEmail(String val) {
        emailEditText.setText(val);
    }

    public void setHeight(String val) {
        heightEditText.setText(val);
    }

    public void setUsername(String val) {
        usernameEditText.setText(val);
    }

    public void setWeight(String val) {
        weightEditText.setText(val);
    }

    public void setSex(User.Sex sex) {
        if (sex == User.Sex.FEMALE) {
            sexRadioFemale.setChecked(true);
        } else {
            sexRadioMale.setChecked(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        IActivityNavigator nav = new ActivityNavigator(this);
        UserValidationPresenter userValidationPresenter = presenterFactory.create(this);
        presenter= presenterFactory.create(userValidationPresenter, nav, this);
        presenter.onCreate();
    }

    public void onClickCreateAccount(View v) {
        presenter.onClickCreateAccount();
    }

    @Override
    public void setLocationError(String msg) {
        locationEditText.setError(msg);
    }

    @Override
    public void setBirthDateError(String msg) {
        birthDateEditText.setError(msg);
    }

    @Override
    public void setEmailError(String msg) {
        emailEditText.setError(msg);
    }

    @Override
    public void setFirstNameError(String msg) {
        firstNameEditText.setError(msg);
    }

    @Override
    public void setHeightError(String msg) {
        heightEditText.setError(msg);
    }

    @Override
    public void setLastNameError(String msg) {
        lastNameEditText.setError(msg);
    }

    @Override
    public void setPrefNameError(String msg) {
        prefNameEditText.setError(msg);
    }

    @Override
    public void setUsernameError(String msg) {
        usernameEditText.setError(msg);
    }

    @Override
    public void setWeightError(String msg) {
        weightEditText.setError(msg);
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
