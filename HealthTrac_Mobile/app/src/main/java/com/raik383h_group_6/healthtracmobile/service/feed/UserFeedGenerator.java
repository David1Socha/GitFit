package com.raik383h_group_6.healthtracmobile.service.feed;

import android.content.res.Resources;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedActivity;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedEnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedMeal;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedMembership;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedUserBadge;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedUserGoal;
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

    public UserFeedGenerator(String username, IAsyncUserService usvc, IAsyncActivityService asvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, Resources res, AccessGrant grant, IActivityNavigator nav, String uid) throws Exception {
        super(username, usvc, asvc, bsvc, esvc, gsvc, mlsvc, mbsvc, tsvc, ubsvc, ugsvc, res, grant, nav);
        this.uid = uid;
    }


    @Override
    protected List<FeedActivity> getActivities() {
        try {
            List<Activity> activities = asvc.getActivitiesAsync(uid, grant.getAuthHeader());
            return mapToFeedActivities(activities);
        } catch (InterruptedException | ExecutionException e) {
            Log.d("davidsocha", "api failure in feed generation");
            return null;
        }
    }

    @Override
    protected List<FeedEnergyLevel> getEnergyLevels() {
        return null;
    }

    @Override
    protected List<FeedMeal> getMeals() {
        return null;
    }

    @Override
    protected List<FeedMembership> getMemberships() {
        return null;
    }

    @Override
    protected List<FeedUserBadge> getUserBadges() {
        return null;
    }

    @Override
    protected List<FeedUserGoal> getUserGoals() {
        return null;
    }
}
