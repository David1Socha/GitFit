package com.raik383h_group_6.healthtracmobile.view;


import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.Fragment;
import com.raik383h_group_6.healthtracmobile.R;

/**
 * Created by Aaron on 2/18/2015.
 */
public class TabListener implements ActionBar.TabListener{
    Fragment fragment;

    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.teamContainer, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}