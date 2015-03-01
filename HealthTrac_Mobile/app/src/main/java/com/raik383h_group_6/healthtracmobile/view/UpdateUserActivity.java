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
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.RegisterUserPresenter;
import com.raik383h_group_6.healthtracmobile.service.api.AccountService;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;

import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_update_user)
public class UpdateUserActivity extends RoboActionBarActivity {
    @Inject
    private PresenterFactory presenterFactory;
    private Object presenter; //TODO
    @Inject
    UserService userService;
    @InjectView(R.id.button_update_user)
    Button updateUserButton;
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
        IResources resources = new ResourcesAdapter(getResources());
        ActivityNavigator nav = new ActivityNavigator(this);
        presenter = null;
        presenter.populateFields();
    }

    public void onClickUpdateUser(View v) {
        presenter.onClickUpdateUser(User.Sex.MALE); //TODO
    }

    public void setLocationError(String msg) {
        locationEditText.setError(msg);
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
