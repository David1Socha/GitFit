package com.raik383h_group_6.healthtracmobile.view;

public interface ViewUserView  extends BaseView {
    void setBirthDate(String txt);

    void setEmail(String txt);

    void setFirstName(String txt);

    void setHeight(String txt);

    void setLastName(String txt);

    void setLocation(String txt);

    void setPrefName(String txt);

    void setSex(String txt);

    void setUserName(String txt);

    void setWeight(String txt);

    void setShowEditUserButton(boolean enabled);

    void setProfilePicture(String profilePicture);
}
