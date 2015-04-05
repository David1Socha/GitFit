package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Point;
import com.raik383h_group_6.healthtracmobile.service.api.PointService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncPointService implements IAsyncPointService {

    private PointService service;

    @Inject
    public AsyncPointService(PointService service) {
        this.service = service;
    }

    @Override
    public Point getPoint(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Point>() {
            @Override
            protected Point doInBackground(Void... params) {
                try {
                    return service.getPoint(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<Point> getPoints(final long activityId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Point>>() {
            @Override
            protected List<Point> doInBackground(Void... params) {
                try {
                    return service.getPoints(activityId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public Point createPoint(final Point point, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, Point>() {
            @Override
            protected Point doInBackground(Void... params) {
                try {
                    return service.createPoint(point, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
