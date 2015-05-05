package com.raik383h_group_6.healthtracmobile.presenter;


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
import com.raik383h_group_6.healthtracmobile.view.InviteMembersView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InviteMembersPresenter extends BasePresenter{

    private IActivityNavigator nav;
    private InviteMembersView view;
    private IAsyncUserService userService;
    private IAsyncMembershipService membershipService;
    private AccessGrant grant;
    private Team team;

    @Inject
    public InviteMembersPresenter(IAsyncUserService userService, IAsyncMembershipService membershipService, @Assisted IActivityNavigator nav, @Assisted InviteMembersView view) {
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
            users = getUsersNotOnTeam();
            if (users != null) {
                view.setNoUsersMessageDisplay(false);
                view.setUsers(users);
            } else {
                view.setNoUsersMessageDisplay(true);
            }

        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
    }

    private List<User> getUsersNotOnTeam() throws ExecutionException, InterruptedException{
        List<User> users = new ArrayList<User>();
        List<Membership> teamMembership = membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader());
        List<User> allUsers = userService.getUsersAsync(grant.getAuthHeader());
        if(teamMembership == null || allUsers == null) {
            return null;
        }
        List<String> userIds = new ArrayList<String>();
        for (Membership membership: teamMembership) {
            userIds.add(membership.getUserID());
        }
        for (User user: allUsers) {
            if(!userIds.contains(user.getId())) {
                users.add(user);
            }
        }
        return users;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }

    public void onClickInviteMember(User user) {
        Membership newMember = new Membership();
        newMember.setDateCreated(new Date());
        newMember.setDateModified(new Date());
        newMember.setTeamID(team.getId());
        newMember.setUserID(user.getId());
        newMember.setMembershipStatus(Membership.MembershipStatus.MEMBER);
        try {
            membershipService.createMembershipAsync(newMember, grant.getAuthHeader());
        } catch (Exception e) {
        }
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
