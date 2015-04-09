package com.raik383h_group_6.healthtracmobile.view;

import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

/**
 * Created by Aaron on 4/8/2015.
 */
public interface TeamLeaderboardView extends BaseView{

    void setUsers(List<User> users);

    void setNoUsersMessageDisplay(boolean enabled);
}
