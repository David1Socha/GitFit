package com.raik383h_group_6.healthtracmobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.content.IResources;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.view.GitFitMainActivity;

public class GitFitMainPresenter {
    public static final int AUTH = 1;
    private AccessGrant grant;
    private IResources resources;
    private ActivityNavigator nav;
    private GitFitMainActivity view;

    @Inject
    public GitFitMainPresenter(@Assisted IResources resources, @Assisted ActivityNavigator nav, @Assisted GitFitMainActivity view) {
        this.resources = resources;
        this.nav=nav;
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.grant = savedInstanceState.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
        }
    }

    public void onResume() {
        if (grant == null || grant.isExpired()) {
            nav.openAuthentication(AUTH);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT), grant);
    }

    public void onActivityResult(int requestCode, int resultCode, Bundle data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case AUTH:
                    grant = (AccessGrant) data.getParcelable(resources.getString(R.string.EXTRA_ACCESS_GRANT));
                    break;
                default:
                    break;
            }

        }
    }

}
