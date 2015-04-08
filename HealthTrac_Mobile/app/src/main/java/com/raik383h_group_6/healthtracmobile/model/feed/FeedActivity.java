package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;

import java.util.Date;

public class FeedActivity extends FeedModel {

    private Activity activity;
    private AccessGrant grant;

    public FeedActivity(String msg, Date date, IActivityNavigator nav, Activity activity, AccessGrant grant) {
        super(msg, date, nav);
        this.grant = grant;
        this.activity = activity;
    }

    @Override
    public void onClick() {
        //nav.openActivityDetail(activity, grant);
    }

    @Override
    public Date getDateReal() {
        return date;
    }
}
