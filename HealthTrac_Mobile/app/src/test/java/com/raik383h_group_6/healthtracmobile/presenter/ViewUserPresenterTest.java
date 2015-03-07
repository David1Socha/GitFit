package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

public class ViewUserPresenterTest {
    private ViewUserPresenter presenter;
    private Bundle bundle;
    private IResources resources;
    private User user;
    private AccessGrant grant;
    private IActivityNavigator nav;
    private ViewUserView view;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        user = ModelGenerator.genBasicUser();
        grant = ModelGenerator.genBasicGrant();
        bundle = mock(Bundle.class);
        view = mock(ViewUserView.class);
        when(bundle.getParcelable(USER_KEY)).thenReturn(user);
        when(bundle.getParcelable(GRANT_KEY)).thenReturn(grant);
        resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_USER)).thenReturn(USER_KEY);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        when(resources.getString(R.string.male_label)).thenReturn(MALE);
        when(resources.getString(R.string.label_female)).thenReturn(FEMALE);
        presenter = new ViewUserPresenter(bundle, resources, nav, view);
    }

    @Test
    public void onClickEditUserCallsNavigator() {

        presenter.onClickEditUser();
        verify(nav).openEditUser(grant, user, ViewUserPresenter.UPDATE);
        Assert.assertEquals(true, true);
    }

    @Test
    public void onResumeUpdatesFields() {
        presenter.onResume();
        assertCorrectlyUpdatesFields(view, user);
    }

    @Test
    public void onActivityResultUpdatesFieldsOnSuccess() {
        Bundle extras = mock(Bundle.class);
        when(extras.getParcelable(USER_KEY)).thenReturn(user);
        presenter.onActivityResult(ViewUserPresenter.UPDATE, Activity.RESULT_OK, extras);
        assertCorrectlyUpdatesFields(view, user);
    }

    private void assertCorrectlyUpdatesFields(ViewUserView view, User user) {
        verify(view).setBirthDate(FormatUtils.format(user.getBirthDate()));
        verify(view).setEmail(user.getEmail());
        verify(view).setFirstName(user.getFirstName());
        verify(view).setHeight(FormatUtils.format(user.getHeight()));
        verify(view).setLastName(user.getLastName());
        verify(view).setLocation(user.getLocation());
        verify(view).setPrefName(user.getPreferredName());
        verify(view).setSex(user.getSex() == User.Sex.MALE ? "Male" : "Female");
        verify(view).setUserName(user.getUserName());
        verify(view).setWeight(FormatUtils.format(user.getWidth()));
    }
}