package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
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
    private User user;

    @Before
    public void setup() {
        accountService = mock(IAsyncAccountService.class);
        userService = mock(IAsyncUserService.class);
        facebookService = mock(IAsyncFacebookService.class);
        extras = mock(Bundle.class);
        resources = ModelGenerator.genStubbedResources();
        nav = mock(IActivityNavigator.class);
        view = mock(CreateUserView.class);
        user = ModelGenerator.genBasicUser();
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

    private void stubViewGetters() {
        when(view.getBirthDate()).thenReturn(FormatUtils.format(user.getBirthDate()));
        when(view.getEmail()).thenReturn(user.getEmail());
        when(view.getFirstName()).thenReturn(user.getFirstName());
        when(view.getHeight()).thenReturn(FormatUtils.format(user.getHeight()));
        when(view.getLastName()).thenReturn(user.getLastName());
        when(view.getLocation()).thenReturn(user.getLocation());
        when(view.getPreferredName()).thenReturn(user.getPreferredName());
        when(view.getSex()).thenReturn(user.getSex().name());
        when(view.getUsername()).thenReturn(user.getUserName());
        when(view.getWeight()).thenReturn(FormatUtils.format(user.getWeight()));
    }

    @Test
    public void onClickCreateAccountDisplaysErrorWhenUserInvalid() {
        stubViewGetters();
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, extras, resources, nav, view);
        presenter.onClickCreateAccount();
        verify(view).displayMessage(INVALID_FIELD_MSG);
    }

    @Test
    public void onClickCreateAccountCreatesAccountAndFinishesWhenValid() throws Exception {
        stubViewGetters();
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(user);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, extras, resources, nav, view);
        presenter.onClickCreateAccount();
        verify(accountService).registerAccountAsync(any(UserLogin.class));
        verify(nav).finishCreateUserSuccess();
    }

    @Test
    public void onClickCreateAccountFailsAndShowsErrorWhenServiceFails() throws Exception {
        stubViewGetters();
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(user);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, extras, resources, nav, view);
        doThrow(new Exception("Sample exception for api fail")).when(accountService).registerAccountAsync(any(UserLogin.class));
        presenter.onClickCreateAccount();
        verify(view).displayMessage(ACCOUNT_NOT_MADE);
        verify(nav).finishCreateUserFailure();
    }
}
