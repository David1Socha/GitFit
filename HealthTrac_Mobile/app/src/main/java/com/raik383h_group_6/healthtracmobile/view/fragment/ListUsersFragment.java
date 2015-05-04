package com.raik383h_group_6.healthtracmobile.view.fragment;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.ListUsersPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.ListUsersView;

import java.util.List;

import roboguice.RoboGuice;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

public class ListUsersFragment extends BaseFragment implements ListUsersView {
    LinearLayout listUsersLayout;
    @InjectView(R.id.user_list_view)
    ListView userListView;
    @InjectView(R.id.no_users_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ListUsersPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IActivityNavigator nav = new ActivityNavigator(super.getActivity());
        listUsersLayout = (LinearLayout) inflater.inflate(R.layout.activity_list_users,container,false);
        presenter = presenterFactory.create(nav, this);
        return listUsersLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
    }

    @Override
    public void setUsers(List<User> users) {
        UserAdapter adapter = new UserAdapter(super.getActivity(), users);
        userListView.setAdapter(adapter);
    }


    public void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setNoUsersMessageDisplay(boolean enabled) {
        if (enabled) {
            noUsersTextView.setVisibility(View.VISIBLE);
        } else {
            noUsersTextView.setVisibility(View.GONE);
        }
    }
}
