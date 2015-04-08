package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;

import java.util.Date;

public class FeedUserBadge extends FeedModel {

    private Badge badge;
    private AccessGrant grant;

    public FeedUserBadge(String msg, Date date, IActivityNavigator nav, Badge badge, AccessGrant grant) {
        super(msg, date, nav);
        this.badge = badge;
        this.grant = grant;
    }

    @Override
    public void onClick() {
        //nav.openViewBadge(badge, grant);
    }
}
