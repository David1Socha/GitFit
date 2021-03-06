package com.raik383h_group_6.healthtracmobile.view;

import com.raik383h_group_6.healthtracmobile.model.User;

public interface UserValidationView  extends BaseView {
    void setLastName(String val);

    void setFirstName(String val);

    void setPrefName(String val);

    void setBirthDate(String val);

    void setLocation(String val);

    void setEmail(String val);

    void setHeight(String val);

    void setUsername(String val);

    void setWeight(String val);

    String getBirthDate();

    String getEmail();

    String getFirstName();

    String getHeight();

    String getLastName();

    String getLocation();

    String getPreferredName();

    String getSex();

    String getUsername();

    String getWeight();

    void setSex(User.Sex sex);

    void setLocationError(String msg);

    void setBirthDateError(String msg);

    void setEmailError(String msg);

    void setFirstNameError(String msg);

    void setHeightError(String msg);

    void setLastNameError(String msg);

    void setPrefNameError(String msg);

    void setUsernameError(String msg);

    void setWeightError(String msg);
}
