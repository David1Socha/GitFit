package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewActivityReportPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityReportView;

import org.w3c.dom.Text;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_activity_report)
public class ViewActivityReportActivity extends BaseActivity implements ViewActivityReportView {
    @InjectView(R.id.distance_textview)
    TextView distance;
    @InjectView(R.id.duration_textview)
    TextView duration;
    @InjectView(R.id.steps_textview)
    TextView steps;
    @InjectView(R.id.title_textview)
    TextView title;
    private ViewActivityReportPresenter presenter;
    @Inject
    private PresenterFactory presenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void setDistance(String msg) {
        distance.setText(msg);
    }

    @Override
    public void setDuration(String msg) {
        duration.setText(msg);
    }

    @Override
    public void setSteps(String msg) {
        steps.setText(msg);
    }

    @Override
    public void setTitle(String s) {
        title.setText(s);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
