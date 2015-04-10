package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;

import java.util.Date;

public class FeedActivity extends FeedModel {

    private Activity activity;
    private AccessGrant grant;
    private String username;

    public FeedActivity(String msg, Date date, IActivityNavigator nav, Activity activity, AccessGrant grant, String username) {
        super(msg, date, nav);
        this.grant = grant;
        this.activity = activity;
        this.username = username;
    }

    @Override
    public void onClick() {
        nav.openViewActivity(activity, username, grant);
    }

    @Override
    public Date getDateReal() {
        return date;
    }
}
