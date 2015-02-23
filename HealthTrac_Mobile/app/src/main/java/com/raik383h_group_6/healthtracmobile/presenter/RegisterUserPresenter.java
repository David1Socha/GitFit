package com.raik383h_group_6.healthtracmobile.presenter;

import com.raik383h_group_6.healthtracmobile.model.FacebookUser;
import com.raik383h_group_6.healthtracmobile.service.api.FacebookService;
import com.raik383h_group_6.healthtracmobile.view.RegisterUserActivity;

public class RegisterUserPresenter {
    private RegisterUserActivity view;
    private FacebookService facebookService;
    private FacebookUser facebookUser;

    public void initialize(FacebookService facebookService, RegisterUserActivity view) {
        this.facebookService = facebookService;
        this.view = view;
    }

    public void onCreate() {
        //facebookUser = facebookService.getUser()
    }
}
