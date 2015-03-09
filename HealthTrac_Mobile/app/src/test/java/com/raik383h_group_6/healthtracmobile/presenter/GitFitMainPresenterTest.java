package com.raik383h_group_6.healthtracmobile.presenter;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.helper.ModelGenerator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncTeamService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainView;
import com.raik383h_group_6.healthtracmobile.view.ListTeamsView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.raik383h_group_6.healthtracmobile.helper.TestConstants.GRANT_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitFitMainPresenterTest {

    private GitFitMainView view;
    private GitFitMainPresenter presenter;
    private IAsyncUserService userService;
    private IResources resources;
    private IActivityNavigator nav;

    @Before
    public void setup() {
        view = mock(GitFitMainView.class);
        userService = mock(IAsyncUserService.class);
        resources = mock(IResources.class);
        nav = mock(IActivityNavigator.class);
        presenter = new GitFitMainPresenter(userService, resources, nav, view);
    }

    @Test
    public void truth() {

    }
}
