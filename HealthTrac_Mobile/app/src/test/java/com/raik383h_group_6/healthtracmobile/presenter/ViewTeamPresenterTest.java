package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMembershipService;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TEAM_KEY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewTeamPresenterTest {
    private ViewTeamPresenter presenter;
    private Bundle bundle;
    private IResources resources;
    private Team team;
    private AccessGrant grant;
    private Membership memberMembership;
    private IActivityNavigator nav;
    private ViewTeamView view;
    private IAsyncMembershipService membershipService;
    private ArrayList<Membership> memberships;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        team = ModelGenerator.genBasicTeam();
        grant = ModelGenerator.genBasicGrant();
        memberMembership = ModelGenerator.genMemberMembership();
        memberships = new ArrayList<Membership>();
        bundle = mock(Bundle.class);
        view = mock(ViewTeamView.class);
        when(bundle.getParcelable(TEAM_KEY)).thenReturn(team);
        when(bundle.getParcelable(GRANT_KEY)).thenReturn(grant);
        resources = ModelGenerator.genStubbedResources();
        membershipService = mock(IAsyncMembershipService.class);
        presenter = new ViewTeamPresenter(membershipService, bundle, resources, nav, view);
    }

    @Test
    public void onResumeUpdatesFields() {
        presenter.onResume();
        assertCorrectlyUpdatesFields(view, team);
    }

    @Test
    public void onResumeLoadsCurrentMembership() throws ExecutionException, InterruptedException {
        memberships.add(memberMembership);
        when(membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader())).thenReturn(memberships);
        presenter.onResume();
        verify(membershipService).getMembershipsAsync(team.getId(), grant.getAuthHeader());
        assertEquals(memberMembership, presenter.getUserMembership());
    }

    @Test
    public void onActivityResultUpdatesFieldsOnSuccess() {
        Bundle extras = mock(Bundle.class);
        when(extras.getParcelable(TEAM_KEY)).thenReturn(team);
        presenter.onActivityResult(ViewUserPresenter.UPDATE, Activity.RESULT_OK, extras);
        assertCorrectlyUpdatesFields(view, team);
    }

    @Test
    public void onClickJoinTeamCreatesMembershipWhenNull() throws ExecutionException, InterruptedException {
        when(membershipService.createMembershipAsync(any(Membership.class), eq(grant.getAuthHeader()))).thenReturn(memberMembership);
        presenter.onResume();
        presenter.onClickJoinTeam();
        Membership createdMembership = presenter.getUserMembership();
        assertEquals(memberMembership, createdMembership);
    }

    @Test
    public void onClickJoinTeamUpdatesMembershipWhenExtant() throws Exception {
        memberships.add(memberMembership);
        when(membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader())).thenReturn(memberships);
        presenter.onResume();
        presenter.onClickJoinTeam();
        verify(membershipService).updateMembershipAsync(memberMembership.getId(), memberMembership, grant.getAuthHeader());
    }

    @Test
    public void onClickLeaveTeamUpdatesMembershipAsInactive() throws Exception {
        memberships.add(memberMembership);
        when(membershipService.getMembershipsAsync(team.getId(), grant.getAuthHeader())).thenReturn(memberships);
        presenter.onResume();
        presenter.onClickLeaveTeam();
        verify(membershipService).updateMembershipAsync(eq(memberMembership.getId()), argThat(new HasSpecifiedMembershipStatus(Membership.MembershipStatus.INACTIVE)), eq(grant.getAuthHeader()));
    }

    private void assertCorrectlyUpdatesFields(ViewTeamView view, Team team) {
        verify(view).setDateCreated(FormatUtils.format(team.getDateCreated()));
        verify(view).setDescription(team.getDescription());
        verify(view).setTeamName(team.getName());
    }

    private static class HasSpecifiedMembershipStatus extends ArgumentMatcher<Membership> {

        Membership.MembershipStatus expectedStatus;

        public HasSpecifiedMembershipStatus(Membership.MembershipStatus status) {
             expectedStatus = status;
        }

        @Override
        public boolean matches(Object o) {
            return ((Membership) o).getMembershipStatus() == expectedStatus;
        }
    }

}
