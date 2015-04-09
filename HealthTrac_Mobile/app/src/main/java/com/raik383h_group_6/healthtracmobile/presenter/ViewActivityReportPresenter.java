package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.ActivityReport;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewActivityReportView;

public class ViewActivityReportPresenter extends BasePresenter {

    private AccessGrant grant;
    private ViewActivityReportView view;
    private IActivityNavigator nav;
    private ActivityReport ar;
    private String username;

    public ViewActivityReportPresenter(@Assisted IActivityNavigator nav, @Assisted ViewActivityReportView view) {
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.ar = (ActivityReport) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACTIVITY_REPORT));
        this.username = view.getStringExtra(view.getResource(R.string.EXTRA_USERNAME));
    }

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setTitle(view.getResource(R.string.activity_report_title, username, FormatUtils.format(ar.getDate())));
        view.setDistance(String.format("%.2f", ar.getDistance()));
        view.setDuration(String.format("%.2f", ar.getDuration()));
        view.setSteps(String.valueOf(ar.getSteps()));
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
}
