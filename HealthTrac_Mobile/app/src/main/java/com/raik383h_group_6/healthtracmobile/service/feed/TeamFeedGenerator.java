package com.raik383h_group_6.healthtracmobile.service.feed;

import android.content.res.Resources;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedActivity;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedActivityReport;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedEnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedMeal;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedMembership;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedUserBadge;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedUserGoal;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityReportService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMealService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;

import java.util.ArrayList;
import java.util.List;

public class TeamFeedGenerator extends FeedGenerator {

    private long teamId;
    private List<String> uids;

    public TeamFeedGenerator(IAsyncActivityReportService arsvc, IAsyncUserService usvc, IAsyncActivityService asvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, Resources res, AccessGrant grant, IActivityNavigator nav, long teamId) throws Exception {
        super(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, res, grant, nav);
        this.teamId = teamId;
        try {
            List<Membership> memberships = mbsvc.getMembershipsAsync(teamId, grant.getAuthHeader());
            uids = new ArrayList<>(memberships.size());
            for (Membership m : memberships) {
                uids.add(m.getUserID());
            }
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation");
            throw new Exception("api failure in feed construction", e);
        }
    }

    @Override
    protected List<FeedActivity> getActivities() {
        try {
            List<Activity> allActivities = asvc.getActivitiesAsync(grant.getAuthHeader());
            List<Activity> activities = new ArrayList<>();
            for (Activity a : allActivities) {
                if (uids.contains(a.getUserId())) {
                    activities.add(a);
                }
            }
            return mapToFeedActivities(activities);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (activity)");
            return null;
        }
    }

    @Override
    protected List<FeedEnergyLevel> getEnergyLevels() {
        try {
            List<EnergyLevel> allEls = esvc.getEnergyLevelsAsync(grant.getAuthHeader());
            List<EnergyLevel> els = new ArrayList<>();
            for (EnergyLevel el : allEls) {
                if (uids.contains(el.getUserID())) {
                    els.add(el);
                }
            }
            return mapToFeedEnergyLevels(els);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (energy level)");
            return null;
        }
    }

    @Override
    protected List<FeedMeal> getMeals() {
        try {
            List<Meal> allMs = mlsvc.getMeals(grant.getAuthHeader());
            List<Meal> ms = new ArrayList<>();
            for (Meal m : allMs) {
                if (uids.contains(m.getUserID())) {
                    ms.add(m);
                }
            }
            return mapToFeedMeals(ms);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (meal)");
            return null;
        }
    }

    @Override
    protected List<FeedMembership> getMemberships() {
        try {
            List<Membership> ms = mbsvc.getMembershipsAsync(teamId, grant.getAuthHeader());
            return mapToFeedMemberships(ms);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (membership)");
            return null;
        }
    }

    @Override
    protected List<FeedUserBadge> getUserBadges() {
        try {
            List<UserBadge> allUbs = ubsvc.getUserBadges(grant.getAuthHeader());
            List<UserBadge> ubs = new ArrayList<>();
            for (UserBadge ub : allUbs) {
                if (uids.contains(ub.getUserID())) {
                    ubs.add(ub);
                }
            }
            return mapToFeedUserBadges(ubs);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (userbadge)");
            return null;
        }
    }

    @Override
    protected List<FeedUserGoal> getUserGoals() {
        try {
            List<UserGoal> allUgs = ugsvc.getUserGoals(grant.getAuthHeader());
            List<UserGoal> ugs = new ArrayList<>();
            for (UserGoal ug : allUgs) {
                if (uids.contains(ug.getUserID())) {
                    ugs.add(ug);
                }
            }
            return mapToFeedUserGoals(ugs);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (usergoal)");
            return null;
        }
    }

    @Override
    protected List<FeedActivityReport> getActivityReports() {
        try {
            List<ActivityReport> allAs = arsvc.getActivityReportsAsync(grant.getAuthHeader());
            List<ActivityReport> as = new ArrayList<>();
            for (ActivityReport a : allAs) {
                if (uids.contains(a.getUserID())) {
                    as.add(a);
                }
            }
            return mapToFeedActivityReports(as);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (activity report)");
            return null;
        }
    }
}
