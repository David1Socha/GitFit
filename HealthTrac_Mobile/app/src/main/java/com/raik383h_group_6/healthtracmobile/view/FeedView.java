package com.raik383h_group_6.healthtracmobile.view;

import android.content.res.Resources;

import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.model.feed.GoalProgress;

import java.util.List;

public interface FeedView extends BaseView {
    Resources getResources();
    void setFeedModels(List<FeedModel> fms);
    void setEmptyFeedDisplay(boolean enabled);
    void displayMessage(String msg);
    void setFeedHeaderDisplay(boolean enabled);
    void setDuration(String d);
    void setDistance(String d);
    void setSteps(String s);

    void setGoalHeaderDisplay(boolean enabled);

    void setGoalsInProgress(List<GoalProgress> goalsInProgress);
}
