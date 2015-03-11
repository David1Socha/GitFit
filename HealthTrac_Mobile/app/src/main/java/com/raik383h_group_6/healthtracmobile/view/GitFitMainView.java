package com.raik383h_group_6.healthtracmobile.view;

public interface GitFitMainView extends CustomMenuView{
    String getPref(String key);

    void setPref(String key, String val);

    void displayMessage(String msg);
}
