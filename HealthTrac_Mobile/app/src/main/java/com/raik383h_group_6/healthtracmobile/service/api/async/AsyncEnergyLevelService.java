package com.raik383h_group_6.healthtracmobile.service.api.async;

import android.os.AsyncTask;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.service.api.EnergyLevelService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsyncEnergyLevelService implements IAsyncEnergyLevelService {

    private EnergyLevelService service;

    @Inject
    public AsyncEnergyLevelService(EnergyLevelService service) {
        this.service = service;
    }

    @Override
    public EnergyLevel getEnergyLevelAsync(final long id, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, EnergyLevel>() {
            @Override
            protected EnergyLevel doInBackground(Void... params) {
                try {
                    return service.getEnergyLevel(id, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<EnergyLevel> getEnergyLevelsAsync(final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<EnergyLevel>>() {
            @Override
            protected List<EnergyLevel> doInBackground(Void... params) {
                try {
                    return service.getEnergyLevels(token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public List<EnergyLevel> getEnergyLevelsAsync(final String userId, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<EnergyLevel>>() {
            @Override
            protected List<EnergyLevel> doInBackground(Void... params) {
                try {
                    return service.getEnergyLevels(userId, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }

    @Override
    public EnergyLevel createEnergyLevelAsync(final EnergyLevel energyLevel, final String token) throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, EnergyLevel>() {
            @Override
            protected EnergyLevel doInBackground(Void... params) {
                try {
                    return service.createEnergyLevel(energyLevel, token);
                } catch (Exception e) {
                    return null;
                }
            }
        }.execute().get();
    }
}
