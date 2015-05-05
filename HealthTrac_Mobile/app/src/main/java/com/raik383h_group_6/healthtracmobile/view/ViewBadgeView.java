package com.raik383h_group_6.healthtracmobile.view;

import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

public interface ViewBadgeView extends BaseView{
    void setBadgeName(String s);
    void setField(String s);
    void setThreshold(String s);
    void setBadgeHolders(List<User> users);
}
