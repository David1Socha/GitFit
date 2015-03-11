package com.raik383h_group_6.healthtracmobile.view;

import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

public interface ListUsersView extends BaseView {

    void setUsers(List<User> users);

    void setNoUsersMessageDisplay(boolean enabled);
}
