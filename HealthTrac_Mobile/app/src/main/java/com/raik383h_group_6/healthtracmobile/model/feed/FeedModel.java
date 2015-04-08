package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.service.FormatUtils;

import java.util.Date;

public abstract class FeedModel {

    protected String msg;
    protected Date date;

    public FeedModel(String msg, Date date) {
        this.msg = msg;
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public String getDate() {
        return FormatUtils.format(date);
    }
}
