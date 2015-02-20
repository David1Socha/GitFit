package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.raik383h_group_6.healthtracmobile.models.User;

import org.w3c.dom.Text;


public class AccountFragment extends Fragment {
    private User user = new User("123213","test","lol","idk","stuff", User.Sex.Female,12,12,"420","USERNAME", "2015");
//    private User user;
    private EditText firstName, lastName, dateOfBirth, height_ft, height_in, weight;
    private TextView dateCreated;
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

        int ft = user.getHeight()/12;
        int in = user.getHeight()%12;

        firstName = (EditText) rootView.findViewById(R.id.name_edit);
 //       lastName = (EditText) rootView.findViewById(R.id.);
        dateOfBirth = (EditText) rootView.findViewById(R.id.birth_edit);
        height_ft = (EditText) rootView.findViewById(R.id.height_ft_edit);
        height_in = (EditText) rootView.findViewById(R.id.height_in_edit);
        weight = (EditText) rootView.findViewById(R.id.weight_lb_edit);
        dateCreated = (TextView) rootView.findViewById(R.id.creation_date_static);
        if (user.getSex().equals(User.Sex.Male)) {
            sex = (RadioButton) rootView.findViewById(R.id.radio_sex_male);
        } else {
            sex = (RadioButton) rootView.findViewById(R.id.radio_sex_female);
        }

        firstName.setText(user.getFirstName());
  //      lastName.setText(user.getLastName());
        dateOfBirth.setText(user.getBirthdate());
        height_ft.setText(ft + "");
        height_in.setText(in + "");
        weight.setText(user.getWeight() + "");
        dateCreated.setText("Member since " + user.getDateCreated());
        sex.setChecked(true);



        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((NavigationActivity) activity).onSectionAttached(1);
    }
}
