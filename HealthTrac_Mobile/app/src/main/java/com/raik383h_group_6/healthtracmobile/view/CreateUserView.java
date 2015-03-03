package com.raik383h_group_6.healthtracmobile.view;

public interface CreateUserView extends InputUserView{
    void setLocationError(String msg);

    void setBirthDateError(String msg);

    void setEmailError(String msg);

    void setFirstNameError(String msg);

    void setHeightError(String msg);

    void setLastNameError(String msg);

    void setPrefNameError(String msg);

    void setUsernameError(String msg);

    void setWeightError(String msg);

    void displayMessage(String msg);
}
