package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.example.lifestyle.UI.MainScreen;
import com.example.lifestyle.UI.Workout.Adapters.WorkoutModelAdapter;
import com.example.lifestyle.UI.Workout.Custom.DebounceClickListener;

import java.util.ArrayList;

public class MyProgramFragment extends Fragment {
    ListView mlistViewMyProgram;
    DatabaseHelper databaseHelper; //база данных
    ProgramModel program; //текущая программа
    int current_user_id; //id текущего пользователя

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_program, container, false);

        mlistViewMyProgram = v.findViewById(R.id.my_program_list2);
        databaseHelper = DatabaseHelper.getInstance(getContext());
        current_user_id = new UserModel().getCurrentUser(v.getContext()).getUser_id();
        program = databaseHelper.getProgramUser(current_user_id);

        getCurrentProgramInfo(); //информация о текущей программе (заголовок списка)
        getWorkoutList(); //список тренировок текущей программы (тело списка)

        return v;
    }

    private void getCurrentProgramInfo(){
        View header = getLayoutInflater().inflate(R.layout.header_listview_workout, null);

        TextView txtLevel = header.findViewById(R.id.level_headerMyProgram);
        TextView txtLevelName = header.findViewById(R.id.levelname_headerMyProgram);
        TextView txtComplete = header.findViewById(R.id.complete_headerMyProgram);
        ImageView imgLevel = header.findViewById(R.id.img_headerMyProgram);

        int levelValue = databaseHelper.getLevelViaProgram(program.getProgram_id());
        int completeWorkout = databaseHelper.getCountCompleteWorkouts2(current_user_id);
        int countWorkout = databaseHelper.getCountWorkoutsInPrograms(current_user_id);

        String programName = program.getProgramName(getContext());
        String levelValueString = "Уровень " + levelValue;
        String countCompleteWorkout = "Выполнено: " + completeWorkout + "/" + countWorkout;
        int imageProgram = program.getImage();

        txtLevel.setText(levelValueString);
        txtLevelName.setText(programName);
        txtComplete.setText(countCompleteWorkout);
        imgLevel.setImageResource(imageProgram);

        mlistViewMyProgram.addHeaderView(header, null, false);

        //переход на следующий уровень
        if (countWorkout == completeWorkout) {
            Toast.makeText(getContext(), "Вы выполнили программу " + programName + ", поздравляем!", Toast.LENGTH_SHORT).show();
            ((MainScreen) getActivity()).openSplashScreenNextProgram(program);
        }
    }

    private void getWorkoutList() {
        ArrayList<WorkoutModel> workoutList = program.getWorkouts();
        WorkoutModelAdapter modelAdapter =
                new WorkoutModelAdapter(getActivity(), R.layout.item_workouts_myprogram, workoutList);

        mlistViewMyProgram.setAdapter(modelAdapter);

        mlistViewMyProgram.setOnItemClickListener(new DebounceClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean isDoneWorkout = databaseHelper.isDoneWorkout2(workoutList.get(i - 1), current_user_id);
                ((MainScreen) getActivity()).openWorkoutPrewiewActivity(workoutList.get(i - 1), isDoneWorkout);
            }
        }));
    }
}

