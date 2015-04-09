package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.FeedModelAdapter;
import com.raik383h_group_6.healthtracmobile.adapter.TeamAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.FeedPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.ListTeamsPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.FeedView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_feed)
public class FeedActivity extends BaseActivity implements FeedView{

    @InjectView(R.id.feed_listview)
    ListView listView;
    @InjectView(R.id.no_feeds_textview)
    TextView noFeedsText;
    @Inject
    PresenterFactory presenterFactory;
    private FeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setFeedModels(List<FeedModel> fms) {
        FeedModelAdapter adapter = new FeedModelAdapter(this, fms);
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
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}