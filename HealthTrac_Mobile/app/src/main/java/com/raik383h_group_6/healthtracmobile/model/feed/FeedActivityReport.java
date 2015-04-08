package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.model.Meal;

import java.util.Date;

public class FeedActivityReport extends FeedModel {

    private ActivityReport rep;

    public FeedActivityReport(String msg, Date date, IActivityNavigator nav, ActivityReport rep) {
        super(msg, date, nav);
        this.rep = rep;
    }

    @Override
    public void onClick() {
        //nav.openActivityReportDetail(rep);
    }
}
