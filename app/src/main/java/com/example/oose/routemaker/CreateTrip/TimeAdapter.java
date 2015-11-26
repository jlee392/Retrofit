package com.example.oose.routemaker.CreateTrip;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.oose.routemaker.R;

import java.util.List;


public class TimeAdapter extends BaseAdapter {

    private final List<TimeItem> items;
    private final Context context;

    public TimeAdapter(Context context, List<TimeItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderTime holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.time_item, null);
            holder = new ViewHolderTime();
            holder.startTime = (TextView) convertView.findViewById(R.id.startTime_text);
            holder.endTime = (TextView) convertView.findViewById(R.id.endTime_text);
//            holder.startButton = (Button) convertView.findViewById(R.id.startTime_button);
//            holder.endButton = (Button) convertView.findViewById(R.id.endTime_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderTime) convertView.getTag();
        }
        holder.startTime.setText((CharSequence) items.get(position).getStartTime());
        holder.endTime.setText((CharSequence) items.get(position).getEndTime());
        return convertView;
    }

    static class ViewHolderTime {
        TextView startTime;
//        Button startButton;
        TextView endTime;
//        Button endButton;
    }
}