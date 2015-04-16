package com.raik383h_group_6.healthtracmobile.view.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.BanMembersAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BanMembersPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.BanMembersView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_ban_members)
public class BanMembersActivity extends BaseActivity implements BanMembersView{
    @InjectView(R.id.ban_members_list_view)
    ListView banMembersListView;
    @InjectView(R.id.empty_ban_members_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private BanMembersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        banMembersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
        presenter = presenterFactory.create(nav, this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setUsers(List<User> users) {
        BanMembersAdapter adapter = new BanMembersAdapter(this, users, presenter);
        banMembersListView.setAdapter(adapter);
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
    public BasePresenter getPresenter() { return presenter; }
}
