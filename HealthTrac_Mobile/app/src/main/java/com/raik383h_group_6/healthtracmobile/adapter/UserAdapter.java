package com.raik383h_group_6.healthtracmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.User;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        mInflater = LayoutInflater.from(context);
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null) {
            view = mInflater.inflate(R.layout.user_row_layout, parent, false);
            holder = new ViewHolder();
            holder.preferredName = (TextView)view.findViewById(R.id.preferred_name);
            holder.userName = (TextView) view.findViewById(R.id.user_name);
            holder.location = (TextView) view.findViewById(R.id.location);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        User user = users.get(position);
        holder.preferredName.setText(user.getPreferredName());
        holder.location.setText(user.getLocation());
        holder.userName.setText(user.getUserName());

        return view;
    }

    public class ViewHolder {
        public TextView preferredName, userName, location;
    }
}