package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

public class UserValidationPresenterTest {

    private UserValidationPresenter presenter;
    private User validUser;
    private UserValidationView view;
    private IResources resources;
    private IAsyncUserService userService;

    @Before
    public void setup() {
        validUser = ModelGenerator.genBasicUser();
        view = mock(UserValidationView.class);
        resources = mock(IResources.class);
        when(resources.getString(R.string.male_label)).thenReturn(MALE);
        userService = mock(IAsyncUserService.class);
        presenter = new UserValidationPresenter(userService, view, resources);
    }

    @Test
    public void validateUserReturnsUserToCreateWhenValid() {
        String sexStr = validUser.getSex() == User.Sex.MALE ? MALE : FEMALE;
        User userToCreate = presenter.validateUser(FormatUtils.format(validUser.getBirthDate()), validUser.getEmail(), validUser.getFirstName(), FormatUtils.format(validUser.getHeight()), validUser.getLastName(), validUser.getLocation(), validUser.getPreferredName(), sexStr, validUser.getUserName(), FormatUtils.format(validUser.getWidth()));
        assertSameSignificantValues(userToCreate, validUser);
    }

    private void assertSameSignificantValues(User u1, User u2) {
        assertEquals(FormatUtils.format(u1.getBirthDate()), FormatUtils.format(u2.getBirthDate())); //Not doing compare exact because of earlier loss of info when transferring date as mm/dd/yyyy string
        assertEquals(u1.getEmail(), u2.getEmail());
        assertEquals(u1.getFirstName(), u2.getFirstName());
        assertEquals(u1.getHeight(), u2.getHeight(), 0);
        assertEquals(u1.getLastName(), u2.getLastName());
        assertEquals(u1.getLocation(), u2.getLocation());
        assertEquals(u1.getPreferredName(), u2.getPreferredName());
        assertEquals(u1.getSex(), u2.getSex());
        assertEquals(u1.getUserName(), u2.getUserName());
        assertEquals(u1.getWidth(), u2.getWidth(), 0);
        //TODO assertEquals(u1.getLocation(), u2.getLocation());
    }

}
