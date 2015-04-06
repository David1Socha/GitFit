package com.raik383h_group_6.healthtracmobile.presenter;


import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;

public class ActivityPresenter extends BasePresenter{
    private final IActivityNavigator nav;
    private final ActivityView view;
    private int steps;
    private GoogleApiClient gClient;
    private Location lastLocation;

    @Inject
    public ActivityPresenter(@Assisted IActivityNavigator nav, @Assisted GoogleApiClient gClient, @Assisted ActivityView view) {
        this.nav = nav;
        this.view = view;
        this.steps = 0;
        this.gClient = gClient;
    }

    public void onConnected(Location loc) {
        lastLocation = loc;
        view.startLocationUpdates();
    }

    public void onConnectionSuspended(int cause) {
        view.showMessage("Connection suspended");
    }

    public void onConnectionFailed() {
        view.showMessage("Connection failed");
    }

    public void onLocationChanged(Location location) {
        view.showMessage(String.valueOf(location.distanceTo(lastLocation)));
        lastLocation = location;
    }

    public void onStep() {
        steps++;
        view.setStepCount(Integer.toString(steps));
    }

    public void resetSteps() {
        steps = 0;
        view.setStepCount(Integer.toString(steps));
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
