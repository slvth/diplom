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
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.Models.WorkoutModel;

import java.util.ArrayList;

public class WorkoutModelAdapter extends ArrayAdapter<WorkoutModel> {
    private Context mContext;
    private int mResource;

    public WorkoutModelAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WorkoutModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);
        ImageView imageView = convertView.findViewById(R.id.img_workout_program);
        TextView txtName = convertView.findViewById(R.id.tv_workout_program1);
        TextView txtMinCal = convertView.findViewById(R.id.tv_workout_program2);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());
        int user_id =  new UserModel().getCurrentUser(mContext).getUser_id();

        if(databaseHelper.isDoneWorkout2(getItem(position),user_id))
            imageView.setImageResource(R.drawable.ic_check_mark);
        else
            imageView.setImageResource(R.drawable.ic_right_arrow);

        txtName.setText(getItem(position).getName());
        txtMinCal.setText(getItem(position).getTime()+" мин., "+getItem(position).getCalorie()+" кал.");

        return convertView;
    }
}
