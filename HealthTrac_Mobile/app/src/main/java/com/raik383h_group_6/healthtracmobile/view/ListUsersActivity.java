package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.ListUsersPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.Navigator;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_list_users)
public class ListUsersActivity extends RoboActivity {
    @InjectView(R.id.user_list_view)
    ListView userListView;
    @InjectView(R.id.no_users_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ListUsersPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        Navigator nav = new Navigator(this);
        presenter = presenterFactory.create(resources, nav, this);
        presenter.onCreate();
    }

    public void setUserListView(List<User> users) {
        ListAdapter listAdapter = new UserAdapter(this, users);
        userListView.setAdapter(listAdapter);
    }

    public void setNoUsersMessageDisplay(boolean enabled) {
        if (enabled) {
            noUsersTextView.setVisibility(View.VISIBLE);
        } else {
            noUsersTextView.setVisibility(View.GONE);
        }
    }

}
