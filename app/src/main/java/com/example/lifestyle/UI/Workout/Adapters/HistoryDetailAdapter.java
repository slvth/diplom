package com.example.lifestyle.UI.Workout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.example.lifestyle.Models.HistoryFoodModel;

import java.util.ArrayList;

public class HistoryDetailAdapter extends ArrayAdapter<HistoryFoodModel>  {

    private Context mContext;
    private int mResource;

    public HistoryDetailAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HistoryFoodModel> historyList) {
        super(context, resource, historyList);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);
        ImageView imageView = convertView.findViewById(R.id.img_history);
        TextView txtDate = convertView.findViewById(R.id.txt_historyDate);
        TextView txtWorkout = convertView.findViewById(R.id.txt_historyWorkout);
        TextView txtTime = convertView.findViewById(R.id.txt_historyTime);
        TextView txtCalorie = convertView.findViewById(R.id.txt_historyCalorie);

        txtDate.setText(getItem(position).getDate());
        txtCalorie.setText("");
        txtWorkout.setText(String.valueOf(getItem(position).getCalorieTotal())+" Кал.");
        txtTime.setText("");
        return convertView;
    }
}
