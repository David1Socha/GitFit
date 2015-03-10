package com.raik383h_group_6.healthtracmobile.helper;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.Token;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.*;
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

    public static IResources genStubbedResources() {
        IResources resources = mock(IResources.class);
        when(resources.getString(R.string.EXTRA_ACCESS_GRANT)).thenReturn(GRANT_KEY);
        when(resources.getString(R.string.EXTRA_USER)).thenReturn(USER_KEY);
        when(resources.getString(R.string.invalid_field_message)).thenReturn(INVALID_FIELD_MSG);
        when(resources.getString(R.string.user_updated_message)).thenReturn(USER_UPDATED_MSG);
        when(resources.getString(R.string.update_user_error)).thenReturn(USER_UPDATE_ERR);
        when(resources.getString(R.string.pref_access_grant)).thenReturn(GRANT_PREF_KEY);
        when(resources.getString(R.string.error_find_profile)).thenReturn(ERROR_FIND_PROFILE);
        when(resources.getString(R.string.PROVIDER_FACEBOOK)).thenReturn(FACEBOOK);
        when(resources.getString(R.string.PROVIDER_TWITTER)).thenReturn(TWITTER);
        when(resources.getString(R.string.EXTRA_ACCESS_TOKEN)).thenReturn(TOKEN_KEY);
        when(resources.getString(R.string.EXTRA_ACCESS_SECRET)).thenReturn(SECRET_KEY);
        when(resources.getString(R.string.EXTRA_PROVIDER)).thenReturn(PROVIDER_KEY);
        when(resources.getString(R.string.male_label)).thenReturn(MALE);
        when(resources.getString(R.string.empty_field_error)).thenReturn(EMPTY_FIELD_ERROR);
        when(resources.getString(R.string.invalid_date_error)).thenReturn(INVALID_DATE_ERROR);
        when(resources.getString(R.string.EXTRA_TEAM)).thenReturn(TEAM_KEY);
        when(resources.getString(R.string.label_female)).thenReturn(FEMALE);
        return resources;
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
