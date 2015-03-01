package com.raik383h_group_6.healthtracmobile.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.content.ResourcesAdapter;
import com.raik383h_group_6.healthtracmobile.presenter.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewUserPresenter;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_user)
public class ViewUserActivity extends RoboActionBarActivity {
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
    @InjectView(R.id.edit_user_button)
    private
    Button editUserButton;
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

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void onClickEditUser(View v) {
        presenter.onClickEditUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras = data == null ? null : data.getExtras();
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    public void setBirthDate(String txt) {
        birthDateTextView.setText(txt);
    }

    public void setEmail(String txt) {
        emailTextView.setText(txt);
    }

    public void setFirstName(String txt) {
        firstNameTextView.setText(txt);
    }

    public void setHeight(String txt) {
        heightTextView.setText(txt);
    }

    public void setLastName(String txt) {
        lastNameTextView.setText(txt);
    }

    public void setLocation(String txt) {
        locationTextView.setText(txt);
    }

    public void setPrefName(String txt) {
        prefNameTextView.setText(txt);
    }

    public void setSex(String txt) {
        sexTextView.setText(txt);
    }

    public void setUserName(String txt) {
        userNameTextView.setText(txt);
    }

    public void setWeight(String txt) {
        weightTextView.setText(txt);
    }

    public void setShowEditUserButton(boolean enabled) {
        if (enabled) {
            editUserButton.setVisibility(View.VISIBLE);
        } else {
            editUserButton.setVisibility(View.GONE);
        }
    }
}
