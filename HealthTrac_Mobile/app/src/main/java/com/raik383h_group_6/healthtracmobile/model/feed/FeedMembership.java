package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Membership;

import java.util.Date;

public class FeedMembership extends FeedModel {

    private Membership membership;

    public FeedMembership(String msg, Date date, IActivityNavigator nav, Membership membership) {
        super(msg, date, nav);
        this.membership = membership;
    }

    @Override
    public void onClick() {
        //nav.openMembershipDetail(membership);
    }
}
