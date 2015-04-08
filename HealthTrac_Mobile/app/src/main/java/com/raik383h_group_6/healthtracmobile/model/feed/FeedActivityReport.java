package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Meal;

import java.util.Date;

public class FeedActivityReport extends FeedModel {

    private ActivityReport rep;
    private AccessGrant grant;

    public FeedActivityReport(String msg, Date date, IActivityNavigator nav, ActivityReport rep, AccessGrant grant) {
        super(msg, date, nav);
        this.rep = rep;
        this.grant = grant;
    }

    @Override
    public void onClick() {
        //nav.openActivityReportDetail(rep, grant);
    }

    @Override
    public Date getDateReal() {
        return date;
    }
}
