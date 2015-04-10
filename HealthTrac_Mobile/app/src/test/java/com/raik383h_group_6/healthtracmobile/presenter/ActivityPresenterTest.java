package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncPointService;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ActivityPresenterTest {
    private IActivityNavigator nav;
    private AccessGrant grant;
    private ActivityView view;
    private GoogleApiClient gClient;
    private IAsyncActivityService asvc;
    private IAsyncPointService psvc;
    private ActivityPresenter presenter;

    @Before
    public void setup() {
        nav = mock(IActivityNavigator.class);
        grant = ModelGenerator.genBasicGrant();
        view = mock(ActivityView.class);
        gClient = mock(GoogleApiClient.class);
        asvc = mock(IAsyncActivityService.class);
        psvc = mock(IAsyncPointService.class);
        TestStubber.stubViewForResources(view);
        presenter = new ActivityPresenter(nav, gClient, view, asvc, psvc);
    }

    @Test
    public void onCreateConnectsGoogleClient() {
        presenter.onCreate();
        verify(gClient).connect();
    }

    @Test
    public void onPauseStopsLocationUpdates() {
        presenter.onPause();
        verify(view).stopLocationUpdates();
    }

    @Test
    public void onConnectedStartsUpdates() {
        presenter.onConnected(null);
        verify(view).startLocationUpdates();
    }
}
