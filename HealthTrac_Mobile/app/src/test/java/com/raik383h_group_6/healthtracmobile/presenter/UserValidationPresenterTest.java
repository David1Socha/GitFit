package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import org.junit.Before;
import org.junit.Test;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.EMPTY_FIELD_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FEMALE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_DATE_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        resources = ModelGenerator.genStubbedResources();
        userService = mock(IAsyncUserService.class);
        presenter = new UserValidationPresenter(userService, view, resources);
    }

    @Test
    public void validateUserReturnsUserToCreateWhenValid() {
        String sexStr = getSexStr(validUser);
        User userToCreate = presenter.validateUser(FormatUtils.format(validUser.getBirthDate()), validUser.getEmail(), validUser.getFirstName(), FormatUtils.format(validUser.getHeight()), validUser.getLastName(), validUser.getLocation(), validUser.getPreferredName(), sexStr, validUser.getUserName(), FormatUtils.format(validUser.getWeight()), validUser.getUserName());
        assertSameSignificantValues(userToCreate, validUser);
    }

    @Test
    public void validateUserReturnsNullWhenInvalid() {
        String sexStr = getSexStr(validUser);
        User userToCreate = presenter.validateUser(FormatUtils.format(validUser.getBirthDate()), validUser.getEmail(), validUser.getFirstName(), FormatUtils.format(validUser.getHeight()), validUser.getLastName(), "", validUser.getPreferredName(), sexStr, validUser.getUserName(), FormatUtils.format(validUser.getWeight()), validUser.getUserName());
        assertNull(userToCreate);
    }

    @Test
    public void validateUserFailsWhenUserNameUnavailable() throws Exception {
        when(userService.isAvailable(validUser.getUserName())).thenReturn(false);
        String sexStr = getSexStr(validUser);
        User userToCreate = presenter.validateUser(FormatUtils.format(validUser.getBirthDate()), validUser.getEmail(), validUser.getFirstName(), FormatUtils.format(validUser.getHeight()), validUser.getLastName(), "", validUser.getPreferredName(), sexStr, validUser.getUserName(), FormatUtils.format(validUser.getWeight()), null);
        assertNull(userToCreate);
    }

    @Test
    public void validateUserSetsErrorMessagesWhenErrors() {
        String birthDateStr = "123/234/19";
        String email = "";
        String firstName = "";
        String heightStr = "";
        String lastName = "";
        String location = "";
        String prefName = "";
        String userName = "";
        String sexStr = "Male";
        String widthStr = "";
        presenter.validateUser(birthDateStr, email, firstName, heightStr, lastName, location, prefName, sexStr, userName, widthStr, null);
        verify(view).setBirthDateError(INVALID_DATE_ERROR);
        verify(view).setEmailError(EMPTY_FIELD_ERROR);
        verify(view).setFirstNameError(EMPTY_FIELD_ERROR);
        verify(view).setHeightError(EMPTY_FIELD_ERROR);
        verify(view).setLastNameError(EMPTY_FIELD_ERROR);
        verify(view).setLocationError(EMPTY_FIELD_ERROR);
        verify(view).setPrefNameError(EMPTY_FIELD_ERROR);
        verify(view).setUsernameError(EMPTY_FIELD_ERROR);
        verify(view).setWeightError(EMPTY_FIELD_ERROR);
    }

    private String getSexStr(User u) {
        return u.getSex() == User.Sex.MALE ? MALE : FEMALE;
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
        assertEquals(u1.getWeight(), u2.getWeight(), 0);
        assertEquals(u1.getLocation(), u2.getLocation());
    }

}
