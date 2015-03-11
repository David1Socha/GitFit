package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.RequestCodes;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewUserPresenterTest {
    private ViewUserPresenter presenter;
    private User user;
    private AccessGrant grant;
    private IActivityNavigator nav;
    private ViewUserView view;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        user = ModelGenerator.genBasicUser();
        grant = ModelGenerator.genBasicGrant();
        view = mock(ViewUserView.class);
        when(view.getParcelableExtra(USER_KEY)).thenReturn(user);
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        TestStubber.stubViewForResources(view);
        presenter = new ViewUserPresenter(nav, view);
    }

    @Test
    public void onClickEditUserCallsNavigator() {

        presenter.onClickEditUser();
        verify(nav).openEditUser(grant, user, RequestCodes.UPDATE_USER);
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
        presenter.onActivityResult(RequestCodes.UPDATE_USER, Activity.RESULT_OK, extras);
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
        verify(view).setWeight(FormatUtils.format(user.getWeight()));
    }
}