package com.raik383h_group_6.healthtracmobile.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.presenter.ViewUserPresenter;
import com.raik383h_group_6.healthtracmobile.view.ViewUserView;
import com.squareup.picasso.Picasso;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_view_user)
public class ViewUserFragment extends BaseFragment implements ViewUserView {
    ScrollView viewUserLayout;
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
    @InjectView(R.id.profile_picture)
    ImageView profileImageView;
    @InjectView(R.id.edit_user_button)
    private Button editUserButton;
    @InjectView(R.id.user_feed_button)
    private Button userFeedButton;
    @Inject
    PresenterFactory presenterFactory;
    private ViewUserPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IActivityNavigator nav = new ActivityNavigator(super.getActivity());
        viewUserLayout = (ScrollView) inflater.inflate(R.layout.activity_view_user,container,false);
        presenter = presenterFactory.create(nav, this);
        return viewUserLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEditUser(v);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void onClickEditUser(View v) {
        presenter.onClickEditUser();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extras = data == null ? null : data.getExtras();
        presenter.onActivityResult(requestCode, resultCode, extras);
    }

    @Override
    public void setBirthDate(String txt) {
        birthDateTextView.setText(txt);
    }

    @Override
    public void setEmail(String txt) {
        emailTextView.setText(txt);
    }

    @Override
    public void setFirstName(String txt) {
        firstNameTextView.setText(txt);
    }

    @Override
    public void setHeight(String txt) {
        heightTextView.setText(txt);
    }

    @Override
    public void setLastName(String txt) {
        lastNameTextView.setText(txt);
    }

    @Override
    public void setLocation(String txt) {
        locationTextView.setText(txt);
    }

    @Override
    public void setPrefName(String txt) {
        prefNameTextView.setText(txt);
    }

    @Override
    public void setSex(String txt) {
        sexTextView.setText(txt);
    }

    @Override
    public void setUserName(String txt) {
        userNameTextView.setText(txt);
    }

    @Override
    public void setWeight(String txt) {
        weightTextView.setText(txt);
    }

    @Override
    public void setShowEditUserButton(boolean enabled) {
        if (enabled) {
            editUserButton.setVisibility(View.VISIBLE);
        } else {
            editUserButton.setVisibility(View.GONE);
        }
    }

    @Override
         public void setShowUserFeedButton(boolean enabled) {
        if (enabled) {
            userFeedButton.setVisibility(View.VISIBLE);
        } else {
            userFeedButton.setVisibility(View.GONE);
        }
    }

    public void onClickUserFeed(View v) {
        presenter.onClickUserFeed();
    }

    @Override
    public void setProfilePicture(String profilePicture) {
        Picasso.with(this.getActivity())
            .load(profilePicture)
            .placeholder(R.drawable.default_profile_picture)
            .resizeDimen(R.dimen.prof_pic_size, R.dimen.prof_pic_size)
            .centerInside()
            .into(profileImageView);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
