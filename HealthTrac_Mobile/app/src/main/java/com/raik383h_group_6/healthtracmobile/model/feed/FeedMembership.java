package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.Team;

import java.util.Date;

public class FeedMembership extends FeedModel {

    private Team team;
    private AccessGrant grant;

    public FeedMembership(String msg, Date date, IActivityNavigator nav, Team team, AccessGrant grant) {
        super(msg, date, nav);
        this.team = team;
        this.grant = grant;
    }

    @Override
    public void onClick() {
        nav.openViewTeam(team, grant);
    }

    @Override
    public Date getDateReal() {
        return date;
    }
}
