package com.raik383h_group_6.healthtracmobile.view.activity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.raik383h_group_6.healthtracmobile.*;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Point;
import com.raik383h_group_6.healthtracmobile.service.PointLatLngMap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPathActivity extends ActionBarActivity implements OnMapReadyCallback {

    private List<LatLng> latLngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity); // Not using presenter here as almost all logic is tied to android ecosystem / google maps api

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        List<Point> pts = getIntent().getParcelableArrayListExtra(getString(R.string.EXTRA_POINTS));
        latLngs = new ArrayList<>();
        for (Point p : pts) {
            latLngs.add(PointLatLngMap.fromPoint(p));
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng l : latLngs) {
            builder.include(l);
        }
        LatLngBounds bounds = builder.build();
        map.setMyLocationEnabled(true);
        map.addPolyline(buildOptions()).setColor(Color.CYAN);
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, 10);
        map.moveCamera(update);
    }

    private PolylineOptions buildOptions() {
        PolylineOptions options =new PolylineOptions().geodesic(true);
        for (LatLng l : latLngs) {
            options.add(l);
        }
        return options;
    }
}