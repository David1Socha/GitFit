package com.raik383h_group_6.healthtracmobile.service.api.async;

import com.raik383h_group_6.healthtracmobile.model.Badge;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IAsyncEnergyLevelService {
    EnergyLevel getEnergyLevelAsync( long id, String token) throws ExecutionException, InterruptedException;

    List<EnergyLevel> getEnergyLevelsAsync(final String token) throws ExecutionException, InterruptedException;

    List<EnergyLevel> getEnergyLevelsAsync(final String userId, final String token) throws ExecutionException, InterruptedException;

    EnergyLevel createEnergyLevelAsync(final EnergyLevel energyLevel, final String token) throws ExecutionException, InterruptedException;
}
