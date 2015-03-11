package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncFacebookService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.CreateUserView;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ACCOUNT_NOT_MADE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_FIELD_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SAMPLE_TOKEN;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateUserPresenterTest {

    private IAsyncAccountService accountService;
    private IAsyncUserService userService;
    private IAsyncFacebookService facebookService;
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
        nav = mock(IActivityNavigator.class);
        view = mock(CreateUserView.class);
        TestStubber.stubViewForResources(view);
        user = ModelGenerator.genBasicUser();
        fbUser = ModelGenerator.genFacebookUser();
        userValidationPresenter = mock(UserValidationPresenter.class);
    }

    @Test
    public void onCreatePopulatesUserFromFacebookIfFacebookIsProvider() throws ExecutionException, InterruptedException {
        when(view.getStringExtra(PROVIDER_KEY)).thenReturn(FACEBOOK);
        when(view.getStringExtra(TOKEN_KEY)).thenReturn(SAMPLE_TOKEN);
        when(facebookService.getFacebookUserAsync(SAMPLE_TOKEN)).thenReturn(fbUser);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, nav, view);
        presenter.onCreate();
        verify(view).setEmail(fbUser.getEmail());
        verify(view).setFirstName(fbUser.getFirstName());
        verify(view).setPrefName(fbUser.getName());
        verify(view).setLastName(fbUser.getLastName());
        verify(view).setSex(User.Sex.MALE);
    }

    @Test
    public void onClickCreateAccountDisplaysErrorWhenUserInvalid() {
        TestStubber.stubUserViewGetters(view, user);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, nav, view);
        presenter.onClickCreateAccount();
        verify(view).displayMessage(INVALID_FIELD_MSG);
    }

    @Test
    public void onClickCreateAccountCreatesAccountAndFinishesWhenValid() throws Exception {
        TestStubber.stubUserViewGetters(view, user);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(user);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, nav, view);
        presenter.onClickCreateAccount();
        verify(accountService).registerAccountAsync(any(UserLogin.class));
        verify(nav).finishCreateUserSuccess();
    }

    @Test
    public void onClickCreateAccountFailsAndShowsErrorWhenServiceFails() throws Exception {
        TestStubber.stubUserViewGetters(view, user);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(user);
        presenter = new CreateUserPresenter(facebookService, userService, accountService, userValidationPresenter, nav, view);
        doThrow(new Exception("Sample exception for api fail")).when(accountService).registerAccountAsync(any(UserLogin.class));
        presenter.onClickCreateAccount();
        verify(view).displayMessage(ACCOUNT_NOT_MADE);
        verify(nav).finishCreateUserFailure();
    }
}
