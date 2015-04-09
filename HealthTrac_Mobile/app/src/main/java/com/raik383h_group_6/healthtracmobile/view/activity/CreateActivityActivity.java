package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.CreateActivityView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_create_activity)
public class CreateActivityActivity extends BaseActivity implements CreateActivityView {

    @InjectView(R.id.type_textview)
    TextView type;
    @InjectView(R.id.duration_edittext)
    EditText duration;
    @InjectView(R.id.distance_edittext)
    EditText distance;
    @InjectView(R.id.steps_edittext)
    EditText steps;
    @Inject
    PresenterFactory presenterFactory;
    private CreateActivityPresenter presenter;

    protected void onCreate(Bundle s) {
        super.onCreate(s);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    public void onClickCreateActivity(View v) {
        presenter.onClickCreateActivity();
    }

    @Override
    public String getType() {
        return type.getText().toString();
    }

    @Override
    public String getDuration() {
        return duration.getText().toString();
    }

    @Override
    public String getDistance() {
        return distance.getText().toString();
    }

    @Override
    public String getSteps() {
        return steps.getText().toString();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
