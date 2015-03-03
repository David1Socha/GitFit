package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ViewUserPresenterTest {
    private ViewUserPresenter presenter;
    private Bundle bundle;
    private IResources resources;
    private User user;
    private AccessGrant grant;
    private IActivityNavigator nav;
    private static final String USER_KEY ="user", GRANT_KEY = "grant";

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        user = new User();
        grant = new AccessGrant();
        bundle = mock(Bundle.class);
        when(bundle.getParcelable(USER_KEY)).thenReturn(user);
        when(bundle.getParcelable(GRANT_KEY)).thenReturn(grant);
        resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_USER)).thenReturn(USER_KEY);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
    }

    @Test
    public void onClickEditUserCallsNavigator() {
        presenter = new ViewUserPresenter(bundle, resources, nav, null);
        presenter.onClickEditUser();
        verify(nav).openEditUser(grant, user, ViewUserPresenter.UPDATE);
        Assert.assertEquals(true, true);
    }
}