package com.raik383h_group_6.healthtracmobile.view.activity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.raik383h_group_6.healthtracmobile.R;

import roboguice.activity.RoboActionBarActivity;

public abstract class CustomRoboActionBarActivity extends RoboActionBarActivity {

    private Menu menu;

    public abstract void onMenuLogout();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLogoutEnabled(boolean enabled) {
        MenuItem logout = menu.findItem(R.id.logout);
        logout.setVisible(enabled);
    }

    public void setLoginEnabled(boolean enabled) {
        MenuItem login = menu.findItem(R.id.login);
        login.setVisible(enabled);
    }
}
