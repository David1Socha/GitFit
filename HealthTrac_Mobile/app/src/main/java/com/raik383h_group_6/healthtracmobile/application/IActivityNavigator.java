package com.raik383h_group_6.healthtracmobile.application;

import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;

public interface IActivityNavigator {
    void openOAuthBrowser(String provider, int reqCode);

    void finishOAuthPromptInShame();

    void finishOAuthPromptWithInfo(String accessToken, String accessSecret, String provider);

    void finishOAuthBrowserWithToken(Token token, String provider);

    void finishOAuthBrowserInShame();

    void finishCreateUserSuccess();

    void finishCreateUserFailure();

    void openCreateUser(String token, String secret, String provider, int reqCode);

    void openOAuthPrompt(int reqCode);

    void openAuthentication(int reqCode);

    void finishWithAccessGrant(AccessGrant grant);

    void openViewUser(User u, AccessGrant grant);

    void finishViewUser();

    void openListUsers(AccessGrant g);

    void finishListUsers();

    void finishListTeams();

    void openListTeams(AccessGrant g);

    void openViewTeam(Team t, AccessGrant g);

    void finishViewTeam();

    void openEditUser(AccessGrant grant, User user, int reqCode);

    void finishEditUserSuccess(User user);

    void finishEditUserFailure();
}
