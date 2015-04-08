package com.raik383h_group_6.healthtracmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raik383h_group_6.healthtracmobile.R;
import com.raik383h_group_6.healthtracmobile.model.Team;
import com.raik383h_group_6.healthtracmobile.model.feed.FeedModel;

import java.util.List;

public class FeedModelAdapter extends BaseAdapter {
    private List<FeedModel> fms;
    private LayoutInflater mInflater;

    public FeedModelAdapter(Context context, List<FeedModel> fms) {
        this.fms = fms;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fms.size();
    }

    @Override
    public Object getItem(int position) {
        return fms.get(position);
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
            view = mInflater.inflate(R.layout.feed_row_layout, parent, false);
            holder = new ViewHolder();
            holder.msg = (TextView) view.findViewById(R.id.msg);
            holder.date = (TextView) view.findViewById(R.id.date);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        FeedModel fm = fms.get(position);
        holder.msg.setText(fm.getMsg());
        holder.date.setText(fm.getDate());
        return view;
    }

    public class ViewHolder {
        public TextView msg, date;
    }
}
