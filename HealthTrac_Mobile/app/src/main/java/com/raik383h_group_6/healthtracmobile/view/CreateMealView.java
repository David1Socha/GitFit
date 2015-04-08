package com.raik383h_group_6.healthtracmobile.view;

public interface CreateMealView extends BaseView {

    void displayMessage(String msg);

    String getCalories();

    void setCaloriesError(String err);
}
