package com.raik383h_group_6.healthtracmobile.service.feed;

import android.content.res.Resources;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
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
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
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
import java.util.concurrent.ExecutionException;

public class UserFeedGenerator extends FeedGenerator {

    private String uid;

    public UserFeedGenerator(IAsyncActivityReportService arsvc, IAsyncUserService usvc, IAsyncActivityService asvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, Resources res, AccessGrant grant, IActivityNavigator nav, String uid) throws Exception {
        super(arsvc, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, res, grant, nav);
        this.uid = uid;
    }


    @Override
    protected List<FeedActivity> getActivities() {
        try {
            List<Activity> activities = asvc.getActivitiesAsync(uid, grant.getAuthHeader());
            return mapToFeedActivities(activities);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (activity)");
            return null;
        }
    }

    @Override
    protected List<FeedEnergyLevel> getEnergyLevels() {
        try {
            List<EnergyLevel> els = esvc.getEnergyLevelsAsync(uid, grant.getAuthHeader());
            return mapToFeedEnergyLevels(els);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (energy level)");
            return null;
        }
    }

    @Override
    protected List<FeedMeal> getMeals() {
        try {
            List<Meal> ms = mlsvc.getMeals(uid, grant.getAuthHeader());
            return mapToFeedMeals(ms);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (meal)");
            return null;
        }
    }

    @Override
    protected List<FeedMembership> getMemberships() {
        try {
            List<Membership> ms = mbsvc.getMembershipsAsync(uid, grant.getAuthHeader());
            return mapToFeedMemberships(ms);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (membership)");
            return null;
        }
    }

    @Override
    protected List<FeedUserBadge> getUserBadges() {
        try {
            List<UserBadge> us = ubsvc.getUserBadges(uid, grant.getAuthHeader());
            return mapToFeedUserBadges(us);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (userbadge)");
            return null;
        }
    }

    @Override
    protected List<FeedUserGoal> getUserGoals() {
        try {
            List<UserGoal> us = ugsvc.getUserGoals(uid, grant.getAuthHeader());
            return mapToFeedUserGoals(us);
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation (usergoal)");
            return null;
        }
    }

    @Override
    protected List<FeedActivityReport> getActivityReports() {
        try {
            List<ActivityReport> as = arsvc.getActivityReportsAsync(uid, grant.getAuthHeader());
            return mapToFeedActivityReports(as);
        } catch(Exception e) {
            Log.d("davidsocha", "api failure in feed generation (activity report)");
            return null;
        }
    }
}
