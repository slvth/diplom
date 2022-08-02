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
import com.example.lifestyle.Models.TotalHistoryWorkoutModel;

import java.util.ArrayList;


public class HistoryWorkoutAdapter extends ArrayAdapter<TotalHistoryWorkoutModel> {
    private final Context mContext;
    private final int mResource;

    public HistoryWorkoutAdapter(@NonNull Context context, int resource,
                                 @NonNull ArrayList<TotalHistoryWorkoutModel> historyList) {
        super(context, resource, historyList);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);
        //иницилизация элементов интерфейса
        ImageView imageView = convertView.findViewById(R.id.img_history);
        TextView txtDate = convertView.findViewById(R.id.txt_historyDate);
        TextView txtWorkout = convertView.findViewById(R.id.txt_historyWorkout);
        TextView txtTime = convertView.findViewById(R.id.txt_historyTime);
        TextView txtCalorie = convertView.findViewById(R.id.txt_historyCalorie);

        //передача значений в элементы интерфейса
        //imageView.setImageResource(getItem(position).get);
        txtDate.setText(getItem(position).getDate());
        txtWorkout.setText(getItem(position).getName());
        if(getItem(position).getTime_second()>=10)
            txtTime.setText(getItem(position).getTime_second() +" сек.");
        else
            txtTime.setText(getItem(position).getTime_second() +" мин.");
        txtCalorie.setText(getItem(position).getCalorie() +" Кал.");

        //txtCount.setText(getItem(position).getCount_exercise() +" упражнений");
        return convertView;
    }
}
