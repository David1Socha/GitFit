package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewGoalView;

public class ViewGoalPresenter extends BasePresenter {

    private AccessGrant grant;
    private ViewGoalView view;
    private IActivityNavigator nav;
    private Goal goal;

    public ViewGoalPresenter(@Assisted IActivityNavigator nav, @Assisted ViewGoalView view) {
        this.view = view;
        this.nav = nav;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.goal = (Goal) view.getParcelableExtra(view.getResource(R.string.EXTRA_GOAL));
    }

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setField(goal.getField().name().toLowerCase());
        view.setThreshold(FormatUtils.format(goal.getThreshold()));
        view.setGoalName(goal.getName());
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }
}
