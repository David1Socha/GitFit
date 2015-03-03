package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ViewTeamPresenterTest {
    private ViewTeamPresenter presenter;
    private Bundle bundle;
    private IResources resources;
    private Team team;
    private AccessGrant grant;
    private IActivityNavigator nav;
    private ViewTeamView view;
    private MembershipService membershipService;
    private static final String TEAM_KEY ="team", GRANT_KEY = "grant";

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        team = ModelGenerator.genBasicTeam();
        grant = ModelGenerator.genBasicGrant();
        bundle = mock(Bundle.class);
        view = mock(ViewTeamView.class);
        when(bundle.getParcelable(TEAM_KEY)).thenReturn(team);
        when(bundle.getParcelable(GRANT_KEY)).thenReturn(grant);
        resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_TEAM)).thenReturn(TEAM_KEY);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        membershipService = mock(MembershipService.class);
        presenter = new ViewTeamPresenter(membershipService, bundle, resources, nav, view);
    }

    @Test
    public void onResumeUpdatesFields() {
        presenter.onResume();
        assertCorrectlyUpdatesFields(view, team);
    }

    @Test
    public void onActivityResultUpdatesFieldsOnSuccess() {
        Bundle extras = mock(Bundle.class);
        when(extras.getParcelable(TEAM_KEY)).thenReturn(team);
        presenter.onActivityResult(ViewUserPresenter.UPDATE, Activity.RESULT_OK, extras);
        assertCorrectlyUpdatesFields(view, team);
    }

    private void assertCorrectlyUpdatesFields(ViewTeamView view, Team team) {
        verify(view).setDateCreated(FormatUtils.format(team.getDateCreated()));
        verify(view).setDescription(team.getDescription());
        verify(view).setTeamName(team.getName());
    }
}
