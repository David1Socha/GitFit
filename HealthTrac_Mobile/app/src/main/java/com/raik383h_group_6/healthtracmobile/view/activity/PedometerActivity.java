package com.raik383h_group_6.healthtracmobile.view.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PedometerPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.StepListener;
import com.raik383h_group_6.healthtracmobile.view.PedometerView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import com.google.inject.Inject;

@ContentView(R.layout.activity_pedometer)
public class PedometerActivity extends BaseActivity implements PedometerView {
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

    @InjectView(R.id.pedometer_textview)
    TextView pedometer;
    @InjectView(R.id.stepcount_textview)
    TextView stepcount;
    @InjectView(R.id.steps_textview)
    TextView steps;

    @Inject
    PresenterFactory presenterFactory;
    private PedometerPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
        stepListener = new StepListener(handler);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(stepListener,sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void onClickStepReset(View view) { presenter.resetSteps(); }

    @Override
    public void setPedometer(String txt) {  pedometer.setText(txt); }

    @Override
    public void setStepCount(String txt) {  stepcount.setText(txt); }

    @Override
    public void setSteps(String txt) {  steps.setText(txt);}

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

}
