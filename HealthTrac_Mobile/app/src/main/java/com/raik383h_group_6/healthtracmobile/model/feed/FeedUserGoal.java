package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;

import java.util.Date;

public class FeedUserGoal extends FeedModel {

    private Goal goal;
    private AccessGrant grant;

    public FeedUserGoal(String msg, Date date, IActivityNavigator nav, Goal goal, AccessGrant grant) {
        super(msg, date, nav);
        this.goal = goal;
        this.grant = grant;
    }

    @Override
    public void onClick() {
        //nav.openGoal(goal, grant);
    }
}
