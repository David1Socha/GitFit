package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.UserValidationPresenter;
import com.raik383h_group_6.healthtracmobile.view.CreateGoalView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_create_goal)
public class CreateGoalActivity extends BaseActivity implements CreateGoalView {
    @InjectView(R.id.name_edittext)
    EditText nameEditText;
    @InjectView(R.id.radio_field)
    RadioGroup fieldGroup;
    @InjectView(R.id.threshold_edittext)
    EditText thresholdEditText;
    @Inject
    private PresenterFactory presenterFactory;
    private CreateGoalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Bundle extras = getIntent().getExtras();
        IActivityNavigator nav = new ActivityNavigator(this);
        UserValidationPresenter userValidationPresenter = presenterFactory.create(this);
        presenter= presenterFactory.create(userValidationPresenter, nav, this);
        presenter.onCreate();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }


    public void onClickCreateGoal(View v) {
        presenter.onClickCreateGoal();
    }

    @Override
    public String getName() {
        return nameEditText.getText().toString();
    }

    @Override
    public String getThreshold() {
        return thresholdEditText.getText().toString();
    }

    @Override
    public String getField() {
        return ((RadioButton)findViewById(fieldGroup.getCheckedRadioButtonId())).getText().toString();
    }

    @Override
    public void setNameError(String msg) {
        nameEditText.setError(msg);
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
