package com.raik383h_group_6.healthtracmobile.presenter;


import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserGoalService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ChallengeUsersView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChallengeUsersPresenter extends BasePresenter {

    private IActivityNavigator nav;
    private ChallengeUsersView view;
    private IAsyncUserService userService;
    private IAsyncUserGoalService userGoalService;
    private AccessGrant grant;
    private Goal goal;

    @Inject
    public ChallengeUsersPresenter(IAsyncUserService userService, IAsyncUserGoalService userGoalService, @Assisted IActivityNavigator nav, @Assisted ChallengeUsersView view) {
        this.nav = nav;
        this.userService = userService;
        this.userGoalService = userGoalService;
        this.view = view;
        goal = (Goal) view.getParcelableExtra(view.getResource(R.string.EXTRA_GOAL));
        grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onResume() {
        populateUsers();
    }

    public void populateUsers() {
        List<User> users = null;
        try {
            if (grant == null || grant.isExpired()) {
                view.setNoUsersMessageDisplay(true);
                return;
            }
            users = getUsersNotOnGoal();
            if (users != null) {
                view.setNoUsersMessageDisplay(false);
                view.setUsers(users);
            } else {
                view.setNoUsersMessageDisplay(true);
            }

        } catch (ExecutionException | InterruptedException e) {
            view.setNoUsersMessageDisplay(true);
        }
    }

    private List<User> getUsersNotOnGoal() throws ExecutionException, InterruptedException{
        List<User> users = new ArrayList<User>();
        List<UserGoal> goalMembership = userGoalService.getUserGoals(goal.getId(), grant.getAuthHeader());
        List<User> allUsers = userService.getUsersAsync(grant.getAuthHeader());
        if(goalMembership == null || allUsers == null) {
            return null;
        }
        List<String> userIds = new ArrayList<String>();
        for (UserGoal userGoal: goalMembership) {
            userIds.add(userGoal.getUserID());
        }
        for (User user: allUsers) {
            if(!userIds.contains(user.getId())) {
                users.add(user);
            }
        }
        return users;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }

    public void onClickChallengeUser(User user) {
        UserGoal challenge = new UserGoal();
        challenge.setDateAssigned(new Date());
        challenge.setDateCompleted(new Date());
        challenge.setGoalID(goal.getId());
        challenge.setUserID(user.getId());

        try {
            userGoalService.createUserGoal(challenge, grant.getAuthHeader());
        } catch (Exception e) {
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
