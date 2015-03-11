package com.raik383h_group_6.healthtracmobile.view.activity;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import roboguice.activity.RoboActionBarActivity;

public abstract class BaseActivity extends RoboActionBarActivity implements BaseView {

    private Menu menu;

    public abstract BasePresenter getPresenter();

    public void onMenuLogout() {
        getPresenter().onClickLogout();
    }

    public void onMenuLogin() {
        getPresenter().onClickLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                onMenuLogout();
                return true;
            case R.id.login:
                onMenuLogin();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setLogoutEnabled(boolean enabled) {
        MenuItem logout = menu.findItem(R.id.logout);
        logout.setVisible(enabled);
    }

    @Override
    public void setLoginEnabled(boolean enabled) {
        MenuItem login = menu.findItem(R.id.login);
        login.setVisible(enabled);
    }

    @Override
    public String getResource(int id) {
        return getResources().getString(id);
    }

    @Override
    public String getResource(int id, Object... params) {
        return getResources().getString(id, params);
    }

    @Override
    public String getStringExtra(String key) {
        return getIntent().getStringExtra(key);
    }

    @Override
    public Parcelable getParcelableExtra(String key) {
        return getIntent().getParcelableExtra(key);
    }

    @Override
    public String getPref(String key) {
        return getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).getString(key, null);
    }

    @Override
    public void setPref(String key, String val) {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit();
        editor.putString(key, val);
        editor.apply();
    }

    @Override
    public void clearPrefs() {
        getSharedPreferences(getString(R.string.shared_prefs), MODE_PRIVATE).edit().clear().commit();
    }
}
