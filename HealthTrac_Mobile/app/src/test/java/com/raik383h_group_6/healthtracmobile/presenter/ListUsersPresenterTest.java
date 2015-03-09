package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

public class ListUsersPresenterTest {

    private ListUsersPresenter presenter;
    private ListUsersView view;
    private IResources resources;
    private IActivityNavigator nav;
    private Bundle extras;
    private IAsyncUserService userService;

    @Before
    public void setup() {

    }
}
