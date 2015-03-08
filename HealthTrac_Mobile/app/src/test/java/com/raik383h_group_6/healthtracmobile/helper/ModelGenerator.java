package com.raik383h_group_6.healthtracmobile.helper;

import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.ArrayList;
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

    public static Team genBasicTeam() {
        Team t = new Team();
        t.setDescription("Boring team");
        t.setDateCreated(new Date());
        t.setId(12);
        t.setName("teeeeeeem");
        return t;
    }


    public static Membership genMemberMembership() {
        Membership m = new Membership();
        m.setId(0);
        m.setTeamID(12);
        m.setUserID("123");
        m.setMembershipStatus(Membership.MembershipStatus.MEMBER);
        return m;
    }

    public static Token genRequestToken() {
        Token t = new Token();
        t.setSecret("secret");
        t.setToken("qwerty");
        return t;
    }
}
