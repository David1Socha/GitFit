package com.raik383h_group_6.healthtracmobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.ListTeamsPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_list_teams)
public class ListTeamsActivity extends RoboActionBarActivity {
    @InjectView(R.id.team_list_view)
    ListView teamListView;
    @InjectView(R.id.no_teams_textview)
    TextView noTeamsTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ListTeamsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        ActivityNavigator nav = new ActivityNavigator(this);
        Bundle extras = getIntent().getExtras();
        presenter = presenterFactory.create(extras, resources, nav, this);
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void setListAdapter(ListAdapter adapter) {
        teamListView.setAdapter(adapter);
    }

    public void setNoTeamsMessageDisplay(boolean enabled) {
        if (enabled) {
            noTeamsTextView.setVisibility(View.VISIBLE);
        } else {
            noTeamsTextView.setVisibility(View.GONE);
        }
    }

}
