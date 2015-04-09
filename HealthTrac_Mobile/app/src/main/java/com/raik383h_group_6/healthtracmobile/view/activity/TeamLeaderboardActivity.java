package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.LeaderboardAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.TeamLeaderboardPresenter;
import com.raik383h_group_6.healthtracmobile.view.TeamLeaderboardView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_team_leaderboard)
public class TeamLeaderboardActivity extends BaseActivity implements TeamLeaderboardView {
    @InjectView(R.id.leaderboard_list_view)
    ListView leaderboardListView;
    @InjectView(R.id.empty_leaderboard_textview)
    TextView noUsersTextView;
    @Inject
    PresenterFactory presenterFactory;
    private TeamLeaderboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        leaderboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    public void setUsers(List<User> users) {
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, users);
        leaderboardListView.setAdapter(adapter);
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
    public BasePresenter getPresenter() { return presenter; }
}
