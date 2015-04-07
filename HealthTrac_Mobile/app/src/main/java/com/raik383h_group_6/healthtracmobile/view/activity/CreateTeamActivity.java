package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.CreateTeamPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.TeamValidationPresenter;
import com.raik383h_group_6.healthtracmobile.view.CreateTeamView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_create_team)
public class CreateTeamActivity extends BaseActivity implements CreateTeamView {
    @Inject
    private PresenterFactory presenterFactory;
    private CreateTeamPresenter presenter;

    @InjectView(R.id.create_team_button)
    Button createTeamButton;
    @InjectView(R.id.team_name_edittext)
    EditText nameEditText;
    @InjectView(R.id.team_description_edittext)
    EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        IActivityNavigator nav = new ActivityNavigator(this);
        TeamValidationPresenter teamValidationPresenter = presenterFactory.create(this);
        presenter= presenterFactory.create(teamValidationPresenter, nav, this);
    }

    public void onClickCreateTeam(View view) { presenter.onClickCreateTeam(); }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setName(String val) { nameEditText.setText(val); }

    @Override
    public void setDescription(String val) { descriptionEditText.setText(val); }

    @Override
    public String getName() { return nameEditText.getText().toString(); }

    @Override
    public String getDescription() { return descriptionEditText.getText().toString(); }

    @Override
    public void setNameError(String msg) { nameEditText.setError(msg); }

    @Override
    public void setDescriptionError(String msg) { descriptionEditText.setError(msg); }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
