package com.raik383h_group_6.healthtracmobile.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewActivityPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityView;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_activity)
public class ViewActivityActivity extends BaseActivity implements ViewActivityView {

    @InjectView(R.id.title_textview)
    private TextView title;
    @InjectView(R.id.type_textview)
    private TextView type;
    @InjectView(R.id.duration_textview)
    private TextView duration;
    @InjectView(R.id.distance_textview)
    private TextView distance;
    @InjectView(R.id.steps_textview)
    private TextView steps;
    @InjectView(R.id.startdate_textview)
    private TextView startDate;
    @Inject
    private PresenterFactory presenterFactory;
    private ViewActivityPresenter presenter;

    protected void onCreate(Bundle s) {
        super.onCreate(s);
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter = presenterFactory.create(nav, this);
    }

    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setDate(String d) {
        startDate.setText(d);
    }

    @Override
    public void setTitle(String t) {
        title.setText(t);
    }

    @Override
    public void setType(String t) {
        type.setText(t);
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

    public void onClickUpdateType(View v) {
        presenter.onClickUpdateType();
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void promptUserType(final String[] types) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.prompt_type));
        builder.setItems(types, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                presenter.onChooseType(types[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
