package com.raik383h_group_6.healthtracmobile.view.fragment;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.presenter.BasePresenter;
import com.raik383h_group_6.healthtracmobile.view.BaseView;

import roboguice.activity.RoboActionBarActivity;

public abstract class BaseActivity extends RoboActionBarActivity implements BaseView {

    private Menu menu;

    public abstract BasePresenter getPresenter();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                onClickMenuLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onClickMenuLogout() {
        getPresenter().onClickMenuLogout();
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
    public long getLongExtra(String key) {
        return getIntent().getLongExtra(key, -1l);
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
