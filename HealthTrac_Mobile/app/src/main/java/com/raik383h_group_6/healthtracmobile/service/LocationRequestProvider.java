package com.raik383h_group_6.healthtracmobile.service;

import com.google.android.gms.location.LocationRequest;
import com.google.inject.Provider;

public class LocationRequestProvider implements Provider<LocationRequest> {
    public static final long UPDATE_INTERVAL = 5000;
    public static final long MAX_UPDATE_INTERVAL =
            UPDATE_INTERVAL;

    @Override
    public LocationRequest get() {
        LocationRequest lr = new LocationRequest();
        lr.setInterval(UPDATE_INTERVAL);
        lr.setFastestInterval(MAX_UPDATE_INTERVAL);
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return lr;
    }
}
