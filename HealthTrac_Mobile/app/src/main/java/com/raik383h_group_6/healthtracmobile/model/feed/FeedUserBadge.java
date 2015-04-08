package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;

import java.util.Date;

public class FeedUserBadge extends FeedModel {

    private UserBadge userBadge;

    public FeedUserBadge(String msg, Date date, IActivityNavigator nav, UserBadge userBadge) {
        super(msg, date, nav);
        this.userBadge = userBadge;
    }

    @Override
    public void onClick() {
        //nav.openUserBadge(userBadge);
    }
}
