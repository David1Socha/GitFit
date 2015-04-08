package com.raik383h_group_6.healthtracmobile.view;

import android.content.res.Resources;

import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;

import java.util.List;

public interface FeedView extends BaseView {
    Resources getResources();
    void setFeedModels(List<FeedModel> fms);
    void setEmptyFeedDisplay(boolean enabled);
    void displayMessage(String msg);
}
