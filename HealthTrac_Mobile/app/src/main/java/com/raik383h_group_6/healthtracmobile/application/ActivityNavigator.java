package com.raik383h_group_6.healthtracmobile.application;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;


import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Point;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.activity.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.BanMembersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ChallengeUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateActivityActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.EditTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateEnergyLevelActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateGoalActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateMealActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.FeedActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.InviteMembersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.EditUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ActivityActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.TeamLeaderboardActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewActivityActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewActivityReportActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewBadgeActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewEnergyLevelActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewGoalActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewMealActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewPathActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewUserActivity;
import com.raik383h_group_6.healthtracmobile.view.fragment.EventFragment;
import com.raik383h_group_6.healthtracmobile.view.fragment.FeedFragment;
import com.raik383h_group_6.healthtracmobile.view.fragment.ListTeamsFragment;
import com.raik383h_group_6.healthtracmobile.view.fragment.ListUsersFragment;
import com.raik383h_group_6.healthtracmobile.view.fragment.ViewUserFragment;

import java.util.ArrayList;
import java.util.List;

public class ActivityNavigator implements IActivityNavigator {
    private final Activity activity;

    public ActivityNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void openOAuthBrowser(final String provider, final int reqCode) {
        Intent intent = new Intent(activity, OAuthBrowserActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void openMain() {
        Intent restartIntent = activity.getPackageManager()
                .getLaunchIntentForPackage(activity.getPackageName() );
        PendingIntent intent = PendingIntent.getActivity(
                activity, 0,
                restartIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager manager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, intent);
        System.exit(0);
    }

    @Override
    public void finishOAuthPromptInShame() {
        genericFinishCancelled();
    }

    @Override
    public void finishOAuthPromptWithInfo(String accessToken, String accessSecret, String provider) {
        Intent data = new Intent();
        data.putExtra(activity.getString(R.string.EXTRA_ACCESS_SECRET), accessSecret);
        data.putExtra(activity.getString(R.string.EXTRA_ACCESS_TOKEN), accessToken);
        data.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void finishOAuthBrowserWithToken(Token token, String provider) {
        Intent data = new Intent();
        data.putExtra(activity.getString(R.string.EXTRA_ACCESS_SECRET), token.getSecret());
        data.putExtra(activity.getString(R.string.EXTRA_ACCESS_TOKEN), token.getToken());
        data.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void finishOAuthBrowserInShame() {
        genericFinishCancelled();
    }

    @Override
    public void finishCreateUserSuccess() {
        genericFinishOk();
    }

    @Override
    public void finishCreateUserFailure() {
        genericFinishCancelled();
    }

    @Override
    public void finishCreateTeamSuccess() {
        genericFinishOk();
    }

    @Override
    public void finishCreateTeamFailure() {
        genericFinishCancelled();
    }

    @Override
    public void openCreateUser(String token, String secret, String provider, int reqCode) {
            Intent intent = new Intent(activity, CreateUserActivity.class);
            intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_TOKEN), token);
            intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_SECRET), secret);
            intent.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
            activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void openCreateTeam(AccessGrant g) {
        Intent intent = new Intent(activity, CreateTeamActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(intent);
    }

    @Override
    public void openOAuthPrompt(int reqCode) {
        Intent intent = new Intent(activity, OAuthPromptActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void openAuthentication(int reqCode) {
        Intent intent = new Intent(activity, AuthenticationActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void finishWithAccessGrant(AccessGrant grant) {
        Intent data = new Intent();
        data.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void openViewUser(User u, AccessGrant grant) {
        Intent intent = new Intent(activity, ViewUserActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_USER), u);
        activity.startActivity(intent);
    }

    @Override
    public void openViewUserFragment(User u, AccessGrant grant) {
        Fragment fragment = new ViewUserFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        bundle.putParcelable(activity.getString(R.string.EXTRA_USER), u);
        Log.i("User", u.toString());
        fragment.setArguments(bundle);
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void finishViewUser() {
        genericFinishOk();
    }

    @Override
    public void openListUsers(AccessGrant g) {
        Fragment fragment = new ListUsersFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        fragment.setArguments(bundle);
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void finishListUsers() {
        genericFinishOk();
    }

    @Override
    public void finishListTeams() {genericFinishOk(); }

    @Override
    public void openListTeams(AccessGrant g) {
        Fragment fragment = new ListTeamsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        fragment.setArguments(bundle);
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void openViewTeam(Team t, AccessGrant g) {
        Intent intent = new Intent(activity, ViewTeamActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_TEAM), t);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(intent);
    }

    @Override
    public void finishViewTeam() {
        genericFinishOk();
    }

    private void genericFinishOk() {
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    private void genericFinishCancelled() {
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }

    @Override
    public void openEditUser(AccessGrant grant, User user, int reqCode) {
        Intent intent = new Intent(activity, EditUserActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_USER), user);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void openEditTeam(AccessGrant grant, Team team, int reqCode) {
        Intent intent = new Intent(activity, EditTeamActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_TEAM), team);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    public void openEvent(AccessGrant grant) {
        Fragment fragment = new EventFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        fragment.setArguments(bundle);
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void openTeamLeaderboard(AccessGrant grant, Team team) {
        Intent intent = new Intent(activity, TeamLeaderboardActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_TEAM), team);
        activity.startActivity(intent);
    }

    @Override
    public void openInviteMembers(AccessGrant grant, Team team) {
        Intent intent = new Intent(activity, InviteMembersActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_TEAM), team);
        activity.startActivity(intent);
    }

    @Override
    public void openBanMembers(AccessGrant grant, Team team) {
        Intent intent = new Intent(activity, BanMembersActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_TEAM), team);
        activity.startActivity(intent);
    }

    public void openChallengeUsers(AccessGrant grant, Goal goal) {
        Intent intent = new Intent(activity, ChallengeUsersActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        intent.putExtra(activity.getString(R.string.EXTRA_GOAL), goal);
        activity.startActivity(intent);
    }

    @Override
    public void finishEditUserSuccess(User user) {
        Intent data = new Intent();
        data.putExtra(activity.getString(R.string.EXTRA_USER), user);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void finishEditUserFailure() {
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }

    @Override
    public void finishEditTeamSuccess(Team team) {
        Intent data = new Intent();
        data.putExtra(activity.getString(R.string.EXTRA_TEAM), team);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void finishEditTeamFailure() {
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }

    @Override
    public void openActivity(AccessGrant g) {
        Intent i = new Intent(activity, ActivityActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(i);
    }

    @Override
    public void finishActivity() {
        genericFinishOk();
    }

    @Override
    public void finishCreateGoal() {
        genericFinishOk();
    }

    @Override
    public void openCreateGoal(AccessGrant g) {
        Intent i = new Intent(activity, CreateGoalActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(i);
    }

    @Override
    public void finishCreateEnergyLevel() {
        genericFinishOk();
    }

    @Override
    public void openCreateEnergyLevel(AccessGrant g) {
        Intent i = new Intent(activity, CreateEnergyLevelActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(i);
    }

    @Override
    public void finishCreateMeal() {
        genericFinishOk();
    }

    @Override
    public void openCreateMeal(AccessGrant g) {
        Intent i = new Intent(activity, CreateMealActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(i);
    }

    @Override
    public void openFeed(String uid, AccessGrant grant) {
        Intent i = new Intent(activity, FeedActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_USER_ID), uid);
        activity.startActivity(i);
    }

    @Override
    public void openFeedFragment(String uid, AccessGrant grant) {
        Fragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        bundle.putString(activity.getString(R.string.EXTRA_USER_ID), uid);
        fragment.setArguments(bundle);
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void openFeed(long teamId, AccessGrant grant) {
        Intent i = new Intent(activity, FeedActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_TEAM_ID), teamId);
        activity.startActivity(i);
    }

    @Override
    public void openActivityReport(ActivityReport ar, String username, AccessGrant grant) {
        Intent i = new Intent(activity, ViewActivityReportActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_USERNAME), username);
        i.putExtra(activity.getString(R.string.EXTRA_ACTIVITY_REPORT), ar);
        activity.startActivity(i);
    }

    @Override
    public void openViewEnergyLevel(EnergyLevel el, String username, AccessGrant grant) {
        Intent i = new Intent(activity, ViewEnergyLevelActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_USERNAME), username);
        i.putExtra(activity.getString(R.string.EXTRA_ENERGY_LEVEL), el);
        activity.startActivity(i);
    }

    @Override
    public void openViewMeal(Meal m, String username, AccessGrant grant) {
        Intent i = new Intent(activity, ViewMealActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_USERNAME), username);
        i.putExtra(activity.getString(R.string.EXTRA_MEAL), m);
        activity.startActivity(i);
    }

    @Override
    public void openViewGoal(Goal g, AccessGrant grant) {
        Intent i = new Intent(activity, ViewGoalActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_GOAL), g);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        activity.startActivity(i);
    }

    @Override
    public void openViewBadge(Badge b, AccessGrant grant) {
        Intent i = new Intent(activity, ViewBadgeActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        i.putExtra(activity.getString(R.string.EXTRA_BADGE), b);
        activity.startActivity(i);
    }

    @Override
    public void openViewActivity(com.raik383h_group_6.healthtracmobile.model.Activity a, String u, AccessGrant g) {
        Intent i = new Intent(activity, ViewActivityActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        i.putExtra(activity.getString(R.string.EXTRA_ACTIVITY), a);
        i.putExtra(activity.getString(R.string.EXTRA_USERNAME), u);
        activity.startActivity(i);
    }

    @Override
    public void openCreateActivity(AccessGrant grant) {
        Intent i = new Intent(activity, CreateActivityActivity.class);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        activity.startActivity(i);
    }

    @Override
    public void openViewPath(ArrayList<Point> pts, AccessGrant grant) {
        Intent i = new Intent(activity, ViewPathActivity.class);
        i.putParcelableArrayListExtra(activity.getString(R.string.EXTRA_POINTS), pts);
        i.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        activity.startActivity(i);
    }
}
