package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListUsersPresenterTest {

    private ListUsersPresenter presenter;
    private ListUsersView view;
    private IActivityNavigator nav;
    private IAsyncUserService userService;
    private User user;
    private AccessGrant grant;
    private List<User> users;

    @Before
    public void setup() {
        user = ModelGenerator.genBasicUser();
        users = new ArrayList<User>();
        users.add(user);
        view = mock(ListUsersView.class);
        TestStubber.stubViewForResources(view);
        grant = ModelGenerator.genBasicGrant();
        nav = mock(IActivityNavigator.class);
        userService = mock(IAsyncUserService.class);
    }

    @Test
    public void onItemClickOpensViewUserWithCorrectInfo() {
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        presenter = new ListUsersPresenter(userService, nav, view);
        AdapterView<?> parent = mock(AdapterView.class);
        Adapter adapter = mock(Adapter.class);
        when(parent.getAdapter()).thenReturn(adapter);
        when(adapter.getItem(0)).thenReturn(user);
        presenter.onItemClick(parent, null, 0, 0);
        verify(nav).openViewUser(user, grant);
    }

    @Test
    public void onResumeShowsNoUsersMessageWhenNoUsersFound() throws ExecutionException, InterruptedException {
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        presenter = new ListUsersPresenter(userService, nav, view);
        when(userService.getUsersAsync(grant.getAuthHeader())).thenReturn(null);
        presenter.onResume();
        verify(view).setNoUsersMessageDisplay(true);
    }

    @Test
    public void onResumePopulatesUsersWhenGrantExistsAndCallSucceeds() throws ExecutionException, InterruptedException {
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        presenter = new ListUsersPresenter(userService, nav, view);
        when(userService.getUsersAsync(grant.getAuthHeader())).thenReturn(users);
        presenter.onResume();
        verify(view).setUsers(users);
        verify(view).setNoUsersMessageDisplay(false);
    }

    @Test
    public void onResumeShowsNoUsersMessageWhenNoAccessGrant() throws ExecutionException, InterruptedException {
        presenter = new ListUsersPresenter(userService, nav, view);
        presenter.onResume();
        verify(view).setNoUsersMessageDisplay(true);
    }
}
