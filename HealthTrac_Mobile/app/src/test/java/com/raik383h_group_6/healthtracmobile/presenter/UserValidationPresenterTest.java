package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.UserValidationView;

import org.junit.Before;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserValidationPresenterTest {

    private UserValidationPresenter presenter;
    private User validUser;
    private UserValidationView view;
    private IResources resources;
    private IAsyncUserService userService;

    @Before
    public void setup() {
        //TODO
        presenter = new UserValidationPresenter(userservice, view, resources);
    }

}
