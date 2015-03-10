package com.raik383h_group_6.healthtracmobile.presenter;

import android.graphics.AvoidXfermode;
import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
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
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CreateUserPresenterTest {

    private IAsyncAccountService accountService;
    private IAsyncUserService userService;
    private IAsyncFacebookService facebookService;
    private Bundle extras;
    private IResources resources;
    private IActivityNavigator nav;
    private CreateUserView view;
    private CreateUserPresenter presenter;
    private FacebookUser fbUser;
    private UserValidationPresenter userValidationPresenter;

    @Before
    public void setup() {
        accountService = mock(IAsyncAccountService.class);
        userService = mock(IAsyncUserService.class);
        facebookService = mock(IAsyncFacebookService.class);
        extras = mock(Bundle.class);
        resources = ModelGenerator.genStubbedResources();
        nav = mock(IActivityNavigator.class);
        view = mock(CreateUserView.class);
        fbUser = ModelGenerator.genFacebookUser();
        userValidationPresenter = mock(UserValidationPresenter.class);
    }

    @Test
    public void onCreatePopulatesUserFromFacebookIfFacebookIsProvider() throws ExecutionException, InterruptedException {
        when(extras.getString(PROVIDER_KEY)).thenReturn(FACEBOOK);
        when(extras.getString(TOKEN_KEY)).thenReturn(SAMPLE_TOKEN);
        when(facebookService.getFacebookUserAsync(SAMPLE_TOKEN)).thenReturn(fbUser);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, extras, resources, nav, view);
        presenter.onCreate();
        verify(view).setEmail(fbUser.getEmail());
        verify(view).setFirstName(fbUser.getFirstName());
        verify(view).setPrefName(fbUser.getName());
        verify(view).setLastName(fbUser.getLastName());
        verify(view).setSex(User.Sex.MALE);
    }
}
