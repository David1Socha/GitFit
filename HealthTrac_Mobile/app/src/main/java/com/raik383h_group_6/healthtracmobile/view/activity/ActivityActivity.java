package com.raik383h_group_6.healthtracmobile.view.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.ActivityPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.StepListener;
import com.raik383h_group_6.healthtracmobile.view.ActivityView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import com.google.inject.Inject;

@ContentView(R.layout.activity_pedometer)
public class ActivityActivity extends BaseActivity implements ActivityView, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener{
    private Sensor sensor;
    private SensorManager sensorManager;
    private StepListener stepListener;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if(message.what == 1) {
                presenter.onStep();
            }
        }
    };
    private GoogleApiClient gClient;

    @InjectView(R.id.stepcount_textview)
    TextView stepcount;
    @InjectView(R.id.steps_textview)
    TextView steps;
    @Inject
    LocationRequest
    locRequest;

    @Inject
    PresenterFactory presenterFactory;
    private ActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        gClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        presenter = presenterFactory.create(nav, gClient, this);
        stepListener = new StepListener(handler);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(stepListener,sensor, SensorManager.SENSOR_DELAY_FASTEST);
        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
        sensorManager.unregisterListener(stepListener);
    }

    public void onClickStepReset(View view) { presenter.resetSteps(); }

    @Override
    public void setStepCount(String txt) {  stepcount.setText(txt); }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location loc = LocationServices.FusedLocationApi.getLastLocation(gClient);
        presenter.onConnected(loc);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                gClient, locRequest, this);
    }

    @Override
    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(gClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        presenter.onConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        presenter.onConnectionFailed();
    }

    @Override
    public void onLocationChanged(Location location) {
        presenter.onLocationChanged(location);
    }
}
