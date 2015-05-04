package com.raik383h_group_6.healthtracmobile.presenter;


import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.EventView;

public class EventPresenter extends BasePresenter{
    EventView view;
    IActivityNavigator nav;
    AccessGrant grant;

    @Inject
    public EventPresenter(@Assisted IActivityNavigator nav, @Assisted EventView view) {
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onClickCreateActivity() {
        nav.openActivity(grant);
    }

    public void onClickCreateActivityManually() {
        nav.openCreateActivity(grant);
    }

    public void onClickCreateMeal() {
        nav.openCreateMeal(grant);
    }

    public void onClickCreateEnergyLevel() {
        nav.openCreateEnergyLevel(grant);
    }

    public void onClickCreateGoal() {
        nav.openCreateGoal(grant);
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
