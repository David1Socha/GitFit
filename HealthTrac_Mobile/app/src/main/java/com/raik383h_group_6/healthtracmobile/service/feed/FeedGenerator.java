package com.raik383h_group_6.healthtracmobile.service.feed;

import android.content.res.Resources;
import android.util.Log;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;
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

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class FeedGenerator {

    protected AccessGrant grant;
    protected IAsyncActivityService asvc;
    protected IAsyncBadgeService bsvc;
    protected IAsyncEnergyLevelService esvc;
    protected IAsyncGoalService gsvc;
    protected IAsyncMealService mlsvc;
    protected IAsyncMembershipService mbsvc;
    protected IAsyncTeamService tsvc;
    protected IAsyncUserBadgeService ubsvc;
    protected IAsyncUserGoalService ugsvc;
    protected Resources res;
    protected IActivityNavigator nav;
    protected IAsyncUserService usvc;
    protected List<User> users;
    protected List<Team> teams;
    protected List<Badge> badges;
    protected List<Goal> goals;

    public FeedGenerator(IAsyncUserService usvc, IAsyncActivityService asvc, IAsyncBadgeService bsvc, IAsyncEnergyLevelService esvc, IAsyncGoalService gsvc, IAsyncMealService mlsvc, IAsyncMembershipService mbsvc, IAsyncTeamService tsvc, IAsyncUserBadgeService ubsvc, IAsyncUserGoalService ugsvc, Resources res, AccessGrant grant, IActivityNavigator nav) throws Exception {
        this.asvc = asvc;
        this.bsvc = bsvc;
        this.esvc = esvc;
        this.gsvc = gsvc;
        this.mlsvc = mlsvc;
        this.mbsvc = mbsvc;
        this.tsvc = tsvc;
        this.usvc = usvc;
        this.ubsvc = ubsvc;
        this.ugsvc = ugsvc;
        this.res = res;
        this.grant = grant;
        this.nav = nav;
        try {
            users = usvc.getUsersAsync(grant.getAuthHeader());
            teams = tsvc.getTeamsAsync(grant.getAuthHeader());
            goals = gsvc.getGoals(grant.getAuthHeader());
            badges = bsvc.getBadgesAsync(grant.getAuthHeader());
        } catch (Exception e) {
            Log.d("davidsocha", "api failure in feed generation");
            throw new Exception("Could not find users- api failure", e);
        }
    }

    public List<FeedModel> getFeedElements() {
        List<FeedModel> els = new ArrayList<>();
        try {
            els.addAll(getActivities());
            els.addAll(getEnergyLevels());
            els.addAll(getMeals());
            els.addAll(getMemberships());
            els.addAll(getUserBadges());
            els.addAll(getUserGoals());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
        return els;
    }

    protected List<FeedActivity> mapToFeedActivities(List<Activity> activities) {
        List<FeedActivity> fas = new ArrayList<>();
        for (Activity a : activities) {
            String username = getCorrespondingUserName(a.getUserId());
            FeedActivity fa = new FeedActivity(res.getString(R.string.feed_activity, username, a.getType().name().toLowerCase(), a.getSteps()), a.getStartDate(), nav, a);
            fas.add(fa);
        }
        return fas;
    }

    protected List<FeedEnergyLevel> mapToFeedEnergyLevels(List<EnergyLevel> els) {
        List<FeedEnergyLevel> fels = new ArrayList<>();
        for (EnergyLevel el : els) {
            String username = getCorrespondingUserName(el.getUserID());
            FeedEnergyLevel fel = new FeedEnergyLevel(res.getString(R.string.feed_energy_level, username, el.getMood().name().toLowerCase()), el.getDateCreated(), nav, el);
            fels.add(fel);
        }
        return fels;
    }

    protected List<FeedMeal> mapToFeedMeals(List<Meal> ms) {
        List<FeedMeal> fms = new ArrayList<>();
        for (Meal m : ms) {
            String username = getCorrespondingUserName(m.getUserID());
            FeedMeal fm = new FeedMeal(res.getString(R.string.feed_meal, username, m.getCalories()), m.getDateCreated(), nav, m);
            fms.add(fm);
        }
        return fms;
    }

    private String getCorrespondingUserName(String uid) {
        String name = "";
        for (User u : users) { // y no lambda :(
            if (u.getId().equals(uid)) {
                name = u.getUserName();
                break;
            }
        }
        return name;
    }

    private String getCorrespondingTeamName(long tid) {
        String correspondingTeamName = "";
        for (Team t : teams) {
            if (t.getId() == tid) {
                correspondingTeamName = t.getName();
                break;
            }
        }
        return correspondingTeamName;
    }

    protected List<FeedMembership> mapToFeedMemberships(List<Membership> ms) {
        List<FeedMembership> fms = new ArrayList<>();
        for (Membership m : ms) {
            String correspondingTeamName = getCorrespondingTeamName(m.getTeamID());
            String username = getCorrespondingUserName(m.getUserID());
            FeedMembership fm = new FeedMembership(res.getString(R.string.feed_membership, username, m.getMembershipStatus().name().toLowerCase(), correspondingTeamName), m.getDateCreated(), nav, m);
            fms.add(fm);
        }
        return fms;
    }

    protected List<FeedUserBadge> mapToFeedUserBadges(List<UserBadge> us) {
        List<FeedUserBadge> fus = new ArrayList<>();
        for (UserBadge u : us) {
            String username = getCorrespondingUserName(u.getUserID());
            String badgeName = getCorrespondingBadgeName(u.getBadgeID());
            FeedUserBadge fu = new FeedUserBadge(res.getString(R.string.feed_userbadge, username, badgeName), u.getDateCompleted(), nav, u);
            fus.add(fu);
        }
        return fus;
    }

    protected List<FeedUserGoal> mapToFeedUserGoals(List<UserGoal> us) {
        List<FeedUserGoal> fus = new ArrayList<>();
        for (UserGoal u : us) {
            String username = getCorrespondingUserName(u.getUserID());
            String goalName = getCorrespondingGoalName(u.getGoalID());
            FeedUserGoal fu = new FeedUserGoal(res.getString(R.string.feed_usergoal_start, username, goalName), u.getDateAssigned(), nav, u);
            if (u.getDateCompleted() != null) {
                FeedUserGoal fu2 = new FeedUserGoal(res.getString(R.string.feed_usergoal_end, username, goalName), u.getDateCompleted(), nav, u);
                fus.add(fu2);
            }
            fus.add(fu);
        }
        return fus;
    }

    private String getCorrespondingBadgeName(long badgeID) {
        String cor = "";
        for (Badge b : badges) {
            if (b.getId() == badgeID) {
                cor = b.getName();
                break;
            }
        }
        return cor;
    }

    private String getCorrespondingGoalName(long gid) {
        String cor = "";
        for (Goal g : goals) {
            if (g.getId() == gid) {
                cor = g.getName();
                break;
            }
        }
        return cor;
    }

    protected abstract List<FeedActivity> getActivities();
    protected abstract List<FeedEnergyLevel> getEnergyLevels();
    protected abstract List<FeedMeal> getMeals();
    protected abstract List<FeedMembership> getMemberships();
    protected abstract List<FeedUserBadge> getUserBadges();
    protected abstract List<FeedUserGoal> getUserGoals();
}
