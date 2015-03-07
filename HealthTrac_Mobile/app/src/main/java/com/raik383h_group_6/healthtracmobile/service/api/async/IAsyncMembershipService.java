package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Membership;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

public interface IAsyncMembershipService {
    Membership getMembershipAsync( long id, String token);

    Membership getMembershipAsync( String userId,  long teamId, String token);

    List<Membership> getMembershipsAsync( String token);

    List<Membership> getMembershipsAsync(String userId, String token);

    List<Membership> getMembershipsAsync( long teamId,  String token) throws ExecutionException, InterruptedException;

    void updateMembershipAsync(long id, Membership membership, String token) throws Exception;

    Membership createMembershipAsync( Membership membership, String token) throws ExecutionException, InterruptedException;
}
