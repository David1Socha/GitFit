package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Field;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncGoalService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.CreateGoalView;

import java.util.Date;

public class CreateEnergyLevelPresenter extends BasePresenter {

    private IAsyncEnergyLevelService eSvc;
    private IActivityNavigator nav;
    private CreateEnergyLevelView view;
    private AccessGrant grant;

    @Inject
    public CreateEnergyLevelPresenter(IAsyncEnergyLevelService eSvc, @Assisted IActivityNavigator nav, @Assisted CreateEnergyLevelView view) {
        this.eSvc = eSvc;
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onCreate() {
        if (grant == null || grant.isExpired()) {
            view.displayMessage(view.getResource(R.string.no_grant_message));
            nav.finishActivity();
        }
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    public void onClickCreateEnergyLevel() {
        EnergyLevel.Mood mood = EnergyLevel.Mood.valueOf(view.getMood());
        EnergyLevel el = new EnergyLevel();
        el.setMood(mood);
        Date curr = new Date();
        el.setDateCreated(curr);
        el.setDateModified(curr);
        createEnergyLevel(el);
    }

    private void createEnergyLevel(EnergyLevel el) {
        try {
            eSvc.createEnergyLevelAsync(el, grant.getAuthHeader());
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.error_create_energy_level));
        }
        nav.finishCreateEnergyLevel();
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

}
