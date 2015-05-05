package com.raik383h_group_6.healthtracmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.InviteMembersPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InviteMembersAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<User> users;
    private Context context;
    private InviteMembersPresenter presenter;

    public InviteMembersAdapter(Context context, List<User> users, InviteMembersPresenter presenter) {
        this.presenter = presenter;
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
        final View view;
        final ViewHolder holder;
        if(convertView == null) {
            view = mInflater.inflate(R.layout.invite_users_row_layout, parent, false);
            holder = new ViewHolder();
            holder.userName = (TextView)view.findViewById(R.id.invite_member_username);
            holder.profilePicture = (ImageView)view.findViewById(R.id.invite_member_picture);
            holder.invite = (Button)view.findViewById(R.id.button_invite_member);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }
        final User user = users.get(position);
        holder.userName.setText(user.getUserName());
        Picasso.with(context)
                .load(user.getProfilePicture())
                .placeholder(R.drawable.default_profile_picture)
                .resizeDimen(R.dimen.prof_pic_size, R.dimen.prof_pic_size)
                .tag(context)
                .centerInside()
                .into(holder.profilePicture);
        holder.invite.setText(R.string.invite_member);
        holder.invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.invite.getText().equals(R.string.invite_member)) {
                    presenter.onClickInviteMember(user);
                    holder.invite.setText(R.string.invited_member);
                }
            }
        });


        return view;
    }

    public class ViewHolder {
        public TextView userName;
        public ImageView profilePicture;
        public Button invite;
    }
}
