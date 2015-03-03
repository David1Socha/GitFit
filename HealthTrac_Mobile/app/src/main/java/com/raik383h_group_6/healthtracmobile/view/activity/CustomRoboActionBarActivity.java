package com.raik383h_group_6.healthtracmobile.view.activity;

import android.view.MenuItem;
import roboguice.activity.RoboActionBarActivity;

public class CustomRoboActionBarActivity extends RoboActionBarActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
