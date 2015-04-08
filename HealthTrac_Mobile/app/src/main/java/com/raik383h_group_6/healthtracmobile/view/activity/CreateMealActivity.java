package com.raik383h_group_6.healthtracmobile.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.inject.Inject;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.ActivityNavigator;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.presenter.CreateGoalPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.CreateMealPresenter;
import com.raik383h_group_6.healthtracmobile.presenter.PresenterFactory;
import com.raik383h_group_6.healthtracmobile.view.CreateGoalView;
import com.raik383h_group_6.healthtracmobile.view.CreateMealView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_create_goal)
public class CreateMealActivity extends BaseActivity implements CreateMealView {
    @InjectView(R.id.calorie_edittext)
    EditText calEditText;
    @Inject
    private PresenterFactory presenterFactory;
    private CreateMealPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Bundle extras = getIntent().getExtras();
        IActivityNavigator nav = new ActivityNavigator(this);
        presenter= presenterFactory.create(nav, this);
        presenter.onCreate();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    public void onClickCreateMeal(View v) {
        presenter.onClickCreateMeal();
    }

    @Override
    public String getCalories() {
        return calEditText.getText().toString();
    }

    @Override
    public void setCaloriesError(String msg) {
        calEditText.setError(msg);
    }

    @Override
    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
