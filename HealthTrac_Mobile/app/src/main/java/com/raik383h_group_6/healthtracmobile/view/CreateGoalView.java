package com.raik383h_group_6.healthtracmobile.view;

import android.view.View;

public interface CreateGoalView {

    String getName();

    String getThreshold();

    String getField();

    void setNameError(String msg);

    void displayMessage(String msg);
}
