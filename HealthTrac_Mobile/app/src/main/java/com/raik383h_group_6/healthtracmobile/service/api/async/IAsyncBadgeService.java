package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Badge;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncBadgeService {
    Badge getBadgeAsync( long id, String token) throws ExecutionException, InterruptedException;

    List<Badge> getBadgesAsync(final String token) throws ExecutionException, InterruptedException;
}
