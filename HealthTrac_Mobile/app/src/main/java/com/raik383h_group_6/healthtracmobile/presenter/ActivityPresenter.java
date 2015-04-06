package com.raik383h_group_6.healthtracmobile.presenter;


import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;

public class ActivityPresenter extends BasePresenter{
    private final IActivityNavigator nav;
    private final ActivityView view;
    private int steps;


    @Inject
    public ActivityPresenter(@Assisted IActivityNavigator nav, @Assisted ActivityView view) {
        this.nav = nav;
        this.view = view;
        this.steps = 0;

    }

    public void onStep() {
        steps++;
        view.setStepCount(Integer.toString(steps));
    }

    public void resetSteps() {
        steps = 0;
        view.setStepCount(Integer.toString(steps));
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
