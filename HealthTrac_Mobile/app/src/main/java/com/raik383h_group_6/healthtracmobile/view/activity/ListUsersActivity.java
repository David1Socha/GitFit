package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.ListUsersPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_list_users)
public class ListUsersActivity extends BaseActivity implements ListUsersView {
    @InjectView(R.id.user_list_view)
    ListView userListView;
    @InjectView(R.id.no_users_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ListUsersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void setUsers(List<User> users) {
        UserAdapter adapter = new UserAdapter(this, users);
        userListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setNoUsersMessageDisplay(boolean enabled) {
        if (enabled) {
            noUsersTextView.setVisibility(View.VISIBLE);
        } else {
            noUsersTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
