package com.raik383h_group_6.healthtracmobile.application;

import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;

public interface IActivityNavigator {
    void openOAuthBrowser(String provider, int reqCode);

    void openMain();

    void finishOAuthPromptInShame();

    void finishOAuthPromptWithInfo(String accessToken, String accessSecret, String provider);

    void finishOAuthBrowserWithToken(Token token, String provider);

    void finishOAuthBrowserInShame();

    void finishCreateUserSuccess();

    void finishCreateUserFailure();

    void finishCreateTeamSuccess();

    void finishCreateTeamFailure();

    void openCreateUser(String token, String secret, String provider, int reqCode);

    void openOAuthPrompt(int reqCode);

    void openAuthentication(int reqCode);

    void finishWithAccessGrant(AccessGrant grant);

    void openViewUser(User u, AccessGrant grant);

    void finishViewUser();

    void openListUsers(AccessGrant g);

    void finishListUsers();

    void finishListTeams();

    void openCreateTeam(AccessGrant g);

    void openListTeams(AccessGrant g);

    void openViewTeam(Team t, AccessGrant g);

    void finishViewTeam();

    void openEditUser(AccessGrant grant, User user, int reqCode);

    void openEditTeam(AccessGrant grant, Team team, int reqCode);

    void finishEditUserSuccess(User user);

    void finishEditUserFailure();

    void finishEditTeamSuccess(Team team);

    void finishEditTeamFailure();

    void openActivity(AccessGrant g);

    void finishActivity();

    void finishCreateGoal();

    void openCreateGoal(AccessGrant grant);

    void finishCreateEnergyLevel();

    void openCreateEnergyLevel(AccessGrant grant);

    void finishCreateMeal();

    void openCreateMeal(AccessGrant grant);

    void openFeed(String uid, AccessGrant grant);

    void openFeed(long teamId, AccessGrant grant);

    void openActivityReport(ActivityReport ar, String username, AccessGrant grant);
    void openViewEnergyLevel(EnergyLevel el, String username, AccessGrant grant);
}
