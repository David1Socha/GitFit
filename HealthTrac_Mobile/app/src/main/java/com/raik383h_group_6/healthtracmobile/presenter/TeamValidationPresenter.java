package com.raik383h_group_6.healthtracmobile.presenter;


import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.TeamValidationView;

import java.util.Date;

public class TeamValidationPresenter {

    private final TeamValidationView view;
    private final IAsyncTeamService teamService;

    @Inject
    public TeamValidationPresenter(IAsyncTeamService teamService, @Assisted TeamValidationView view) {
        this.view = view;
        this.teamService = teamService;
    }

    public Team validateTeam(String name, String description) {
        Team teamToCreate = null;
        if (fieldsValid(name, description)) {
            Date dateCreated = new Date();
            Date dateModified = new Date();
            teamToCreate = new Team(name, description, dateCreated, dateModified);
        }
        return teamToCreate;
    }

    private boolean fieldsValid(String name, String description) {
        boolean allGood = true;
        if (name.equals("")) {
            allGood = false;
            view.setNameError(view.getResource(R.string.empty_field_error));
        }
        if (description.equals("")) {
            allGood = false;
            view.setDescriptionError(view.getResource(R.string.empty_field_error));
        }
        return allGood;
    }
}
