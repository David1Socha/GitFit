package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Point;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncPointService {
    Point getPoint(long id, String token) throws ExecutionException, InterruptedException;
    List<Point> getPoints(long activityId, String token) throws ExecutionException, InterruptedException;
    Point createPoint(Point point, String token) throws ExecutionException, InterruptedException;
    List<Point> createPoints(List<Point> points, String token) throws ExecutionException, InterruptedException;
}
