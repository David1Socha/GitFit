package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.json.JsonParser;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitFitMainPresenterTest {

    private GitFitMainView view;
    private GitFitMainPresenter presenter;
    private IAsyncUserService userService;
    private IResources resources;
    private IActivityNavigator nav;
    private JsonParser json;
    private AccessGrant grant;
    private User user;

    @Before
    public void setup() {
        grant = ModelGenerator.genBasicGrant();
        user = ModelGenerator.genBasicUser();

        view = mock(GitFitMainView.class);
        userService = mock(IAsyncUserService.class);
        resources = mock(IResources.class);
        when(resources.getString(R.string.pref_access_grant)).thenReturn(GRANT_PREF_KEY);
        nav = mock(IActivityNavigator.class);
        json = mock(JsonParser.class);
        presenter = new GitFitMainPresenter(userService, json, resources, nav, view);
    }

    @Test
    public void onResumeLoadsGrantFromSharedPrefs() {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        assertEquals(grant, presenter.getGrant());
    }
}
