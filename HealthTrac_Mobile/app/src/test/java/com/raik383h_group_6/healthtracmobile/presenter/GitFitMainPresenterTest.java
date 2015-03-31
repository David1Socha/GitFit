package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.service.json.JsonParser;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ERROR_FIND_PROFILE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FAKE_JSON;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_PREF_KEY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitFitMainPresenterTest {

    private GitFitMainView view;
    private GitFitMainPresenter presenter;
    private IAsyncUserService userService;
    private IActivityNavigator nav;
    private JsonParser json;
    private AccessGrant grant;
    private User user;

    @Before
    public void setup() {
        grant = ModelGenerator.genBasicGrant();
        user = ModelGenerator.genBasicUser();

        view = mock(GitFitMainView.class);
        TestStubber.stubViewForResources(view);
        userService = mock(IAsyncUserService.class);
        nav = mock(IActivityNavigator.class);
        json = mock(JsonParser.class);
        presenter = new GitFitMainPresenter(userService, json, nav, view);
    }

    @Test
    public void onResumeLoadsGrantFromSharedPrefs() {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        assertEquals(grant, presenter.getGrant());
    }

    @Test
    public void onPauseSavesGrantAsSharedPref() {
        when(json.toJson(null)).thenReturn(FAKE_JSON);
        presenter.onPause();
        verify(view).setPref(GRANT_PREF_KEY, FAKE_JSON);
    }

    @Test
    public void onClickShowUsersOpensAuthenticationWhenGrantBad() {
        presenter.onClickShowUsers();
        verify(nav).openAuthentication(RequestCodes.AUTH);
    }

    @Test
    public void onClickShowUsersOpensListUsersWhenGrant() {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        presenter.onClickShowUsers();
        verify(nav).openListUsers(grant);
    }

    @Test
    public void onClickViewUserOpensViewUserWhenServiceWorking() throws ExecutionException, InterruptedException {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        when(userService.getUserAsync(grant.getId(), grant.getAuthHeader())).thenReturn(user);
        presenter.onClickShowProfile();
        verify(nav).openViewUser(user, grant);
    }

    @Test
    public void onClickViewUserDisplaysErrorWhenNoUserFound() throws ExecutionException, InterruptedException {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        when(userService.getUserAsync(grant.getId(), grant.getAuthHeader())).thenReturn(null);
        presenter.onClickShowProfile();
        verify(view).displayMessage(ERROR_FIND_PROFILE);
    }

    @Test
    public void onClickViewTeamsOpensListTeamsWhenGrant() {
        when(view.getPref(GRANT_PREF_KEY)).thenReturn(FAKE_JSON);
        when(json.fromJson(FAKE_JSON, AccessGrant.class)).thenReturn(grant);
        presenter.onResume();
        presenter.onClickShowTeams();
        verify(nav).openListTeams(grant);
    }

    @Test
    public void onClickShowTeamsOpensAuthenticationWhenGrantBad() {
        presenter.onClickShowTeams();
        verify(nav).openAuthentication(RequestCodes.AUTH);
    }

    @Test
    public void onActivityResultSavesGrantWhenFromAuthSuccess() {
        Bundle data = mock(Bundle.class);
        when(data.getParcelable(GRANT_KEY)).thenReturn(grant);
        presenter.onActivityResult(RequestCodes.AUTH, Activity.RESULT_OK, data);
        assertEquals(grant, presenter.getGrant());
    }

}
