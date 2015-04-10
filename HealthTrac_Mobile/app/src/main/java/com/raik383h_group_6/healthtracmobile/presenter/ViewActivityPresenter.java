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
import com.raik383h_group_6.healthtracmobile.view.ViewActivityView;

public class ViewActivityPresenter extends BasePresenter {

    private AccessGrant grant;
    private ViewActivityView view;
    private IActivityNavigator nav;
    private Activity activity;
    private String username;
    private IAsyncActivityService asvc;

    @Inject
    public ViewActivityPresenter(IAsyncActivityService asvc, @Assisted IActivityNavigator nav, @Assisted ViewActivityView view) {
        this.nav = nav;
        this.view = view;
        this.asvc = asvc;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.activity = (Activity) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACTIVITY));
        this.username = view.getStringExtra(view.getResource(R.string.EXTRA_USERNAME));
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

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setDistance(FormatUtils.format(activity.getDistance()));
        view.setDuration(FormatUtils.format(activity.getDuration()));
        view.setSteps(FormatUtils.format(activity.getSteps()));
        view.setType(activity.getType().name());
        view.setTitle(view.getResource(R.string.activity_title, username));
        view.setDate(FormatUtils.format(activity.getStartDate()));
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
        try {
            activity.setType(Activity.ActivityType.valueOf(type));
            asvc.updateActivityAsync(activity.getId(), activity, grant.getAuthHeader());
            view.setType(type);
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.error_update_type));
        }
    }
}
