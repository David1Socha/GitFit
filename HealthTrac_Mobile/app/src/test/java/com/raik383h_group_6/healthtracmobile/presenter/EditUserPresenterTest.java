package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;

import org.junit.Before;
import org.junit.Test;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_FIELD_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATED_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATE_ERR;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EditUserPresenterTest {

    private EditUserView view;
    private EditUserPresenter presenter;
    private IActivityNavigator nav;
    private IAsyncUserService userService;
    private User ogUser;
    private AccessGrant grant;
    private UserValidationPresenter userValidationPresenter;

    @Before
    public void setup() {
        userService = mock(IAsyncUserService.class);
        nav = mock(IActivityNavigator.class);
        view = mock(EditUserView.class);
        TestStubber.stubViewForResources(view);
        userValidationPresenter = mock(UserValidationPresenter.class);

        ogUser = ModelGenerator.genBasicUser();
        grant = ModelGenerator.genBasicGrant();
    }

    @Test
    public void onCreatePopulatesFieldsWhenOriginalUserExists() {
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        when(view.getParcelableExtra(USER_KEY)).thenReturn(ogUser);
        presenter = new EditUserPresenter(userService, userValidationPresenter, nav, view);
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

    private void stubViewGetters() {
        when(view.getBirthDate()).thenReturn(FormatUtils.format(ogUser.getBirthDate()));
        when(view.getEmail()).thenReturn(ogUser.getEmail());
        when(view.getFirstName()).thenReturn(ogUser.getFirstName());
        when(view.getHeight()).thenReturn(FormatUtils.format(ogUser.getHeight()));
        when(view.getLastName()).thenReturn(ogUser.getLastName());
        when(view.getLocation()).thenReturn(ogUser.getLocation());
        when(view.getPreferredName()).thenReturn(ogUser.getPreferredName());
        when(view.getSex()).thenReturn(ogUser.getSex().name());
        when(view.getUsername()).thenReturn(ogUser.getUserName());
        when(view.getWeight()).thenReturn(FormatUtils.format(ogUser.getWeight()));
    }

    @Test
    public void onClickUpdateUserDisplaysErrorWhenUserInvalid() {
        stubViewGetters();
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        presenter = new EditUserPresenter(userService, userValidationPresenter, nav, view);
        presenter.onClickUpdateUser();
        verify(view).displayMessage(INVALID_FIELD_MSG);
    }

    @Test
    public void onClickUpdateUserUpdatesAndFinishesWhenUserValid() throws Exception {
        stubViewGetters();
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        when(view.getParcelableExtra(USER_KEY)).thenReturn(ogUser);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(ogUser);
        presenter = new EditUserPresenter(userService, userValidationPresenter, nav, view);
        presenter.onClickUpdateUser();
        verify(userService).updateUserAsync(ogUser.getId(), ogUser, grant.getAuthHeader());
        verify(view).displayMessage(USER_UPDATED_MSG);
        verify(nav).finishEditUserSuccess(ogUser);
    }

    @Test
    public void onClickUpdateUserFailsAndFinishesWhenServiceFails() throws Exception {
        stubViewGetters();
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        when(view.getParcelableExtra(USER_KEY)).thenReturn(ogUser);
        when(userValidationPresenter.validateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(ogUser);
        presenter = new EditUserPresenter(userService, userValidationPresenter, nav, view);
        doThrow(new Exception("sample exception indicating service failure")).when(userService).updateUserAsync(ogUser.getId(), ogUser, grant.getAuthHeader());
        presenter.onClickUpdateUser();
        verify(view).displayMessage(USER_UPDATE_ERR);
        verify(nav).finishEditUserFailure();
    }
}
