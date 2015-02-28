package com.raik383h_group_6.healthtracmobile.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raik383h_group_6.healthtracmobile.R;

import java.util.List;

import roboguice.inject.InjectView;

public class UserAdapter extends RoboBaseAdapter<UserAdapter.UserHolder> {
    public static class UserHolder {
        @InjectView(R.id.fb_login_button) //TODO
        Button button;
    }

    private List<String> items;

    public UserAdapter(Context context, int layoutId, List<String> items) {
        super(context, layoutId, UserHolder.class);
        this.items = items;
    }

    @Override
    protected UserHolder createNewViewHolder(int position, View convertView, ViewGroup parent) {
        return new UserHolder();
    }

    @Override
    protected void bindView(int position, View convertView, ViewGroup parent, UserHolder holder) {
        holder.button.setText(getItem(position));
    }

    public int getCount() {
        return items.size();
    }

    public String getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int i) {
        return i;
    }
}
