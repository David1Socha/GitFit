package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PedometerPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.PedometerView;

/**
 * Created by Aaron on 3/31/2015.
 */
public class PedometerActivity extends BaseActivity implements PedometerView {
    private PedometerPresenter presenter;
    PresenterFactory presenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
