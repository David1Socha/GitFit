package com.raik383h_group_6.healthtracmobile.view;

public interface CreateActivityView extends BaseView {
    String getType();

    String getDuration();

    String getDistance();

    String getSteps();

    void displayMessage(String msg);

    void setDurationErr(String e);

    void setDistanceErr(String e);

    void setStepErr(String e);
}
