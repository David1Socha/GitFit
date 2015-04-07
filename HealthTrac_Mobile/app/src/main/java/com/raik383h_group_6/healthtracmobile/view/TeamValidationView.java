package com.raik383h_group_6.healthtracmobile.view;


public interface TeamValidationView extends BaseView {
    void setName(String val);

    void setDescription(String val);

    String getName();

    String getDescription();

    void setNameError(String msg);

    void setDescriptionError(String msg);
}
