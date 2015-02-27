package com.raik383h_group_6.healthtracmobile.content;

import android.content.res.Resources;

public class ResourcesAdapter implements IResources {

    private Resources resources;

    public ResourcesAdapter(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String getString(int resId) {
        return resources.getString(resId);
    }

    @Override
    public String getString(int resId, Object... params) {
        return resources.getString(resId, params);
    }
}
