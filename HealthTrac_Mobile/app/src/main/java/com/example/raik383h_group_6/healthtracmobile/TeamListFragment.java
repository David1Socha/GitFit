package com.example.raik383h_group_6.healthtracmobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class TeamListFragment extends Fragment {

    public static TeamListFragment newInstance() {
        TeamListFragment fragment = new TeamListFragment();
        return fragment;
    }

    public TeamListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_list, container, false);

        String[] userTeams = {"Enter", "Teams", "Here"};

        ListAdapter adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, userTeams);

        ListView teamsView = (ListView) rootView.findViewById(R.id.theListView);

        teamsView.setAdapter(adapter);

        teamsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((NavigationActivity) activity).onSectionAttached(2);
    }
}
