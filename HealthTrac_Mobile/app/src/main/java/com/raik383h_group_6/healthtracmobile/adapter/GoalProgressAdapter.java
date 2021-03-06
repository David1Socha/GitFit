package com.raik383h_group_6.healthtracmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Goal;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.feed.GoalProgress;

import java.util.List;

public class GoalProgressAdapter extends BaseAdapter {

    private List<GoalProgress> progresses;
    private LayoutInflater mInflater;

    public GoalProgressAdapter(Context context, List<GoalProgress> progresses) {
        this.progresses = progresses;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return progresses.size();
    }

    @Override
    public Object getItem(int position) {
        return progresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null) {
            view = mInflater.inflate(R.layout.goal_progress_row_layout, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.goal_name);
            holder.progress = (TextView) view.findViewById(R.id.progress);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        GoalProgress p = progresses.get(position);
        holder.name.setText(p.getGoalName());
        holder.progress.setText(p.getProgress());
        return view;
    }

    public class ViewHolder {
        public TextView name, progress;
    }
}
