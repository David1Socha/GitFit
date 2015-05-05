package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;

import java.util.Date;

public abstract class FeedModel implements Comparable<FeedModel> {

    protected String msg;
    protected Date date;
    protected IActivityNavigator nav;

    public FeedModel(String msg, Date date, IActivityNavigator nav) {
        this.msg = msg;
        this.date = date;
        this.nav = nav;
    }

    public String getMsg() {
        return msg;
    }

    public String getDate() {
        return FormatUtils.format(date);
    }

    public abstract Date getDateReal();

    public abstract void onClick();

    @Override
    public int compareTo(FeedModel o) {
        return -date.compareTo(o.getDateReal());
    }
}
