package com.raik383h_group_6.healthtracmobile.view;


public interface ActivityView extends BaseView{

    void setStepCount(String txt);

    void showMessage(String msg);

    void startLocationUpdates();

    void stopLocationUpdates();
}
