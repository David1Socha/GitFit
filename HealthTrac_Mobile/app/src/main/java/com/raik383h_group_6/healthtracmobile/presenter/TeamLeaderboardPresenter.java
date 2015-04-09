package com.raik383h_group_6.healthtracmobile.presenter;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.TeamLeaderboardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeamLeaderboardPresenter extends BasePresenter{

    private IActivityNavigator nav;
    private TeamLeaderboardView view;
    private IAsyncUserService userService;
    private IAsyncMembershipService membershipService;
    private AccessGrant grant;
    private Team team;

    @Inject
    public TeamLeaderboardPresenter(IAsyncUserService userService, IAsyncMembershipService membershipService, @Assisted IActivityNavigator nav, @Assisted TeamLeaderboardView view) {
        this.nav = nav;
        this.userService = userService;
        this.membershipService = membershipService;
        this.view = view;
        team = (Team) view.getParcelableExtra(view.getResource(R.string.EXTRA_TEAM));
        grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onResume() {
        populateUsers();
    }

    public void populateUsers() {
        List<User> users = null;
        try {
            if (grant == null || grant.isExpired()) {
                view.setNoUsersMessageDisplay(true);
                return;
            }
            users = getUsersOnTeam();
            if (users != null) {
                sortUsersBySteps(users);
                view.setNoUsersMessageDisplay(false);
                view.setUsers(users);
            } else {
                view.setNoUsersMessageDisplay(true);
            }

        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
    }

    private List<User> getUsersOnTeam() throws ExecutionException, InterruptedException{
        List<User> users = new ArrayList<User>();
        List<Membership> teamMembership = membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader());
        List<User> allUsers = userService.getUsersAsync(grant.getAuthHeader());
        if(teamMembership == null || allUsers == null) {
            Log.i("Team Membership", "IS NULL");
            Log.i("Team ID", team.getId() + "");
            Log.i("Token", grant.getAuthHeader());
            Log.i("Membership Service", membershipService.toString());

            return null;
        }
        List<String> userIds = new ArrayList<String>();
        for (Membership membership: teamMembership) {
            userIds.add(membership.getUserID());
        }
        for (User user: allUsers) {
            if(userIds.contains(user.getId())) {
                users.add(user);
            }
        }
        return users;
    }

    private void sortUsersBySteps(List<User> users) {
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return (int) (rhs.getLifetimeSteps() - lhs.getLifetimeSteps());
            }
        };
        Collections.sort(users, comparator);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
