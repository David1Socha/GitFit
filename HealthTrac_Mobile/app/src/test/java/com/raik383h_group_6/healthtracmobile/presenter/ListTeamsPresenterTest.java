package com.raik383h_group_6.healthtracmobile.presenter;


import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListTeamsPresenterTest {

    private ListTeamsPresenter presenter;
    private IAsyncTeamService teamService;
    private Bundle extras;
    private IResources resources;
    private IActivityNavigator nav;
    private ListTeamsView view;
    private Team team;
    private List<Team> teams;
    private AccessGrant grant;

    @Before
    public void setup() {
        grant = ModelGenerator.genBasicGrant();
        team = ModelGenerator.genBasicTeam();
        teams = new ArrayList<Team>();
        teams.add(team);

        teamService = mock(IAsyncTeamService.class);
        extras = mock(Bundle.class);
        resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        nav = mock(IActivityNavigator.class);
        view = mock(ListTeamsView.class);
    }

    @Test
    public void onItemClickOpensViewTeamWithTeam() {
        when(extras.getParcelable(GRANT_KEY)).thenReturn(grant);
        presenter = new ListTeamsPresenter(teamService, extras, resources, nav, view);
        AdapterView<?> parent = mock(AdapterView.class);
        Adapter adapter = mock(Adapter.class);
        when(parent.getAdapter()).thenReturn(adapter);
        when(adapter.getItem(0)).thenReturn(team);
        presenter.onItemClick(parent, null, 0, 0);
        verify(nav).openViewTeam(team, grant);
    }


}
