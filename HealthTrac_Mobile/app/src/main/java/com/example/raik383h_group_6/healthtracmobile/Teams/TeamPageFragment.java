package com.example.raik383h_group_6.healthtracmobile.Teams;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.raik383h_group_6.healthtracmobile.R;
import com.example.raik383h_group_6.healthtracmobile.TabListener;

/**
 * Created by Aaron on 2/18/2015.
 */
public class TeamPageFragment extends Fragment{

    public static TeamPageFragment newInstance() {
        TeamPageFragment fragment = new TeamPageFragment();
        return fragment;
    }

    public TeamPageFragment() {

    }

    ActionBar.Tab newsfeed, members, badges;

    android.app.Fragment teamNewsfeed = new TeamNewsfeedFragment();
    android.app.Fragment teamMembers = new TeamMembersFragment();
    android.app.Fragment teamBadges = new TeamBadgesFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_team_page);
        ActionBar actionBar = getActivity().getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        newsfeed.setTabListener(new TabListener(teamNewsfeed));
        members.setTabListener(new TabListener(teamMembers));
        badges.setTabListener(new TabListener(teamBadges));
    }
}
