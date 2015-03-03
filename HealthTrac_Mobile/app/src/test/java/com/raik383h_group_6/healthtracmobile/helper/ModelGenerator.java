package com.raik383h_group_6.healthtracmobile.helper;

import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.Date;

public final class ModelGenerator {

    private ModelGenerator() {
        //unused
    }

    public static AccessGrant genBasicGrant() {
        AccessGrant g = new AccessGrant( );
        g.setId("123");
        g.setUserName("david1socha");
        return g;
    }

    public static User genBasicUser() {
        User u = new User(new Date(), new Date(), new Date(), "yahoo@gmail.com", "david", 22, "socha", "omaha", "david socha", User.Sex.MALE, "david1socha", 20);
        u.setId("123");
        return u;
    }

}
