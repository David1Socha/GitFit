package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Activity;

import java.util.Date;

public class FeedActivity extends FeedModel {

    private Activity activity;

    public FeedActivity(String msg, Date date, IActivityNavigator nav, Activity activity) {
        super(msg, date, nav);
        this.activity = activity;
    }

    @Override
    public void onClick() {
        //nav.openActivityDetail(activity);
    }
}
