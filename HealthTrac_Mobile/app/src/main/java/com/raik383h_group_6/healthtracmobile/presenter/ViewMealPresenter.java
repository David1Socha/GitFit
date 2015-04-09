package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.ViewMealView;

public class ViewMealPresenter extends BasePresenter {

    private AccessGrant grant;
    private ViewMealView view;
    private IActivityNavigator nav;
    private Meal m;
    private String username;

    @Inject
    public ViewMealPresenter(@Assisted IActivityNavigator nav, @Assisted ViewMealView view) {
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.m = (Meal) view.getParcelableExtra(view.getResource(R.string.EXTRA_MEAL));
        this.username = view.getStringExtra(view.getResource(R.string.EXTRA_USERNAME));
    }

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setTitle(view.getResource(R.string.meal_title, username));
        view.setCalories(FormatUtils.format(m.getCalories()));
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
