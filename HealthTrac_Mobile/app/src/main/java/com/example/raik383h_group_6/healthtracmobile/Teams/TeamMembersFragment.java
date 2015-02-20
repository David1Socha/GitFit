package com.example.raik383h_group_6.healthtracmobile.teams;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.raik383h_group_6.healthtracmobile.R;

/**
 * Created by Aaron on 2/18/2015.
 */
public class TeamMembersFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_members, container, false);

        String[] teamMembers = {"Enter", "Members", "Here"};

        ListAdapter adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, teamMembers);

        ListView teamsView = (ListView) rootView.findViewById(R.id.teamMembersListView);

        teamsView.setAdapter(adapter);

        teamsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;
    }
}
