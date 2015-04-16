package com.raik383h_group_6.healthtracmobile.view.fragment;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.view.BaseView;


public abstract class BaseFragment extends Fragment implements BaseView {
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
        return super.getActivity().getIntent().getStringExtra(key);
    }

    @Override
    public long getLongExtra(String key) {
        return super.getActivity().getIntent().getLongExtra(key, -1l);
    }

    @Override
    public Parcelable getParcelableExtra(String key) {
        return super.getActivity().getIntent().getParcelableExtra(key);
    }

    @Override
    public String getPref(String key) {
        return super.getActivity().getSharedPreferences(getString(R.string.shared_prefs), super.getActivity().MODE_PRIVATE).getString(key, null);
    }

    @Override
    public void setPref(String key, String val) {
        SharedPreferences.Editor editor = super.getActivity().getSharedPreferences(getString(R.string.shared_prefs), super.getActivity().MODE_PRIVATE).edit();
        editor.putString(key, val);
        editor.apply();
    }

    @Override
    public void clearPrefs() {
        super.getActivity().getSharedPreferences(getString(R.string.shared_prefs), super.getActivity().MODE_PRIVATE).edit().clear().commit();
    }
}
