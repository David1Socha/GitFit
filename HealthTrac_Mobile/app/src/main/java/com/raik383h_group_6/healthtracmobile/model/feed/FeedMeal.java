package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Meal;

import java.util.Date;

public class FeedMeal extends FeedModel {

    private Meal meal;

    public FeedMeal(String msg, Date date, IActivityNavigator nav, Meal meal) {
        super(msg, date, nav);
        this.meal = meal;
    }

    @Override
    public void onClick() {
        //nav.openMealDetail(meal);
    }
}
