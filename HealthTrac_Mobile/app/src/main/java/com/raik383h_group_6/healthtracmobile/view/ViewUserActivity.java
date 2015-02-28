package com.raik383h_group_6.healthtracmobile.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewUserPresenter;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_user)
public class ViewUserActivity extends ActionBarActivity {
    @InjectView(R.id.birthdate_textview)
    TextView birthDateTextView;
    @InjectView(R.id.email_textview)
    TextView emailTextView;
    @InjectView(R.id.first_name_textview)
    TextView firstNameTextView;
    @InjectView(R.id.height_textview)
    TextView heightTextView;
    @InjectView(R.id.last_name_textview)
    TextView lastNameTextView;
    @InjectView(R.id.location_textview)
    TextView locationTextView;
    @InjectView(R.id.pref_name_textview)
    TextView prefNameTextView;
    @InjectView(R.id.sex_textview)
    TextView sexTextView;
    @InjectView(R.id.user_name_textview)
    TextView userNameTextView;
    @InjectView(R.id.weight_textview)
    TextView weightTextView;
    @Inject
    PresenterFactory presenterFactory;
    private ViewUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IResources resources = new ResourcesAdapter(getResources());
        ActivityNavigator nav = new ActivityNavigator(this);
        Bundle extras = getIntent().getExtras();
        presenter = presenterFactory.create(extras, resources, nav, this);
        presenter.onCreate();
    }

}
