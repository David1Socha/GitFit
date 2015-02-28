package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.Navigator;

import java.util.List;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;

public class ListUsersActivity extends RoboListActivity {
    @InjectView(R.id.user_list_view)
    ListView userListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator nav = new Navigator(this);
    }
    public void setUserListView(List<User> users) {
        ListAdapter listAdapter = new UserAdapter(this, users);
        userListView.setAdapter(listAdapter);
    }

}
