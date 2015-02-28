package com.raik383h_group_6.healthtracmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RadioButton;

import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.R;


public class AccountFragment extends Fragment {
   // private User user = new User("123213","test","lol","idk","stuff", User.Sex.Female,12,12,"420","USERNAME", "2015");
    private User user;
    private EditText firstName, lastName, preferredName, dateOfBirth, height, weight;
    private RadioButton sex;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    public AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        Bundle bundle = this.getArguments();
        //      user = bundle.getParcelable("User");

        firstName = (EditText) rootView.findViewById(R.id.first_name_edittext);
        lastName = (EditText) rootView.findViewById(R.id.last_name_edittext);

        dateOfBirth = (EditText) rootView.findViewById(R.id.birthdate_edittext);
        height = (EditText) rootView.findViewById(R.id.height_edittext);
        weight = (EditText) rootView.findViewById(R.id.weight_edittext);
        if (user.getSex().equals(User.Sex.MALE)) {
            sex = (RadioButton) rootView.findViewById(R.id.radio_sex_male);
        } else {
            sex = (RadioButton) rootView.findViewById(R.id.radio_sex_female);
        }

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        preferredName.setText(user.getPreferredName());
        dateOfBirth.setText(user.getBirthDate().toString());
        height.setText(user.getHeight() + "");
        weight.setText(user.getWidth() + "");
        sex.setChecked(true);



        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((NavigationActivity) activity).onSectionAttached(1);
    }
}