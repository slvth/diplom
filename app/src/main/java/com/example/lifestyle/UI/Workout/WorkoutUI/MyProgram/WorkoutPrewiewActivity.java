package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ExerciseModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.example.lifestyle.UI.Workout.Adapters.ExerciseModelAdapter;

import java.util.ArrayList;

public class WorkoutPrewiewActivity extends AppCompatActivity {
    ListView mlistViewExercise;
    WorkoutModel workout;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_prewiew);

        ArrayList<ExerciseModel> exerciseList;
        TextView txt_desc_workout_preview = (TextView) findViewById(R.id.txt_desc_workout_preview);
        TextView txt_min_workout_preview = (TextView) findViewById(R.id.txt_min_workout_preview);
        TextView txt_cal_workout_preview = (TextView) findViewById(R.id.txt_cal_workout_preview);
        TextView txt_name_workout_preview = (TextView) findViewById(R.id.txt_name_workout_preview);
        TextView txt_workout_preview_time = (TextView) findViewById(R.id.txt_workout_preview_time);
        mlistViewExercise = findViewById(R.id.workout_preview_list);

        btnStart = findViewById(R.id.btnStartWorkout);
        boolean isDoneWorkout = getIntent().getBooleanExtra("isDone",true);
        btnStart.setVisibility(isDoneWorkout ? View.GONE : View.VISIBLE );

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            workout = (WorkoutModel) arguments.getSerializable(WorkoutModel.class.getSimpleName());
            exerciseList = workout.getExercices();

            String desc = workout.getDesc().substring(0, 1).toUpperCase() + workout.getDesc().substring(1);
            String time = workout.getTime()+" МИН";
            String calorie = workout.getCalorie()+" КАЛОРИЙ";

            txt_desc_workout_preview.setText(desc);
            txt_min_workout_preview.setText(time);
            txt_cal_workout_preview.setText(calorie);
            txt_name_workout_preview.setText("Упражнения в "+workout.getName());

            ExerciseModelAdapter modelAdapter =
                    new ExerciseModelAdapter(this, R.layout.item_workout_preview, exerciseList);
            mlistViewExercise.setAdapter(modelAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void GoBackWorkoutPrewiew(View view) {
        onBackPressed();
    }

    public void exerciseStarted(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra(WorkoutModel.class.getSimpleName(), workout);
        startActivity(intent);
        finish();
    }
}