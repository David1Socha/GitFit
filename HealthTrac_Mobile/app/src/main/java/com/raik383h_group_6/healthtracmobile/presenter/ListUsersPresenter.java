package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.service.api.UserService;
import com.raik383h_group_6.healthtracmobile.view.ListUsersActivity;

import java.util.ArrayList;
import java.util.List;

public class ListUsersPresenter {

    private IResources resources;
    private Navigator nav;
    private ListUsersActivity view;
    private UserService userService;

    @Inject
    public ListUsersPresenter(UserService userService, @Assisted IResources resources, @Assisted Navigator nav, @Assisted ListUsersActivity view) {
        this.resources = resources;
        this.nav = nav;
        this.userService = userService;
        this.view = view;
    }

    public void onCreate() {
        User u = new User();
        u.setUserName("david1socha");
        u.setSex(User.Sex.MALE);
        u.setPreferredName("Dave Socha");
        u.setLocation("Omaha, NE");
        List<User> users = new ArrayList<User>();
        users.add(u);
        view.setUserListView(users);
    }
}
