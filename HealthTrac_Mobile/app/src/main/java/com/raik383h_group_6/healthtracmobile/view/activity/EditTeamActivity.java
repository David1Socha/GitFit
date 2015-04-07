package com.raik383h_group_6.healthtracmobile.view.activity;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.EditTeamPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.service.api.TeamService;
import com.raik383h_group_6.healthtracmobile.view.EditTeamView;


public class EditTeamActivity extends BaseActivity implements EditTeamView {
    @Inject
    private PresenterFactory presenterFactory;
    private EditTeamPresenter presenter;
    @Inject
    TeamService teamService;


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void displayMessage(String msg) {

    }

    @Override
    public void setName(String val) {

    }

    @Override
    public void setDescription(String val) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setNameError(String msg) {

    }

    @Override
    public void setDescriptionError(String msg) {

    }
}
