package com.raik383h_group_6.healthtracmobile.view;

import android.widget.ListAdapter;

import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

public interface ListUsersView {

    void setUsers(List<User> users);

    void setNoUsersMessageDisplay(boolean enabled);
}
