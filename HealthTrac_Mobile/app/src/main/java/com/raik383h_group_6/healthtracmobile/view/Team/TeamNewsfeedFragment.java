package com.raik383h_group_6.healthtracmobile.view.team;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raik383h_group_6.healthtracmobile.R;

/**
 * Created by Aaron on 2/18/2015.
 */
public class TeamNewsfeedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_badges, null);

        return rootView;
    }
}