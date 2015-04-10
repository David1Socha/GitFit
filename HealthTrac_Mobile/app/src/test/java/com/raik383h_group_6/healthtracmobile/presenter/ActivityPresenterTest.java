package com.raik383h_group_6.healthtracmobile.presenter;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.helper.TestStubber;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Point;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncAccountService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncPointService;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;
import com.raik383h_group_6.healthtracmobile.view.AuthenticationView;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        TestStubber.stubViewForResources(view);
        when(view.getParcelableExtra(GRANT_KEY)).thenReturn(grant);
        gClient = mock(GoogleApiClient.class);
        asvc = mock(IAsyncActivityService.class);
        psvc = mock(IAsyncPointService.class);
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

    @Test
    public void onConnectionSuspendedReconnects() {
        presenter.onConnectionSuspended(0);
        verify(gClient).connect();
    }

    @Test
    public void onLocationChangedAddsPoint() {
        Location loc = ModelGenerator.genBasicLocation();
        presenter.onConnected(loc);
        presenter.onLocationChanged(loc);
        Point addedPt = presenter.getPts().get(0);
        Assert.assertEquals(addedPt.getLat(), loc.getLatitude(), 0.000001);
        Assert.assertEquals(addedPt.getLng(), loc.getLongitude(), 0.000001);
    }

    @Test
    public void onClickFinishActivityPostsActivity() throws ExecutionException, InterruptedException {
        presenter.onCreate();
        presenter.onClickFinishActivity();
        verify(asvc).createActivityAsync(any(Activity.class), anyString());
    }

    @Test
    public void onClickFinishActivityPostsPtsWhenPts() throws ExecutionException, InterruptedException {
        presenter.onCreate();
        List<Point> pts = new ArrayList<Point>();
        pts.add(ModelGenerator.genBasicPt());
        presenter.setPts(pts);
        when(asvc.createActivityAsync(any(Activity.class), anyString())).thenReturn(ModelGenerator.genBasicActivity());
        presenter.onClickFinishActivity();
        verify(psvc).createPoints(pts, grant.getAuthHeader());
    }
}
