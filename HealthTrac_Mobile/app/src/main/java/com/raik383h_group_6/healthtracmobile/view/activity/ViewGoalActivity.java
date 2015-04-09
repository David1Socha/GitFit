package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.ViewGoalView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_goal)
public class ViewGoalActivity extends BaseActivity implements ViewGoalView {
    @InjectView(R.id.goal_name)
    TextView goalName;
    @InjectView(R.id.field_textview)
    TextView field;
    @InjectView(R.id.threshold_textview)
    TextView threshold;
    @Inject
    PresenterFactory presenterFactory;
    ViewGoalPresenter presenter;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setGoalName(String s) {
        goalName.setText(s);
    }

    @Override
    public void setField(String s) {
        field.setText(s);
    }

    @Override
    public void setThreshold(String s) {
        threshold.setText(s);
    }
}
