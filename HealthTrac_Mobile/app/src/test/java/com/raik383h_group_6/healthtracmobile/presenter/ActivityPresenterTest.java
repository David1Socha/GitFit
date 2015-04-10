package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;

import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ActivityPresenterTest {
    private IActivityNavigator nav;
    private AccessGrant grant;
    private ActivityView view;
    private GoogleApiClient gClient;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
    }
}
