package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.RegisterUserPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;

import java.util.Date;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends RoboActivity {
    @Inject
    private RegisterUserPresenter presenter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialize(facebookService, accountService, this);
        presenter.populateFields();
        createAccountButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sexRadioText = ((RadioButton)findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();
                presenter.validateAccount(birthDateEditText.getText().toString(), emailEditText.getText().toString(), firstNameEditText.getText().toString(), heightEditText.getText().toString(), lastNameEditText.getText().toString(), prefNameEditText.getText().toString(), sexRadioText, usernameEditText.getText().toString(), weightEditText.getText().toString());
            }
        });
    }

    public void setBirthDateError(String msg) {
        birthDateEditText.setError(msg);
    }

    public void setEmailError(String msg) {
        emailEditText.setError(msg);
    }

    public void setFirstNameError(String msg) {
        firstNameEditText.setError(msg);
    }

    public void setHeightError(String msg) {
        heightEditText.setError(msg);
    }

    public void setLastNameError(String msg) {
        lastNameEditText.setError(msg);
    }

    public void setPrefNameError(String msg) {
        prefNameEditText.setError(msg);
    }

    public void setUsernameError(String msg) {
        usernameEditText.setError(msg);
    }

    public void setWeightError(String msg) {
        weightEditText.setError(msg);
    }

    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
