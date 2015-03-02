package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewTeamPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.ViewUserPresenter;

import org.w3c.dom.Text;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_team)
public class ViewTeamActivity extends RoboActionBarActivity {
    @InjectView(R.id.team_name_textview)
    TextView teamNameTextView;
    @InjectView(R.id.description_textview)
    TextView descriptionTextView;
    @InjectView(R.id.date_created_textview)
    TextView dateCreatedTextView;
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
        IResources resources = new ResourcesAdapter(getResources());
        ActivityNavigator nav = new ActivityNavigator(this);
        Bundle extras = getIntent().getExtras();
        presenter = presenterFactory.create(extras, resources, nav, this);
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

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

    public void setTeamName(String msg) {
        teamNameTextView.setText(msg);
    }

    public void setDescription(String msg) {
        descriptionTextView.setText(msg);
    }

    public void setDateCreated(String msg) {
        dateCreatedTextView.setText(msg);
    }

    public void setShowEditTeam(boolean enabled) {
        if (enabled) {
            editTeamButton.setVisibility(View.VISIBLE);
        } else {
            editTeamButton.setVisibility(View.GONE);
        }
    }

    public void setShowLeaveTeam(boolean enabled) {
        if (enabled) {
            leaveTeamButton.setVisibility(View.VISIBLE);
        } else {
            leaveTeamButton.setVisibility(View.GONE);
        }
    }

    public void setShowJoinTeam(boolean enabled) {
        if (enabled) {
            joinTeamButton.setVisibility(View.VISIBLE);
        } else {
            joinTeamButton.setVisibility(View.GONE);
        }
    }

}
