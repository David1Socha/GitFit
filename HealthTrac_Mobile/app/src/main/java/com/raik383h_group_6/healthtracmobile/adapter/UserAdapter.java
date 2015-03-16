package com.raik383h_group_6.healthtracmobile.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<User> users;
    private Context context;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
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
            holder.profilePicture = (ImageView) view.findViewById(R.id.profile_picture);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        User user = users.get(position);
        holder.preferredName.setText(user.getPreferredName());
        Log.d("davidsocha", user.getProfilePicture());
        Picasso.with(context)
                .load(user.getProfilePicture())
                .placeholder(R.drawable.default_profile_picture)
                .resizeDimen(R.dimen.prof_pic_size, R.dimen.prof_pic_size)
                .tag(context)
                .centerInside()
                .into(holder.profilePicture);

        holder.userName.setText(user.getUserName());

        return view;
    }

    public class ViewHolder {
        public TextView preferredName, userName;
        public ImageView profilePicture;
    }
}