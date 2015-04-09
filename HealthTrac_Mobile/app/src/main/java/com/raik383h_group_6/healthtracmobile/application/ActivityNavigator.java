package com.raik383h_group_6.healthtracmobile.application;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.ViewEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.activity.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.EditTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateEnergyLevelActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateGoalActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateMealActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.FeedActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.EditUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ActivityActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewActivityReportActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewEnergyLevelActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ViewUserActivity;

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
        intent.putExtra(activity.getString(R.string.EXTRA_USER), u);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), grant);
        activity.startActivity(intent);
    }

    @Override
    public void finishViewUser() {
        genericFinishOk();
    }

    @Override
    public void openListUsers(AccessGrant g) {
        Intent intent = new Intent(activity, ListUsersActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(intent);
    }

    @Override
    public void finishListUsers() {
        genericFinishOk();
    }

    @Override
    public void finishListTeams() {genericFinishOk(); }

    @Override
    public void openListTeams(AccessGrant g) {
        Intent intent = new Intent(activity, ListTeamsActivity.class);
        intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_GRANT), g);
        activity.startActivity(intent);
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
}
