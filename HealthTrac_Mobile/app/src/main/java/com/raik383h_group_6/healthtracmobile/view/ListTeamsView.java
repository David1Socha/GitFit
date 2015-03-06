package com.raik383h_group_6.healthtracmobile.view;

import android.widget.ListAdapter;

import com.raik383h_group_6.healthtracmobile.model.Team;

import java.util.List;

public interface ListTeamsView {
    void setListAdapter(ListAdapter adapter);

    void setNoTeamsMessageDisplay(boolean enabled);

    void setTeamsList(List<Team> teams);
}
