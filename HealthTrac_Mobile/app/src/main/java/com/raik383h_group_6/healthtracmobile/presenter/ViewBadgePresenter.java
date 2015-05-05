package com.raik383h_group_6.healthtracmobile.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.model.User;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserBadgeService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncUserService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.ViewBadgeView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewBadgePresenter extends BasePresenter {

    private IAsyncUserService usvc;
    private IAsyncUserBadgeService ubsvc;
    private ViewBadgeView view;
    private IActivityNavigator nav;
    private AccessGrant grant;
    private Badge badge;

    @Inject
    public ViewBadgePresenter(IAsyncUserService usvc, IAsyncUserBadgeService ubsvc, @Assisted IActivityNavigator nav, @Assisted ViewBadgeView view) {
        this.usvc = usvc;
        this.ubsvc = ubsvc;
        this.view = view;
        this.nav = nav;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
        this.badge = (Badge) view.getParcelableExtra(view.getResource(R.string.EXTRA_BADGE));
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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User u = (User) parent.getAdapter().getItem(position);
        nav.openViewUser(u, grant);
    }

    public void onResume() {
        populateFields();
    }

    private void populateFields() {
        view.setField(badge.getField().name().toLowerCase());
        view.setThreshold(FormatUtils.format(badge.getThreshold()));
        view.setBadgeName(badge.getName());
        setBadgeHolders();
    }

    private void setBadgeHolders() {
        try {
            List<UserBadge> ubs = ubsvc.getUserBadges(badge.getId(), grant.getAuthHeader());
            List<User> users = usvc.getUsersAsync(grant.getAuthHeader());
            List<User> holders = new ArrayList<>();
            for (User u : users) {
                for (UserBadge ub : ubs) {
                    if (ub.getUserID().equals(u.getId())) {
                        holders.add(u);
                        break;
                    }
                }
            }
            view.setBadgeHolders(holders);
        } catch (InterruptedException | ExecutionException ignored) {
        }
    }
}
