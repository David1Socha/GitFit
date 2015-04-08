package com.raik383h_group_6.healthtracmobile.view;

import android.os.Parcelable;

public interface BaseView {

    String getResource(int id);

    String getResource(int id, Object... params);

    String getStringExtra(String key);

    long getLongExtra(String key);

    Parcelable getParcelableExtra(String key);

    String getPref(String key);

    void setPref(String key, String val);

    void clearPrefs();
}
