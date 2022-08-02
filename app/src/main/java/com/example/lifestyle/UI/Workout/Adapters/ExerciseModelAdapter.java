package com.example.lifestyle.UI.Workout.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ExerciseModel;
import com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram.BottomSheetExerciseFragment;
import com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram.WorkoutPrewiewActivity;

import java.util.ArrayList;

public class ExerciseModelAdapter extends ArrayAdapter<ExerciseModel> {
    private Context mContext;
    private int mResource;
    private ArrayList<ExerciseModel> mObjects;
    LinearLayout exerciseLinearLayout;

    public ExerciseModelAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ExerciseModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.img_workout_preview);
        TextView txtName = convertView.findViewById(R.id.txt_workout_preview_name);
        TextView txtTime = convertView.findViewById(R.id.txt_workout_preview_time);
        TextView txtRest = convertView.findViewById(R.id.txt_workout_preview_rest);
        LinearLayout restLinearLayout = convertView.findViewById(R.id.linear_layout_rest);
        LinearLayout exerciseLinearLayout = convertView.findViewById(R.id.qwerty);

        imageView.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getName());
        if(getItem(position).getTime_second()>0 && getItem(position).getTime_second()<10)
            txtTime.setText("00:0"+getItem(position).getTime_second());
        else if(getItem(position).getTime_second()>=10)
            txtTime.setText("00:"+getItem(position).getTime_second());
        else
            txtTime.setText(getItem(position).getCount()+" раз");

        txtRest.setText("00:10 Отдых");

        if(position+1>=mObjects.size())
            restLinearLayout.setVisibility(View.GONE);

        exerciseLinearLayout.setOnClickListener(view -> {
            BottomSheetExerciseFragment bottomSheet = new BottomSheetExerciseFragment();
            Bundle mArg = new Bundle();
            mArg.putSerializable(ExerciseModel.class.getSimpleName(), mObjects.get(position));
            bottomSheet.setArguments(mArg);
            bottomSheet.show(((WorkoutPrewiewActivity)mContext).getSupportFragmentManager(),bottomSheet.getTag());
        });

        return convertView;
    }
}
