package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Meal;

import java.util.Date;

public class FeedMeal extends FeedModel {

    private Meal meal;
    private String username;
    private AccessGrant grant;

    public FeedMeal(String msg, Date date, IActivityNavigator nav, Meal meal, AccessGrant grant, String username) {
        super(msg, date, nav);
        this.meal = meal;
        this.username = username;
        this.grant = grant;
    }

    @Override
    public void onClick() {
        nav.openViewMeal(meal, username, grant);
    }

    @Override
    public Date getDateReal() {
        return date;
    }
}
