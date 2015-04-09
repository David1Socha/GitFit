package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_activity)
public class ViewActivityActivity extends BaseActivity implements ViewActivityView {

    @InjectView(R.id.title_textview)
    private TextView title;
    @InjectView(R.id.type_textview)
    private TextView type;
    @InjectView(R.id.duration_textview)
    private TextView duration;
    @InjectView(R.id.distance_textview)
    private TextView distance;
    @InjectView(R.id.steps_textview)
    private TextView steps;
    @Inject
    private PresenterFactory presenterFactory;
    private ViewActivityPresenter presenter;

    protected void onCreate(Bundle s) {
        super.onCreate(s);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setTitle(String t) {
        title.setText(t);
    }

    @Override
    public void setType(String t) {
        type.setText(t);
    }

    @Override
    public void setDuration(String d) {
        type.setText(d);
    }

    @Override
    public void setDistance(String d) {
        type.setText(d);
    }

    @Override
    public void setSteps(String s) {
        type.setText(s);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
