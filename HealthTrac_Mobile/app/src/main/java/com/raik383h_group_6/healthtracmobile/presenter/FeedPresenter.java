package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMealService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncPointService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserGoalService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

public class FeedPresenter extends BasePresenter {

    private FeedView view;
    private IActivityNavigator nav;
    private AccessGrant grant;
    private IAsyncActivityService asvc;
    private IAsyncBadgeService bsvc;
    private IAsyncEnergyLevelService esvc;
    private IAsyncGoalService gsvc;
    private IAsyncMealService mlsvc;
    private IAsyncMembershipService mbsvc;
    private IAsyncTeamService tsvc;
    private IAsyncUserBadgeService ubsvc;
    private IAsyncUserGoalService ugsvc;

    @Inject
    public FeedPresenter(IAsyncActivityService asvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, @Assisted IActivityNavigator nav, @Assisted FeedView view) {
        this.view = view;
        this.nav = nav;
        String uid = view.getStringExtra(view.getResource(R.string.EXTRA_USER_ID));
        long teamId = view.getLongExtra(view.getResource(R.string.EXTRA_TEAM_ID));
        this.asvc = asvc;
        this.bsvc = bsvc;
        this.esvc = esvc;
        this.gsvc = gsvc;
        this.mlsvc = mlsvc;
        this.mbsvc = mbsvc;
        this.tsvc = tsvc;
        this.ubsvc = ubsvc;
        this.ugsvc = ugsvc;
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
