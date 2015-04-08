package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.CreateEnergyLevelPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.CreateEnergyLevelView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_create_energy_level)
public class CreateEnergyLevelActivity extends BaseActivity implements CreateEnergyLevelView {

    @InjectView(R.id.radio_mood)
    RadioGroup moodGroup;
    @Inject
    PresenterFactory presenterFactory;
    CreateEnergyLevelPresenter presenter;

    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public String getMood() {
        return ((RadioButton)findViewById(moodGroup.getCheckedRadioButtonId())).getText().toString();
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onClickCreateEnergyLevel(View v) {
        presenter.onClickCreateEnergyLevel();
    }
}
