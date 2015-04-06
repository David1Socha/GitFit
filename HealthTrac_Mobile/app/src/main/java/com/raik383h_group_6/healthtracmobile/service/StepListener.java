package com.raik383h_group_6.healthtracmobile.service;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class StepListener implements SensorEventListener{
    private float lastValue;
    private float lastDir;
    private float minExtreme;
    private float maxExtreme;
    private float lastDif;
    private int lastMatch = -1;
    private Handler handler;

    public StepListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        synchronized (this) {
            if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float sum = 0;
                for(int i = 0; i < 3; i++) {
                    float temp = 480*.5f + event.values[i] * (-480*.5f * (1.0f / SensorManager.MAGNETIC_FIELD_EARTH_MAX));
                    sum += temp;
                }
                float av = sum/3;
                float dir = Float.compare(av, lastValue);
                if(Math.signum(dir) != Math.signum(lastDir)) {
                    int ext;
                    if(dir > 0) { ext=0; minExtreme = lastValue; }
                    else { ext=1; maxExtreme = lastValue; }

                    float dif = Math.abs(maxExtreme - minExtreme);

                    if(dif > 10) {
                        boolean isLarge = dif > lastDif*2/3;
                        boolean isLastLarge = lastDif > dif/3;
                        boolean isValid = lastMatch != 1-ext;

                        if(isLarge && isLastLarge && isValid) {
                            Message m = Message.obtain();
                            m.what = 1;
                            handler.sendMessage(m);
                            lastMatch = ext;
                        } else {
                            lastMatch = -1;
                        }
                    }
                    lastDif = dif;
                }
                lastDir = dir;
                lastValue = av;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
