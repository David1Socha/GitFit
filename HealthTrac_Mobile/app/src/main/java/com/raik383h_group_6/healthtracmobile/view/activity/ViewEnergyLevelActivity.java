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
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_activity_report)
public class ViewEnergyLevelActivity extends BaseActivity implements ViewEnergyLevelView {
    @InjectView(R.id.mood_textview)
    TextView mood;
    @InjectView(R.id.title_textview)
    TextView title;
    private ViewEnergyLevelPresenter presenter;
    @Inject
    private PresenterFactory presenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void setMood(String msg) {
        mood.setText(msg);
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
