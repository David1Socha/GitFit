package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Field;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncGoalService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateGoalView;

public class CreateGoalPresenter extends BasePresenter {

    private IAsyncGoalService goalSvc;
    private IActivityNavigator nav;
    private CreateGoalView view;
    private AccessGrant grant;

    @Inject
    public CreateGoalPresenter(IAsyncGoalService goalSvc, @Assisted IActivityNavigator nav, @Assisted CreateGoalView view) {
        this.goalSvc = goalSvc;
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    public void onClickCreateGoal() {
        String name = view.getName();
        String thresholdStr = view.getThreshold();
        Field field = Field.valueOf(view.getField());

        if (validateFields(name, thresholdStr)) {
            double threshold = FormatUtils.parseDouble(thresholdStr);
            Goal g = new Goal();
            g.setName(name);
            g.setField(field);
            g.setThreshold(threshold);
            createGoal(g);
        }
    }

    private void createGoal(Goal g) {
        try {
            goalSvc.createGoal(g, grant.getAuthHeader());
            nav.finishCreateGoal();
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.error_create_goal));
            nav.finishCreateGoal();
        }
    }

    private boolean validateFields(String name, String threshold) {
        boolean valid = true;
        if (name.equals("")) {
            view.setNameError(view.getResource(R.string.empty_field_error));
            valid = false;
        }
        if (threshold.equals("")) {
            view.setThresholdError(view.getResource(R.string.empty_field_error));
            valid = false;
        }
        if (!valid) {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
        return valid;
    }

}
