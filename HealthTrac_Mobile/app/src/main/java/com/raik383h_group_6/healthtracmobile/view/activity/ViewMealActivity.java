package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewEnergyLevelPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.ViewMealView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_meal)
public class ViewMealActivity extends BaseActivity implements ViewMealView {
    @InjectView(R.id.calorie_textview)
    TextView calorie;
    @InjectView(R.id.title_textview)
    TextView title;
    private ViewMealPresenter presenter;
    @Inject
    private PresenterFactory presenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void setCalories(String msg) {
        calorie.setText(msg);
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
