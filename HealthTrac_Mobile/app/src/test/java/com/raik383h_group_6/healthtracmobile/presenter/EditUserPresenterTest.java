package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;

import org.apache.http.auth.AUTH;
import org.junit.Before;
import org.junit.Test;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EditUserPresenterTest {

    private EditUserView view;
    private EditUserPresenter presenter;
    private IActivityNavigator nav;
    private IAsyncUserService userService;
    private Bundle extras;
    private IResources resources;
    private User ogUser;
    private AccessGrant grant;
    private UserValidationPresenter userValidationPresenter;

    @Before
    public void setup() {
        resources = ModelGenerator.genStubbedResources();
        extras = mock(Bundle.class);
        userService = mock(IAsyncUserService.class);
        nav = mock(IActivityNavigator.class);
        view = mock(EditUserView.class);
        userValidationPresenter = mock(UserValidationPresenter.class);

        ogUser = ModelGenerator.genBasicUser();
        grant = ModelGenerator.genBasicGrant();
    }

    @Test
    public void onCreatePopulatesFieldsWhenOriginalUserExists() {
        when(extras.getParcelable(GRANT_KEY)).thenReturn(grant);
        when(extras.getParcelable(USER_KEY)).thenReturn(ogUser);
        presenter = new EditUserPresenter(userService, userValidationPresenter, extras, resources, nav, view);
        presenter.onCreate();
        verify(view).setBirthDate(FormatUtils.format(ogUser.getBirthDate()));
        verify(view).setEmail(ogUser.getEmail());
        verify(view).setFirstName(ogUser.getFirstName());
        verify(view).setHeight(FormatUtils.format(ogUser.getHeight()));
        verify(view).setLastName(ogUser.getLastName());
        verify(view).setLocation(ogUser.getLocation());
        verify(view).setPrefName(ogUser.getPreferredName());
        verify(view).setSex(ogUser.getSex());
        verify(view).setUsername(ogUser.getUserName());
        verify(view).setWeight(FormatUtils.format(ogUser.getWeight()));
    }
}
