package com.raik383h_group_6.healthtracmobile.presenter;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.application.IActivityNavigator;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.service.FormatUtils;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncEnergyLevelService;
import com.raik383h_group_6.healthtracmobile.service.api.async.IAsyncMealService;
import com.raik383h_group_6.healthtracmobile.view.BaseView;
import com.raik383h_group_6.healthtracmobile.view.CreateEnergyLevelView;
import com.raik383h_group_6.healthtracmobile.view.CreateMealView;

import java.util.Date;

public class CreateMealPresenter extends BasePresenter {

    private IAsyncMealService ms;
    private IActivityNavigator nav;
    private CreateMealView view;
    private AccessGrant grant;

    @Inject
    public CreateMealPresenter(IAsyncMealService ms, @Assisted IActivityNavigator nav, @Assisted CreateMealView view) {
        this.ms = ms;
        this.nav = nav;
        this.view = view;
        this.grant = (AccessGrant) view.getParcelableExtra(view.getResource(R.string.EXTRA_ACCESS_GRANT));
    }

    public void onCreate() {
        if (grant == null || grant.isExpired()) {
            view.displayMessage(view.getResource(R.string.no_grant_message));
            nav.finishActivity();
        }
    }

    @Override
    protected BaseView getView() {
        return view;
    }

    @Override
    protected IActivityNavigator getNav() {
        return nav;
    }

    public void onClickCreateMeal() {
        String calsStr = view.getCalories();
        if (validateFields(calsStr)) {
            int calories = FormatUtils.parseInt(calsStr);
            Meal m = new Meal();
            m.setCalories(calories);
            m.setDateCreated(new Date());
            m.setUserID(grant.getId());
            createMeal(m);
        }
    }

    private void createMeal(Meal m) {
        try {
            ms.createMeal(m, grant.getAuthHeader());
        } catch (Exception e) {
            view.displayMessage(view.getResource(R.string.error_create_meal));
        }
         nav.finishCreateMeal();
    }

    private boolean validateFields(String caloriesStr) {
        boolean valid = !caloriesStr.equals("");
        if(!valid) {
            view.setCaloriesError(view.getResource(R.string.empty_field_error));
            view.displayMessage(view.getResource(R.string.invalid_field_message));
        }
        return valid;
    }

}
