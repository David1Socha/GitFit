package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncActivityService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateActivityView;

import java.util.Date;

public class CreateActivityPresenter extends BasePresenter {

    private IActivityNavigator nav;
    private CreateActivityView view;
    private AccessGrant grant;
    private IAsyncActivityService asvc;
    private Activity.ActivityType type;
    private long steps;
    private double duration, distance;

    @Inject
    CreateActivityPresenter(IAsyncActivityService asvc, @Assisted IActivityNavigator nav, @Assisted CreateActivityView view) {
        this.nav = nav;
        this.asvc = asvc;
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

    @Override
    protected AccessGrant getGrant() {
        return grant;
    }

    public void onClickCreateActivity() {
        if (validateFields()) {
            try {
                Activity a = new Activity();
                a.setType(type);
                a.setUserId(grant.getId());
                a.setStartDate(new Date());
                a.setDuration(duration);
                a.setDistance(distance);
                a.setSteps(steps);
            } catch (Exception e) {
                view.displayMessage(view.getResource(R.string.error_create_activity));
            }
        } else {
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
    }

    private boolean validateFields() {
        boolean valid = true;
        String typeStr = view.getType();
        type = Activity.ActivityType.valueOf(typeStr);
        String durationStr = view.getDuration();
        if (durationStr.isEmpty()) {
            valid = false;
            view.setDurationErr(view.getResource(R.string.empty_field_error));
        } else {
            duration = FormatUtils.parseDouble(durationStr);
        }
        String distanceStr = view.getDistance();
        if (distanceStr.isEmpty()) {
            valid = false;
            view.setDistanceErr(view.getResource(R.string.empty_field_error));
        } else {
            distance = FormatUtils.parseDouble(distanceStr);
        }
        String stepsStr = view.getSteps();
        if (stepsStr.isEmpty()) {
            valid = false;
            view.setStepErr(view.getResource(R.string.empty_field_error));
        } else {
            steps = FormatUtils.parseInt(stepsStr);
        }
        return valid;
    }

    public void onClickUpdateType() {
        Activity.ActivityType[] types = Activity.ActivityType.values();
        String[] typeStrs = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            typeStrs[i] = types[i].name();
        }
        view.promptUserType(typeStrs);
    }

    public void onChooseType(String type) {
        this.type = Activity.ActivityType.valueOf(type);
    }
}
