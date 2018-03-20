package com.mani.conzumex.assignment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.mani.conzumex.assignment.R;
import com.mani.conzumex.assignment.modal.ActivityData;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityListViewHolder> {
    List<ActivityData> ActivityDataArrayList = new ArrayList<ActivityData>();
    Context context;

    public ActivityAdapter(String details) {
        ActivityData ActivityData = new ActivityData();
        ActivityData.setActivityName(details);
        ActivityDataArrayList.add(ActivityData);
    }

    public ActivityAdapter(ArrayList<ActivityData> ActivityDataArrayList, Context context) {
        this.ActivityDataArrayList = ActivityDataArrayList;
        this.context = context;
    }

    @Override
    public ActivityListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardlayout, parent, false);
        ActivityListViewHolder ActivityListViewHolder = new ActivityListViewHolder(view, context);
        return ActivityListViewHolder;
    }

    @Override
    public void onBindViewHolder(ActivityListViewHolder holder, final int position) {
        final ActivityData td = ActivityDataArrayList.get(position);
        holder.activityName.setText("ActivityName :- "+td.getActivityName());
        holder.activityEndTime.setText("Activity End Time :- "+td.getActivityEndTimeDate());
        holder.activityStartTime.setText("Activity Start Time :- "+td.getActivityStartTimeDate());
        holder.activityIntensity.setText("ActivityIntensity :- "+td.getActivityIntensity());
        String type = td.getActivityIntensity();
        int color = 0;
        if (type.matches("Medium")) {
            color = Color.parseColor("#FFFF00");
        } else if (type.matches("Hard")) {
            color = Color.parseColor("#FF0000");
        } else {
            color = Color.parseColor("#00FF00");
        }
        ((GradientDrawable) holder.intensityColor.getBackground()).setColor(color);
    }


    @Override
    public int getItemCount() {
        return ActivityDataArrayList.size();
    }

    public class ActivityListViewHolder extends RecyclerView.ViewHolder {
        TextView activityName, activityEndTime,activityStartTime,activityIntensity;
        ImageButton intensityColor;
        
        public ActivityListViewHolder(View view, final Context context) {
            super(view);
            activityName = (TextView) view.findViewById(R.id.activityName);
            activityIntensity = (TextView) view.findViewById(R.id.activityIntensity);
            activityStartTime = (TextView) view.findViewById(R.id.activityStartTimeDate);
            activityEndTime = (TextView) view.findViewById(R.id.activityEndTimeDate);
            intensityColor = (ImageButton) view.findViewById(R.id.typeCircle);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
}
