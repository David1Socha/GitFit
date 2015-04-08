package com.raik383h_group_6.healthtracmobile.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityReportService;
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
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.feed.FeedGenerator;
import com.raik383h_group_6.healthtracmobile.service.feed.UserFeedGenerator;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import java.util.List;

public class FeedPresenter extends BasePresenter {

    private FeedView view;
    private FeedGenerator gen;
    private IActivityNavigator nav;
    private AccessGrant grant;
    private IAsyncActivityService asvc;
    private IAsyncActivityReportService arsvc;
    private IAsyncBadgeService bsvc;
    private IAsyncEnergyLevelService esvc;
    private IAsyncGoalService gsvc;
    private IAsyncMealService mlsvc;
    private IAsyncMembershipService mbsvc;
    private IAsyncTeamService tsvc;
    private IAsyncUserBadgeService ubsvc;
    private IAsyncUserGoalService ugsvc;
    private IAsyncUserService usvc;

    @Inject
    public FeedPresenter(IAsyncActivityService asvc, IAsyncUserService usvc, IAsyncActivityReportService arsvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, @Assisted IActivityNavigator nav, @Assisted FeedView view) {
        this.view = view;
        this.nav = nav;
        String uid = view.getStringExtra(view.getResource(R.string.EXTRA_USER_ID));
        long teamId = view.getLongExtra(view.getResource(R.string.EXTRA_TEAM_ID));
        this.grant = view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.asvc = asvc;
        this.arsvc = arsvc;
        this.bsvc = bsvc;
        this.esvc = esvc;
        this.gsvc = gsvc;
        this.mlsvc = mlsvc;
        this.mbsvc = mbsvc;
        this.tsvc = tsvc;
        this.usvc = usvc;
        this.ubsvc = ubsvc;
        this.ugsvc = ugsvc;
        if (uid != null) {
            gen = new UserFeedGenerator(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, view.getResources(), grant, nav, uid);
        } else {
            gen = new TeamFeedGenerator(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, view.getResources(), grant, nav, teamId);
        }
    }

    public void onResume() {
        populateFeed();
    }

    private void populateFeed() {
        List<FeedModel> fms = null;
        try {
            fms = gen.getFeedElements();
            if (fms != null && !fms.isEmpty()) {
                view.setFeedModels(fms);
                view.setEmptyFeedDisplay(false);
            } else {
                view.setEmptyFeedDisplay(true);
            }
        } catch (Exception e) {
            view.setEmptyFeedDisplay(true);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FeedModel fm = (FeedModel) parent.getAdapter().getItem(position);
        fm.onClick();
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
