package com.raik383h_group_6.healthtracmobile.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.InviteMembersAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.InviteMembersPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.InviteMembersView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_invite_members)
public class InviteMembersActivity extends BaseActivity implements InviteMembersView {
    @InjectView(R.id.invite_members_list_view)
    ListView inviteMembersListView;
    @InjectView(R.id.empty_invite_members_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private InviteMembersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        inviteMembersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        InviteMembersAdapter adapter = new InviteMembersAdapter(this, users, presenter);
        inviteMembersListView.setAdapter(adapter);
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
