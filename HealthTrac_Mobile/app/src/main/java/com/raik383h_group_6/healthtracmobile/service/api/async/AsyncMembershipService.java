package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.client.Response;

public class AsyncMembershipService implements IAsyncMembershipService {

    private MembershipService service;

    @Inject
    public AsyncMembershipService(MembershipService service) {
        this.service = service;
    }

    @Override
    public Membership getMembershipAsync(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Membership>() {
            @Override
            protected Membership doInBackground(Void... params) {
                try {
                    return service.getMembership(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public Membership getMembershipAsync(final String userId, final long teamId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Membership>() {
            @Override
            protected Membership doInBackground(Void... params) {
                try {
                    return service.getMembership(userId, teamId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Membership> getMembershipsAsync(final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Membership>>() {
            @Override
            protected List<Membership> doInBackground(Void... params) {
                try {
                    return service.getMemberships(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Membership> getMembershipsAsync(final String userId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Membership>>() {
            @Override
            protected List<Membership> doInBackground(Void... params) {
                try {
                    return service.getMemberships(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Membership> getMembershipsAsync(final long teamId, final String auth) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Membership>>() {
            @Override
            protected List<Membership> doInBackground(Void... params) {
                try {
                    return service.getMemberships(teamId, auth);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public void updateMembershipAsync(final long id, final Membership membership, final String token) throws Exception {
        final Exception[] errs = new Exception[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    service.updateMembership(id, membership, token);
                } catch (Exception ex) {
                    errs[0] = ex;
                }
                return null;
            }
        }.execute().get();
        if (errs[0] != null) {
            throw errs[0];
        }
    }

    @Override
    public Membership createMembershipAsync(final Membership membership, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Membership>() {
            @Override
            protected Membership doInBackground(Void... params) {
                try {
                    return service.createMembership(membership, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
