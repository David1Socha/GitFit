package com.raik383h_group_6.healthtracmobile.presenter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Field;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.model.feed.GoalProgress;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
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
import com.raik383h_group_6.healthtracmobile.service.feed.TeamFeedGenerator;
import com.raik383h_group_6.healthtracmobile.service.feed.UserFeedGenerator;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.FeedView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.model.Field.DISTANCE;
import static com.raik383h_group_6.healthtracmobile.model.Field.DURATION;
import static com.raik383h_group_6.healthtracmobile.model.Field.STEPS;

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
    private String uid;

    @Inject
    public FeedPresenter(IAsyncActivityService asvc, IAsyncUserService usvc, IAsyncActivityReportService arsvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, @Assisted IActivityNavigator nav, @Assisted FeedView view) {
        this.view = view;
        this.nav = nav;
        this.uid = view.getStringExtra(view.getResource(R.string.EXTRA_USER_ID));
        long teamId = view.getLongExtra(view.getResource(R.string.EXTRA_TEAM_ID));
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
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
        try {
            if (uid != null) {
                gen = new UserFeedGenerator(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, view.getResources(), grant, nav, uid);

            } else {
                gen = new TeamFeedGenerator(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, view.getResources(), grant, nav, teamId);
            }
        } catch (Exception ignored) {
        }

    }

    public void onResume() {
        populateFeed();
    }

    private void populateFeed() {
        if (uid != null) {
            setupHeader();
            addGoals();
        } else {
            view.setFeedHeaderDisplay(false);
            view.setGoalHeaderDisplay(false);
        }
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
            view.displayMessage("Error generating feed");
        }
    }

    private void addGoals() {
        try {
            User u = usvc.getUserAsync(uid, grant.getAuthHeader());
            List<UserGoal> ugs = ugsvc.getUserGoals(u.getId(), grant.getAuthHeader());
            List<Goal> goals = gsvc.getGoals(grant.getAuthHeader());
            List<GoalProgress> goalsInProgress = new ArrayList<>();
            for (Goal g : goals) {
                UserGoal cor = getCorrespondingUserGoal(g, ugs);
                if (cor != null) {
                    GoalProgress progress = null;
                    switch (g.getField()) {
                        case DISTANCE:
                            progress = new GoalProgress(g, view.getResource(R.string.goal_progress_distance, u.getLifetimeDistance(), g.getThreshold()));
                            break;
                        case DURATION:
                            progress = new GoalProgress(g, view.getResource(R.string.goal_progress_duration, u.getLifetimeDuration(), g.getThreshold()));
                            break;
                        case STEPS:
                            progress = new GoalProgress(g, view.getResource(R.string.goal_progress_step, u.getLifetimeSteps(), g.getThreshold()));
                            break;
                    }
                    goalsInProgress.add(progress);
                }
            }
            view.setGoalsInProgress(goalsInProgress);
        } catch (ExecutionException | InterruptedException e) {
            view.setGoalHeaderDisplay(false);
        }
    }

    private UserGoal getCorrespondingUserGoal(Goal g, List<UserGoal> ugs) {
        for (UserGoal ug : ugs) {
            if (ug.getGoalID() == g.getId()) {
                return ug;
            }
        }
        return null;
    }

    private void setupHeader() {
        try {
            ActivityReport ar = getTodaysActivityReport();
            User u = usvc.getUserAsync(uid, grant.getAuthHeader());
            if (ar != null) {
                view.setSteps(view.getResource(R.string.steps_header, ar.getSteps(), (long) u.getLifetimeSteps()));
                view.setDuration(view.getResource(R.string.duration_header, ar.getDuration(), u.getLifetimeDuration()));
                view.setDistance(view.getResource(R.string.distance_header, ar.getDistance(), u.getLifetimeDistance()));
            } else {
                view.setSteps(view.getResource(R.string.steps_header, 0, u.getLifetimeSteps()));
                view.setDuration(view.getResource(R.string.duration_header, 0.0, u.getLifetimeDuration()));
                view.setDistance(view.getResource(R.string.distance_header, 0.0, u.getLifetimeDistance()));
            }
            view.setFeedHeaderDisplay(true);
        } catch (ExecutionException | InterruptedException e) {
            view.setFeedHeaderDisplay(false);
        }

    }

    private ActivityReport getTodaysActivityReport() throws InterruptedException, ExecutionException{
        List<ActivityReport> ars = arsvc.getActivityReportsAsync(uid, grant.getAuthHeader());
        ActivityReport todaysAr = null;
        for (ActivityReport ar : ars) {
            if (FormatUtils.format(ar.getDate()).equals(FormatUtils.format(new Date()))) {
                todaysAr = ar;
                break;
            }
        }
        return todaysAr;
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
