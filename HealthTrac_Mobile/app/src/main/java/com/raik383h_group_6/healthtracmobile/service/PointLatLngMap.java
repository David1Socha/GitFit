package com.raik383h_group_6.healthtracmobile.service;

import com.google.android.gms.maps.model.LatLng;
import com.raik383h_group_6.healthtracmobile.model.Point;

public final class PointLatLngMap {

    private PointLatLngMap() {
        //unused
    }

    public static LatLng fromPoint(Point p) {
        return new LatLng(p.getLat(), p.getLng());
    }
}
