package com.raik383h_group_6.healthtracmobile.application;

import android.app.Activity;
import android.content.Intent;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.activity.AuthenticationActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListTeamsActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.ListUsersActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthBrowserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.OAuthPromptActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.CreateUserActivity;
import com.raik383h_group_6.healthtracmobile.view.activity.EditUserActivity;
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
    public void openCreateUser(String token, String secret, String provider, int reqCode) {
            Intent intent = new Intent(activity, CreateUserActivity.class);
            intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_TOKEN), token);
            intent.putExtra(activity.getString(R.string.EXTRA_ACCESS_SECRET), secret);
            intent.putExtra(activity.getString(R.string.EXTRA_PROVIDER), provider);
            activity.startActivityForResult(intent, reqCode);
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
}
