package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;

import java.util.Date;

public class FeedUserGoal extends FeedModel {

    private UserGoal userGoal;

    public FeedUserGoal(String msg, Date date, IActivityNavigator nav, UserGoal userGoal) {
        super(msg, date, nav);
        this.userGoal = userGoal;
    }

    @Override
    public void onClick() {
        //nav.openUserBadge(userBadge);
    }
}
