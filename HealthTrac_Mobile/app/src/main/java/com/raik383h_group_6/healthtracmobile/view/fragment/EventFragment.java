package com.raik383h_group_6.healthtracmobile.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.EventPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.EventView;

import roboguice.inject.InjectView;

public class EventFragment extends BaseFragment implements EventView {
    LinearLayout eventLayout;
    @InjectView(R.id.button_start_activity)
    private Button createActivityButton;
    @InjectView(R.id.button_create_activity)
    private Button createActivityManuallyButton;
    @InjectView(R.id.button_create_goal)
    private Button createGoalButton;
    @InjectView(R.id.button_create_energy_level)
    private Button createEnergyLevelButton;
    @InjectView(R.id.button_create_meal)
    private Button createMealButton;
    @Inject
    PresenterFactory presenterFactory;
    private EventPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IActivityNavigator nav = new ActivityNavigator(super.getActivity());
        eventLayout = (LinearLayout) inflater.inflate(R.layout.activity_event,container,false);
        presenter = presenterFactory.create(nav, this);
        return eventLayout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateActivity(v);
            }
        });
        createActivityManuallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateActivityManually(v);
            }
        });
        createGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateGoal(v);
            }
        });
        createEnergyLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateEnergyLevel(v);
            }
        });
        createMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateMeal(v);
            }
        });
    }

    public void onClickCreateActivity(View v) {
        presenter.onClickCreateActivity();
    }

    public void onClickCreateActivityManually(View v) {
        presenter.onClickCreateActivityManually();
    }

    public void onClickCreateGoal(View v) {
        presenter.onClickCreateGoal();
    }

    public void onClickCreateEnergyLevel(View v) {
        presenter.onClickCreateEnergyLevel();
    }

    public void onClickCreateMeal(View v) {
        presenter.onClickCreateMeal();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
