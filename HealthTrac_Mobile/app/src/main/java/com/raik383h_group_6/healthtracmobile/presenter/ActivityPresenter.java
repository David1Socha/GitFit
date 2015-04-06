package com.raik383h_group_6.healthtracmobile.presenter;


import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.Point;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;

import java.util.Date;
import java.util.List;

public class ActivityPresenter extends BasePresenter{
    private final IActivityNavigator nav;
    private final ActivityView view;
    private int steps;
    private Date startDate, endDate;
    private double distance;
    private List<Point> pts;
    private GoogleApiClient gClient;
    private Location lastLocation;

    @Inject
    public ActivityPresenter(@Assisted IActivityNavigator nav, @Assisted GoogleApiClient gClient, @Assisted ActivityView view) {
        this.nav = nav;
        this.view = view;
        this.steps = 0;
        this.gClient = gClient;
    }

    public void onCreate() {
        gClient.connect();
        startDate = new Date();
    }

    public void onPause() {
        view.stopLocationUpdates();
        endDate = new Date();
        view.showMessage(String.valueOf(endDate.getTime() - startDate.getTime()));
    }

    public void onConnected(Location loc) {
        lastLocation = loc;
        view.startLocationUpdates();
    }

    public void onConnectionSuspended(int cause) {
        view.showMessage("Connection suspended");
        gClient.connect();
    }

    public void onConnectionFailed() {
        view.showMessage("Connection failed");
    }

    public void onLocationChanged(Location location) {
        if (lastLocation != null) {
            distance += location.distanceTo(lastLocation);
            pts.add(new Point(location.getLongitude(), location.getLatitude()));
            view.showMessage(String.valueOf(distance));
        }
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
