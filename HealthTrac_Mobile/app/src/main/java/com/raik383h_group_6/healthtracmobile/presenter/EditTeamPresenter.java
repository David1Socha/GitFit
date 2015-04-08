package com.raik383h_group_6.healthtracmobile.presenter;


import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.EditTeamView;

public class EditTeamPresenter extends BasePresenter{
    private final IAsyncTeamService teamService;
    private final EditTeamView view;
    private final IActivityNavigator nav;
    private TeamValidationPresenter teamValidationPresenter;
    private AccessGrant grant;
    private Team team;

    @Inject
    public EditTeamPresenter(IAsyncTeamService teamService, @Assisted TeamValidationPresenter teamValidationPresenter, @Assisted IActivityNavigator nav, @Assisted EditTeamView view) {
        this.teamService = teamService;
        this.nav = nav;
        this.view = view;
        this.teamValidationPresenter = teamValidationPresenter;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.team = (Team) view.getParcelableExtra(view.getResource(R.string.EXTRA_TEAM));
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onCreate() {
        populateFields();
    }

    private void populateFields() {
        if (team != null) {
            view.setName(team.getName());
            view.setDescription(team.getDescription());
        }
    }

    public void onClickUpdateTeam() {
        Team teamToUpdate = teamValidationPresenter.validateTeam(view.getName(), view.getDescription());
        if (teamToUpdate != null) {
            teamToUpdate.setId(team.getId());
            updateTeam(teamToUpdate);
        } else {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
    }

    private void updateTeam(Team team) {
        try {
            teamService.updateTeamAsync(team.getId(), team, grant.getAuthHeader());
            view.displayMessage(view.getResource(R.string.team_updated_message));
            nav.finishEditTeamSuccess(team);
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.update_team_error));
            nav.finishEditTeamFailure();
        }
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }
}
