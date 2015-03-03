package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.service.api.MembershipService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.client.Response;

public class AsyncMembershipService implements IAsyncMembershipService {

    private MembershipService service;

    public AsyncMembershipService(MembershipService service) {
        this.service = service;
    }

    @Override
    public Membership getMembershipAsync(long id, String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Membership getMembershipAsync(String userId, long teamId, String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Membership> getMembershipsAsync( String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Membership> getMembershipsAsync(String userId, String token) {
        throw new UnsupportedOperationException();
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
    public Exception updateMembershipAsync(final long id, final Membership membership, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    service.updateMembership(id, membership, token);
                    return null;
                } catch (Exception e) {
                    return e;
                }

            }
        }.execute().get();
    }

    @Override
    public Membership createMembershipAsync( final Membership membership, final String token) throws ExecutionException, InterruptedException {
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
