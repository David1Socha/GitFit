package com.raik383h_group_6.healthtracmobile.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.FeedModelAdapter;
import com.raik383h_group_6.healthtracmobile.adapter.GoalProgressAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.model.feed.GoalProgress;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.FeedPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.FeedView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

public class FeedFragment extends BaseFragment implements FeedView{
    LinearLayout feedLayout;
    @InjectView(R.id.goal_listview)
    ListView goalListView;
    @InjectView(R.id.feed_listview)
    ListView listView;
    @InjectView(R.id.no_feeds_textview)
    TextView noFeedsText;
    @InjectView(R.id.steps_textview)
    TextView steps;
    @InjectView(R.id.duration_textview)
    TextView duration;
    @InjectView(R.id.distance_textview)
    TextView distance;
    @InjectView(R.id.feed_header)
    LinearLayout header;
    @InjectView(R.id.goal_header)
    LinearLayout goalHeader;
    @Inject
    PresenterFactory presenterFactory;
    private FeedPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IActivityNavigator nav = new ActivityNavigator(super.getActivity());
        feedLayout = (LinearLayout) inflater.inflate(R.layout.activity_feed,container,false);
        presenter = presenterFactory.create(nav, this);
        return feedLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onFeedItemClick(parent, view, position, id);
            }
        });
        goalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onGoalItemClick(parent, view, position, id);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setFeedModels(List<FeedModel> fms) {
        FeedModelAdapter adapter = new FeedModelAdapter(this.getActivity(), fms);
        listView.setAdapter(adapter);
    }

    @Override
    public void setEmptyFeedDisplay(boolean enabled) {
        if (enabled) {
            noFeedsText.setVisibility(View.VISIBLE);
        } else {
            noFeedsText.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFeedHeaderDisplay(boolean enabled) {
        if (enabled) {
            header.setVisibility(View.VISIBLE);
        } else {
            header.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDuration(String d) {
        duration.setText(d);
    }

    @Override
    public void setDistance(String d) {
        distance.setText(d);
    }

    @Override
    public void setSteps(String s) {
        steps.setText(s);
    }

    @Override
    public void setGoalHeaderDisplay(boolean enabled) {
        if (enabled) {
            goalHeader.setVisibility(View.VISIBLE);
        } else {
            goalHeader.setVisibility(View.GONE);
        }
    }

    @Override
    public void setGoalsInProgress(List<GoalProgress> gps) {
        GoalProgressAdapter adapter = new GoalProgressAdapter(this.getActivity(), gps);
        goalListView.setAdapter(adapter);
    }
}
