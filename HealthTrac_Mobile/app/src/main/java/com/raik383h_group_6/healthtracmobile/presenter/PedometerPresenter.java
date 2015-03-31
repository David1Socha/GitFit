package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.PedometerView;

/**
 * Created by Aaron on 3/31/2015.
 */
public class PedometerPresenter extends BasePresenter{
    private final IActivityNavigator nav;
    private final PedometerView view;

    @Inject
    public PedometerPresenter( @Assisted IActivityNavigator nav, @Assisted PedometerView view) {
        this.nav = nav;
        this.view = view;
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
