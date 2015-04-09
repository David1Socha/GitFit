package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.adapter.UserAdapter;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewGoalPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewBadgeView;
import com.raik383h_group_6.healthtracmobile.view.ViewGoalView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_badge)
public class ViewBadgeActivity extends BaseActivity implements ViewBadgeView {
    @InjectView(R.id.badge_name)
    TextView badgeName;
    @InjectView(R.id.field_textview)
    TextView field;
    @InjectView(R.id.threshold_textview)
    TextView threshold;
    @InjectView(R.id.badge_holders_listview)
    ListView badgeHolders;
    @Inject
    PresenterFactory presenterFactory;
    ViewBadgePresenter presenter;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
        badgeHolders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });
    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setBadgeHolders(List<User> users) {
        UserAdapter adapter = new UserAdapter(this, users);
        badgeHolders.setAdapter(adapter);
    }

    @Override
    public void setBadgeName(String s) {
        badgeName.setText(s);
    }

    @Override
    public void setField(String s) {
        field.setText(s);
    }

    @Override
    public void setThreshold(String s) {
        threshold.setText(s);
    }
}
