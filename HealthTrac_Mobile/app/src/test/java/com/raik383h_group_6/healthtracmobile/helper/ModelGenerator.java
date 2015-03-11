package com.raik383h_group_6.healthtracmobile.helper;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import java.util.Date;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ACCOUNT_NOT_MADE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.EMPTY_FIELD_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.ERROR_FIND_PROFILE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FACEBOOK;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.FEMALE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_PREF_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_DATE_ERROR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.INVALID_FIELD_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.MALE;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.PROVIDER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SECRET_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.SIGNIN_ERR;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TEAM_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TOKEN_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.TWITTER;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_KEY;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATED_MSG;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.USER_UPDATE_ERR;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    public static FacebookUser genFacebookUser() {
        FacebookUser u = new FacebookUser();
        u.setName("John Smith");
        u.setFirstName("John");
        u.setLastName("Smith");
        u.setEmail("john@smith.com");
        u.setGender("male");
        return u;
    }
}
