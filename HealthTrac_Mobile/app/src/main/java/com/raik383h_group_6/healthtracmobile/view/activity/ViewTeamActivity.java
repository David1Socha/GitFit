package com.raik383h_group_6.healthtracmobile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewTeamPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewTeamView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_team)
public class ViewTeamActivity extends BaseActivity implements ViewTeamView {
    @InjectView(R.id.team_name_textview)
    TextView teamNameTextView;
    @InjectView(R.id.description_textview)
    TextView descriptionTextView;
    @InjectView(R.id.date_created_textview)
    TextView dateCreatedTextView;
    @InjectView(R.id.invite_members_button)
    Button inviteMembersButton;
    @InjectView(R.id.ban_members_button)
    Button banMembersButton;
    @InjectView(R.id.edit_team_button)
    Button editTeamButton;
    @InjectView(R.id.join_team_button)
    Button joinTeamButton;
    @InjectView(R.id.leave_team_button)
    Button leaveTeamButton;
    @Inject
    PresenterFactory presenterFactory;
    private ViewTeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void onClickViewLeaderboard(View v) {
        presenter.onClickViewLeaderboard();
    }

    public void onClickInviteMembers(View v) { presenter.onClickInviteMembers(); }

    public void onClickBanMembers(View v) { presenter.onClickBanMembers(); }

    public void onClickEditTeam(View v) {
        presenter.onClickEditTeam();
    }

    public void onClickLeaveTeam(View v) {
        presenter.onClickLeaveTeam();
    }

    public void onClickJoinTeam(View v) {
        presenter.onClickJoinTeam();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras = data == null ? null : data.getExtras();
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    @Override
    public void setTeamName(String msg) {
        teamNameTextView.setText(msg);
    }

    @Override
    public void setDescription(String msg) {
        descriptionTextView.setText(msg);
    }

    @Override
    public void setDateCreated(String msg) {
        dateCreatedTextView.setText(msg);
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onClickTeamFeed(View v) {
        presenter.onClickTeamFeed();
    }

    @Override
    public void setShowInviteMembers(boolean enabled) {
        if (enabled) {
            inviteMembersButton.setVisibility(View.VISIBLE);
        } else {
            inviteMembersButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setShowBanMembers(boolean enabled) {
        if (enabled) {
            banMembersButton.setVisibility(View.VISIBLE);
        } else {
            banMembersButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setShowEditTeam(boolean enabled) {
        if (enabled) {
            editTeamButton.setVisibility(View.VISIBLE);
        } else {
            editTeamButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setShowLeaveTeam(boolean enabled) {
        if (enabled) {
            leaveTeamButton.setVisibility(View.VISIBLE);
        } else {
            leaveTeamButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setShowJoinTeam(boolean enabled) {
        if (enabled) {
            joinTeamButton.setVisibility(View.VISIBLE);
        } else {
            joinTeamButton.setVisibility(View.GONE);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
