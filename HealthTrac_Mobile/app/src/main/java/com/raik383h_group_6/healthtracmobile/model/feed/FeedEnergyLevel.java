package com.raik383h_group_6.healthtracmobile.model.feed;

import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;

import java.util.Date;

public class FeedEnergyLevel extends FeedModel {

    private EnergyLevel energyLevel;

    public FeedEnergyLevel(String msg, Date date, IActivityNavigator nav, EnergyLevel energyLevel) {
        super(msg, date, nav);
        this.energyLevel = energyLevel;
    }

    @Override
    public void onClick() {
        //nav.openEnergyLevelDetail(energyLevel);
    }
}
