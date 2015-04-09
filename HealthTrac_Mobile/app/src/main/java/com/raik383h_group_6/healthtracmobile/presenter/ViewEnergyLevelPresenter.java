package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityReportView;
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;

public class ViewEnergyLevelPresenter extends BasePresenter {

    private AccessGrant grant;
    private ViewEnergyLevelView view;
    private IActivityNavigator nav;
    private EnergyLevel el;
    private String username;

    @Inject
    public ViewEnergyLevelPresenter(@Assisted IActivityNavigator nav, @Assisted ViewEnergyLevelView view) {
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.el = (EnergyLevel) view.getParcelableExtra(view.getResource(R.string.EXTRA_ENERGY_LEVEL));
        this.username = view.getStringExtra(view.getResource(R.string.EXTRA_USERNAME));
    }

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setTitle(view.getResource(R.string.energy_level_title, username));
        view.setMood(el.getMood().name().toLowerCase());
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
