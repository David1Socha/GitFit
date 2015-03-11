package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewTeamPresenter {
    private final Bundle extras;
    private final IResources resources;
    private final IActivityNavigator nav;
    private final ViewTeamView view;
    private Team team;
    private AccessGrant grant;
    private List<Membership> teamMemberships;
    private IAsyncMembershipService membershipService;
    private Membership userMembership;

    @Inject
    public ViewTeamPresenter(IAsyncMembershipService membershipService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted IActivityNavigator nav, @Assisted ViewTeamView view) {
        this.membershipService = membershipService;
        this.extras = extras;
        this.resources = resources;
        this.nav = nav;
        this.view = view;
        team = extras.getParcelable(resources.getString(R.string.EXTRA_TEAM));
        grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
    }

    public Membership getUserMembership() {
        return userMembership;
    }

    public void onClickEditTeam() {
        //nav.openUpdateTeam(team) TODO
    }

    public void onClickLeaveTeam() {
        Membership.MembershipStatus resetStatus = userMembership.getMembershipStatus();
        userMembership.setMembershipStatus(Membership.MembershipStatus.INACTIVE);
        updateCurrentMembership(resources.getString(R.string.success_leave_team), resources.getString(R.string.error_leave_team), resetStatus);
        refreshInfo();
    }

    private void createMembershipLocal() {
        userMembership = new Membership();
        userMembership.setDateCreated(new Date());
        userMembership.setDateModified(new Date());
        userMembership.setTeamID(team.getId());
        userMembership.setUserID(grant.getId());
        userMembership.setMembershipStatus(Membership.MembershipStatus.MEMBER);
    }

    public void onClickJoinTeam() {

        if (userMembership == null) {
            createMembershipLocal();
            createCurrentMembership(resources.getString(R.string.success_join_team), resources.getString(R.string.error_join_team));
        } else {
            Membership.MembershipStatus resetStatus = userMembership.getMembershipStatus();
            userMembership.setMembershipStatus(Membership.MembershipStatus.MEMBER);
            updateCurrentMembership(resources.getString(R.string.success_join_team), resources.getString(R.string.error_join_team), resetStatus);
        }
        updateFields();
    }

    private void createCurrentMembership(String successMessage, String failureMessage) {
        try {
            userMembership = membershipService.createMembershipAsync(userMembership, grant.getAuthHeader());
            if (userMembership == null) {
                view.displayMessage(failureMessage);
            } else {
                view.displayMessage(successMessage);
            }
        } catch (Exception e) {
            view.displayMessage(failureMessage);
        }
    }

    public void onResume() {
        refreshInfo();
    }

    private void refreshInfo() {
        updateTeamMemberships();
        updateCurrentUserStatus();
        updateFields();
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle extras) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.UPDATE_TEAM:
                    team = extras.getParcelable(resources.getString(R.string.EXTRA_TEAM));
                    updateFields();
                    break;
                default:
                    break;
            }
        }
    }

    private void updateTeamMemberships() {
        try {
            teamMemberships = membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader());
        } catch (ExecutionException | InterruptedException ignored) {
        }
    }

    private void updateCurrentUserStatus() {
        try {
            for (Membership m : teamMemberships) { //where are you LINQ?
                if (m.getUserID().equals(grant.getId())) {
                    userMembership = m;
                    return; //only one membership per team + user combo
                }
            }
        } catch (NullPointerException ignored) {
        }
        userMembership = null;
    }

    private void updateCurrentMembership(String successMessage, String failureMessage, Membership.MembershipStatus resetStatus) {
        try {
            membershipService.updateMembershipAsync(userMembership.getId(), userMembership, grant.getAuthHeader());
            view.displayMessage(successMessage);
        } catch (Exception e) {
            view.displayMessage(failureMessage);
            userMembership.setMembershipStatus(resetStatus);
        }
    }

    private void updateFields() {
        view.setTeamName(team.getName());
        view.setDateCreated(FormatUtils.format(team.getDateCreated()));
        view.setDescription(team.getDescription());
        Membership.MembershipStatus status = userMembership == null ? Membership.MembershipStatus.INACTIVE : userMembership.getMembershipStatus();
        switch (status) {
            case MEMBER:
                view.setShowEditTeam(false);
                view.setShowJoinTeam(false);
                view.setShowLeaveTeam(true);
                break;
            case INACTIVE:
                view.setShowEditTeam(false);
                view.setShowJoinTeam(true);
                view.setShowLeaveTeam(false);
                break;
            case ADMIN:
                view.setShowEditTeam(true);
                view.setShowJoinTeam(false);
                view.setShowLeaveTeam(true);
                break;
            case BANNED:
            default:
                view.setShowEditTeam(false);
                view.setShowJoinTeam(false);
                view.setShowLeaveTeam(false);
                break;
        }
    }
}
