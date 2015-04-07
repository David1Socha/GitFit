package com.raik383h_group_6.healthtracmobile.view;

public interface CreateTeamView extends TeamValidationView {
    void setNameError(String msg);

    void setDescriptionError(String msg);

    void displayMessage(String msg);
}
