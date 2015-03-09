package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.EditUserView;

import org.apache.http.auth.AUTH;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setup() {
        resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        when(resources.getString(R.string.EXTRA_USER)).thenReturn(USER_KEY);
        when(resources.getString(R.string.invalid_field_message)).thenReturn(INVALID_FIELD_MSG);
        when(resources.getString(R.string.user_updated_message)).thenReturn(USER_UPDATED_MSG);
        when(resources.getString(R.string.update_user_error)).thenReturn(USER_UPDATE_ERR);
        extras = mock(Bundle.class);
        userService = mock(IAsyncUserService.class);
        nav = mock(IActivityNavigator.class);
        view = mock(EditUserView.class);
    }
}
