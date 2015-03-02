package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamActivity;
import com.raik383h_group_6.healthtracmobile.view.ViewUserActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewTeamPresenter {
    private final Bundle extras;
    private final IResources resources;
    private final ActivityNavigator nav;
    private final ViewTeamActivity view;
    private Team team;
    private AccessGrant grant;
    private List<Membership> teamMemberships;
    private MembershipService membershipService;
    public static final int UPDATE = 1;
    private Membership userMembership;

    @Inject
    public ViewTeamPresenter(MembershipService membershipService, @Assisted Bundle extras, @Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted ViewTeamActivity view) {
        this.membershipService = membershipService;
        this.extras = extras;
        this.resources = resources;
        this.nav = nav;
        this.view = view;
    }

    public void onCreate() {
        team = extras.getParcelable(resources.getString(R.string.EXTRA_TEAM));
        grant = extras.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onClickEditTeam() {
        //nav.openUpdateTeam(team) TODO
        refreshInfo();
    }

    public void onClickLeaveTeam() {
        userMembership.setMembershipStatus(Membership.MembershipStatus.INACTIVE);
        postCurrentMembership(resources.getString(R.string.success_leave_team), resources.getString(R.string.error_leave_team));
        refreshInfo();
    }

    public void onClickJoinTeam() {
        userMembership.setMembershipStatus(Membership.MembershipStatus.MEMBER);
        postCurrentMembership(resources.getString(R.string.success_join_team), resources.getString(R.string.error_join_team));
        refreshInfo();
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
                case UPDATE:
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
            teamMemberships = getMembershipsAsync(team.getId(), grant.getAuthHeader());
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

    private List<Membership> getMembershipsAsync(final long teamId, final String auth) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Membership>>() {
            @Override
            protected List<Membership> doInBackground(Void... params) {
                try {
                    return membershipService.getMemberships(teamId, auth);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    private void postCurrentMembership(String successMessage, String failureMessage) {
        try {
            Exception updateException = updateMembershipAsync(userMembership, grant);
            if (updateException != null) {
                throw updateException;
            } else {
                view.displayMessage(successMessage);
            }
        } catch (Exception e) {
            view.displayMessage(failureMessage);
        }
    }

    private Exception updateMembershipAsync(final Membership membership, final AccessGrant grant) throws ExecutionException, InterruptedException {
        final long membershipId = membership.getId();
        return new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    membershipService.updateMembership(membershipId, membership, grant.getAuthHeader());
                    return null;
                } catch (Exception e) {
                    return e;
                }

            }
        }.execute().get();
    }

    private void updateFields() {
        view.setTeamName(team.getName());
        view.setDateCreated(FormatUtils.format(team.getDateCreated()));
        view.setDescription(team.getDescription());
        Membership.MembershipStatus status = userMembership == null ? Membership.MembershipStatus.INACTIVE : userMembership.getMembershipStatus();
        switch(status) {
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
