package com.raik383h_group_6.healthtracmobile.view.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.raik383h_group_6.healthtracmobile.R;

import roboguice.activity.RoboActionBarActivity;

public abstract class CustomRoboActionBarActivity extends RoboActionBarActivity {

    public abstract void onMenuLogout();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
