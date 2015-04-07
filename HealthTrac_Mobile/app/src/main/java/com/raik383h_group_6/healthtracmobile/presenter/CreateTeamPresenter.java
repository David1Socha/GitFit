package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateTeamView;


public class CreateTeamPresenter extends BasePresenter {
    private CreateTeamView view;
    private IActivityNavigator nav;
    private IAsyncTeamService teamService;
    private AccessGrant grant;
    private TeamValidationPresenter teamValidationPresenter;

    @Inject
    public CreateTeamPresenter(IAsyncTeamService teamService, @Assisted CreateTeamView view, @Assisted IActivityNavigator nav, @Assisted TeamValidationPresenter teamValidationPresenter) {
        this.teamService = teamService;
        this.view = view;
        this.nav = nav;
        this.teamValidationPresenter = teamValidationPresenter;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onClickCreateTeam() {
        Team teamToCreate = teamValidationPresenter.validateTeam(view.getName(), view.getDescription());
        if (teamToCreate != null) {
            createTeam(teamToCreate);
        } else {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
    }

    public void createTeam(Team team) {
        try {
            teamService.createTeamAsync(team, grant.getAuthHeader());
            nav.finishCreateTeamSuccess();
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.team_not_made));
            nav.finishCreateTeamFailure();
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
