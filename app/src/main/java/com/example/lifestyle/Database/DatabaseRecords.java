package com.example.lifestyle.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ActivityModel;
import com.example.lifestyle.Models.ExerciseModel;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.HistoryWorkoutModel;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.LevelProgramModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.ProgramWorkoutModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.Models.WorkoutExerciseModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class DatabaseRecords {
    Context context;
    DatabaseHelper databaseHelper;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public  void data(Context fcontext){
        context = fcontext;
        databaseHelper=DatabaseHelper.getInstance(context);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        UserModel user = new UserModel(1,"Joker", 19, 180, 60,"joker@gmail.com","123123");
        user.setProgram_id(2);
        addRecordsUser(user);

        ArrayList<LevelModel> levels = new ArrayList<>();
        levels.add(new LevelModel(1,"Новичок",1, R.drawable.easy));
        levels.add(new LevelModel(2,"Бывалый",2, R.drawable.normal));
        levels.add(new LevelModel(3,"Мастер",3, R.drawable.hard));
        addRecordsLevels(levels);


        ArrayList<ProgramModel> programs = new ArrayList<>();
        //Уровень Новичок
        programs.add(new ProgramModel(1,"Новичок",1,2,"Уровень новичок", R.drawable.easy));
        programs.add(new ProgramModel(2,"Новичок",2,2,"Уровень новичок", R.drawable.easy));
        programs.add(new ProgramModel(3,"Новичок",3,2,"Уровень новичок", R.drawable.easy));
        programs.add(new ProgramModel(4,"Новичок",4,2,"Уровень новичок", R.drawable.easy));
        programs.add(new ProgramModel(5,"Новичок",5,2,"Уровень новичок", R.drawable.easy));
        //Уровень Бывалый
        programs.add(new ProgramModel(6,"Бывалый",1,2,"Уровень бывалого", R.drawable.normal));
        programs.add(new ProgramModel(7,"Бывалый",2,2,"Уровень бывалого", R.drawable.normal));
        programs.add(new ProgramModel(8,"Бывалый",3,2,"Уровень бывалого", R.drawable.normal));
        programs.add(new ProgramModel(9,"Бывалый",4,2,"Уровень бывалого", R.drawable.normal));
        programs.add(new ProgramModel(10,"Бывалый",5,2,"Уровень бывалого", R.drawable.normal));
        //Уровень Мастер
        programs.add(new ProgramModel(11,"Мастер",1,2,"Уровень мастера", R.drawable.hard));
        programs.add(new ProgramModel(12,"Мастер",2,2,"Уровень мастера", R.drawable.hard));
        programs.add(new ProgramModel(13,"Мастер",3,2,"Уровень мастера", R.drawable.hard));
        programs.add(new ProgramModel(14,"Мастер",4,2,"Уровень мастера", R.drawable.hard));
        programs.add(new ProgramModel(15,"Мастер",5,2,"Уровень мастера", R.drawable.hard));
        addRecordsPrograms(programs);

        ArrayList<WorkoutModel> workouts = new ArrayList<>();
        workouts.add(new WorkoutModel(1,"Тренировка 1",2,150,0, "Тренировка - Повторения"));
        workouts.add(new WorkoutModel(2,"Тренировка 2",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(3,"Тренировка 3",2,170,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(4,"Тренировка 4",2,160,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(5,"Тренировка 5",2,200,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(6,"Тренировка 6",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(7,"Тренировка 7",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(8,"Тренировка 8",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(9,"Тренировка 9",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(10,"Тренировка 10",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(11,"Тренировка 11",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(12,"Тренировка 12",2,150,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(13,"Тренировка 13",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(14,"Тренировка 14",2,100,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(15,"Тренировка 15",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(16,"Тренировка 16",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(17,"Тренировка 17",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(18,"Тренировка 18",2,150,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(19,"Тренировка 19",1,150,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(20,"Тренировка 20",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(21,"Тренировка 21",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(22,"Тренировка 22",2,140,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(23,"Тренировка 23",2,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(24,"Тренировка 24",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(25,"Тренировка 25",2,200,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(26,"Тренировка 26",2,140,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(27,"Тренировка 27",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(28,"Тренировка 28",2,200,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(29,"Тренировка 29",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(30,"Тренировка 30",5,210,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(31,"Тренировка 31",5,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(32,"Тренировка 32",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(33,"Тренировка 33",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(34,"Тренировка 34",4,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(35,"Тренировка 35",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(36,"Тренировка 36",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(37,"Тренировка 37",3,190,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(38,"Тренировка 38",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(39,"Тренировка 39",3,140,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(40,"Тренировка 40",2,110,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(41,"Тренировка 41",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(42,"Тренировка 42",2,130,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(43,"Тренировка 43",4,120,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(44,"Тренировка 44",2,150,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(45,"Тренировка 45",9,160,0, "Тренировка - с интесивными упражнениями"));
        workouts.add(new WorkoutModel(46,"Тренировка 46",2,150,0, "Тренировка - с интесивными упражнениями"));
        addRecordsWorkout(workouts);

        ArrayList<ExerciseModel> exercises = new ArrayList<>();
        exercises.add(new ExerciseModel(1,"Приседания","Упражнение - приседания",0,3, R.drawable.img_squats,R.drawable.monkey));
        exercises.add(new ExerciseModel(2,"Прыжки","Упражнение - Прыжки",0,3, R.drawable.img_jump,R.drawable.monkey));
        exercises.add(new ExerciseModel(3,"Планка","Упражнение - Планка",0,3, R.drawable.img_planka,R.drawable.monkey));
        exercises.add(new ExerciseModel(4,"Круги ногами","Упражнение - Круги ногами",0,3, R.drawable.img_running_on_spot,R.drawable.monkey));
        exercises.add(new ExerciseModel(5,"Махи ногами","Упражнение - Махи ногами",0,20, R.drawable.img_swing_legs,R.drawable.monkey));
        exercises.add(new ExerciseModel(6,"Берпи","Упражнение - Берпи",0,20, R.drawable.img_burpee,R.drawable.monkey));
        exercises.add(new ExerciseModel(7,"Отжимание","Упражнение - Отжимание",0,20, R.drawable.img_push_ups,R.drawable.monkey));
        exercises.add(new ExerciseModel(8,"Отжимание обратным хватом","Упражнение - Отжимание обратным хватом",0,20, R.drawable.img_reverse_push_ups,R.drawable.monkey));
        addRecordsExercises(exercises);

        ArrayList<LevelProgramModel> levelPrograms = new ArrayList<>();
        levelPrograms.add(new LevelProgramModel(1,1,1));
        levelPrograms.add(new LevelProgramModel(2,1,2));
        levelPrograms.add(new LevelProgramModel(3,1,3));
        levelPrograms.add(new LevelProgramModel(4,1,4));
        levelPrograms.add(new LevelProgramModel(5,1,5));

        levelPrograms.add(new LevelProgramModel(6,2,6));
        levelPrograms.add(new LevelProgramModel(7,2,7));
        levelPrograms.add(new LevelProgramModel(8,2,8));
        levelPrograms.add(new LevelProgramModel(9,2,9));
        levelPrograms.add(new LevelProgramModel(10,2,10));

        levelPrograms.add(new LevelProgramModel(11,3,11));
        levelPrograms.add(new LevelProgramModel(12,3,12));
        levelPrograms.add(new LevelProgramModel(13,3,13));
        levelPrograms.add(new LevelProgramModel(14,3,14));
        levelPrograms.add(new LevelProgramModel(15,3,15));
        addRecordsLevelProgram(levelPrograms);

        ArrayList<ProgramWorkoutModel> programWorkout = new ArrayList<>();
        programWorkout.add(new ProgramWorkoutModel(1,1,1));
        programWorkout.add(new ProgramWorkoutModel(2,1,2));
        programWorkout.add(new ProgramWorkoutModel(3,1,3));
        programWorkout.add(new ProgramWorkoutModel(4,2,4));
        programWorkout.add(new ProgramWorkoutModel(5,2,5));
        programWorkout.add(new ProgramWorkoutModel(6,2,6));
        programWorkout.add(new ProgramWorkoutModel(7,3,7));
        programWorkout.add(new ProgramWorkoutModel(8,3,8));
        programWorkout.add(new ProgramWorkoutModel(9,4,9));
        programWorkout.add(new ProgramWorkoutModel(10,4,10));
        programWorkout.add(new ProgramWorkoutModel(11,4,11));
        programWorkout.add(new ProgramWorkoutModel(12,4,12));
        programWorkout.add(new ProgramWorkoutModel(13,5,13));
        programWorkout.add(new ProgramWorkoutModel(14,5,14));
        programWorkout.add(new ProgramWorkoutModel(15,6,15));
        programWorkout.add(new ProgramWorkoutModel(16,6,16));
        programWorkout.add(new ProgramWorkoutModel(17,6,17));
        programWorkout.add(new ProgramWorkoutModel(18,7,18));
        programWorkout.add(new ProgramWorkoutModel(19,7,19));
        programWorkout.add(new ProgramWorkoutModel(20,7,20));
        programWorkout.add(new ProgramWorkoutModel(21,8,21));
        programWorkout.add(new ProgramWorkoutModel(22,8,22));
        programWorkout.add(new ProgramWorkoutModel(23,8,23));
        programWorkout.add(new ProgramWorkoutModel(24,9,24));
        programWorkout.add(new ProgramWorkoutModel(25,9,25));
        programWorkout.add(new ProgramWorkoutModel(26,9,26));
        programWorkout.add(new ProgramWorkoutModel(27,10,27));
        programWorkout.add(new ProgramWorkoutModel(28,10,28));
        programWorkout.add(new ProgramWorkoutModel(29,10,29));
        programWorkout.add(new ProgramWorkoutModel(30,11,30));
        programWorkout.add(new ProgramWorkoutModel(31,11,31));
        programWorkout.add(new ProgramWorkoutModel(32,11,32));
        programWorkout.add(new ProgramWorkoutModel(33,12,33));
        programWorkout.add(new ProgramWorkoutModel(34,12,34));
        programWorkout.add(new ProgramWorkoutModel(35,12,35));
        programWorkout.add(new ProgramWorkoutModel(36,13,36));
        programWorkout.add(new ProgramWorkoutModel(37,13,37));
        programWorkout.add(new ProgramWorkoutModel(38,13,38));
        programWorkout.add(new ProgramWorkoutModel(39,14,39));
        programWorkout.add(new ProgramWorkoutModel(40,14,40));
        programWorkout.add(new ProgramWorkoutModel(41,14,41));
        programWorkout.add(new ProgramWorkoutModel(42,15,42));
        programWorkout.add(new ProgramWorkoutModel(43,15,43));
        programWorkout.add(new ProgramWorkoutModel(44,15,44));
        programWorkout.add(new ProgramWorkoutModel(45,15,45));
        programWorkout.add(new ProgramWorkoutModel(46,15,46));

        addRecordsProgramWorkout(programWorkout);

        ArrayList<WorkoutExerciseModel> workoutExercises = new ArrayList<>();
        workoutExercises.add(new WorkoutExerciseModel(1,1,1));
        workoutExercises.add(new WorkoutExerciseModel(2,1,2));
        workoutExercises.add(new WorkoutExerciseModel(3,1,1));
        workoutExercises.add(new WorkoutExerciseModel(4,1,3));
        workoutExercises.add(new WorkoutExerciseModel(5,2,4));
        workoutExercises.add(new WorkoutExerciseModel(6,2,5));
        workoutExercises.add(new WorkoutExerciseModel(7,2,6));
        workoutExercises.add(new WorkoutExerciseModel(8,2,7));
        workoutExercises.add(new WorkoutExerciseModel(9,2,8));
        workoutExercises.add(new WorkoutExerciseModel(10,3,4));
        workoutExercises.add(new WorkoutExerciseModel(11,3,5));
        workoutExercises.add(new WorkoutExerciseModel(12,3,6));
        workoutExercises.add(new WorkoutExerciseModel(13,3,7));
        workoutExercises.add(new WorkoutExerciseModel(14,3,8));

        workoutExercises.add(new WorkoutExerciseModel(15,4,4));
        workoutExercises.add(new WorkoutExerciseModel(16,4,5));
        workoutExercises.add(new WorkoutExerciseModel(17,4,6));
        workoutExercises.add(new WorkoutExerciseModel(18,4,7));

        workoutExercises.add(new WorkoutExerciseModel(19,5,1));
        workoutExercises.add(new WorkoutExerciseModel(20,5,2));
        workoutExercises.add(new WorkoutExerciseModel(21,5,3));
        workoutExercises.add(new WorkoutExerciseModel(22,5,4));

        workoutExercises.add(new WorkoutExerciseModel(23,6,3));
        workoutExercises.add(new WorkoutExerciseModel(24,6,4));
        workoutExercises.add(new WorkoutExerciseModel(25,6,3));
        workoutExercises.add(new WorkoutExerciseModel(26,6,4));

        workoutExercises.add(new WorkoutExerciseModel(27,7,5));
        workoutExercises.add(new WorkoutExerciseModel(28,7,3));
        workoutExercises.add(new WorkoutExerciseModel(29,7,8));
        workoutExercises.add(new WorkoutExerciseModel(30,7,2));

        workoutExercises.add(new WorkoutExerciseModel(31,8,1));
        workoutExercises.add(new WorkoutExerciseModel(32,8,3));
        workoutExercises.add(new WorkoutExerciseModel(33,8,2));
        workoutExercises.add(new WorkoutExerciseModel(34,8,3));

        workoutExercises.add(new WorkoutExerciseModel(35,9,8));
        workoutExercises.add(new WorkoutExerciseModel(36,9,7));
        workoutExercises.add(new WorkoutExerciseModel(37,9,5));
        workoutExercises.add(new WorkoutExerciseModel(38,9,6));

        workoutExercises.add(new WorkoutExerciseModel(35,10,8));
        workoutExercises.add(new WorkoutExerciseModel(36,10,7));
        workoutExercises.add(new WorkoutExerciseModel(37,10,5));
        workoutExercises.add(new WorkoutExerciseModel(38,10,6));

        workoutExercises.add(new WorkoutExerciseModel(39,11,5));
        workoutExercises.add(new WorkoutExerciseModel(40,11,4));
        workoutExercises.add(new WorkoutExerciseModel(41,11,3));
        workoutExercises.add(new WorkoutExerciseModel(42,11,2));

        workoutExercises.add(new WorkoutExerciseModel(43,12,2));
        workoutExercises.add(new WorkoutExerciseModel(44,12,1));
        workoutExercises.add(new WorkoutExerciseModel(45,12,7));
        workoutExercises.add(new WorkoutExerciseModel(46,12,6));

        workoutExercises.add(new WorkoutExerciseModel(47,13,1));
        workoutExercises.add(new WorkoutExerciseModel(48,13,2));
        workoutExercises.add(new WorkoutExerciseModel(49,13,1));
        workoutExercises.add(new WorkoutExerciseModel(50,13,3));

        workoutExercises.add(new WorkoutExerciseModel(51,14,4));
        workoutExercises.add(new WorkoutExerciseModel(52,14,5));
        workoutExercises.add(new WorkoutExerciseModel(53,14,6));
        workoutExercises.add(new WorkoutExerciseModel(54,14,7));
        workoutExercises.add(new WorkoutExerciseModel(55,14,8));

        workoutExercises.add(new WorkoutExerciseModel(56,15,4));
        workoutExercises.add(new WorkoutExerciseModel(57,15,5));
        workoutExercises.add(new WorkoutExerciseModel(58,15,6));
        workoutExercises.add(new WorkoutExerciseModel(59,15,7));
        workoutExercises.add(new WorkoutExerciseModel(60,15,8));

        workoutExercises.add(new WorkoutExerciseModel(61,16,4));
        workoutExercises.add(new WorkoutExerciseModel(62,16,5));
        workoutExercises.add(new WorkoutExerciseModel(63,16,6));
        workoutExercises.add(new WorkoutExerciseModel(64,16,7));

        workoutExercises.add(new WorkoutExerciseModel(65,17,1));
        workoutExercises.add(new WorkoutExerciseModel(66,17,2));
        workoutExercises.add(new WorkoutExerciseModel(67,17,3));
        workoutExercises.add(new WorkoutExerciseModel(68,17,4));

        workoutExercises.add(new WorkoutExerciseModel(69,18,3));
        workoutExercises.add(new WorkoutExerciseModel(70,18,4));
        workoutExercises.add(new WorkoutExerciseModel(71,18,3));
        workoutExercises.add(new WorkoutExerciseModel(72,18,4));

        workoutExercises.add(new WorkoutExerciseModel(73,19,5));
        workoutExercises.add(new WorkoutExerciseModel(74,19,3));
        workoutExercises.add(new WorkoutExerciseModel(75,19,8));
        workoutExercises.add(new WorkoutExerciseModel(76,19,2));

        workoutExercises.add(new WorkoutExerciseModel(77,20,1));
        workoutExercises.add(new WorkoutExerciseModel(78,20,3));
        workoutExercises.add(new WorkoutExerciseModel(79,20,2));
        workoutExercises.add(new WorkoutExerciseModel(80,20,3));

        workoutExercises.add(new WorkoutExerciseModel(81,21,8));
        workoutExercises.add(new WorkoutExerciseModel(82,21,7));
        workoutExercises.add(new WorkoutExerciseModel(83,21,5));
        workoutExercises.add(new WorkoutExerciseModel(84,21,6));

        workoutExercises.add(new WorkoutExerciseModel(85,22,8));
        workoutExercises.add(new WorkoutExerciseModel(86,22,7));
        workoutExercises.add(new WorkoutExerciseModel(87,22,5));
        workoutExercises.add(new WorkoutExerciseModel(88,22,6));

        workoutExercises.add(new WorkoutExerciseModel(89,23,5));
        workoutExercises.add(new WorkoutExerciseModel(90,23,4));
        workoutExercises.add(new WorkoutExerciseModel(91,23,3));
        workoutExercises.add(new WorkoutExerciseModel(92,23,2));

        workoutExercises.add(new WorkoutExerciseModel(93,24,2));
        workoutExercises.add(new WorkoutExerciseModel(94,24,1));
        workoutExercises.add(new WorkoutExerciseModel(95,24,7));
        workoutExercises.add(new WorkoutExerciseModel(96,24,6));

        workoutExercises.add(new WorkoutExerciseModel(97,25,5));
        workoutExercises.add(new WorkoutExerciseModel(98,25,1));
        workoutExercises.add(new WorkoutExerciseModel(99,25,3));

        workoutExercises.add(new WorkoutExerciseModel(100,26,2));
        workoutExercises.add(new WorkoutExerciseModel(101,26,3));
        workoutExercises.add(new WorkoutExerciseModel(102,26,7));

        workoutExercises.add(new WorkoutExerciseModel(103,27,2));
        workoutExercises.add(new WorkoutExerciseModel(104,27,3));
        workoutExercises.add(new WorkoutExerciseModel(105,27,7));

        workoutExercises.add(new WorkoutExerciseModel(106,28,2));
        workoutExercises.add(new WorkoutExerciseModel(107,28,3));
        workoutExercises.add(new WorkoutExerciseModel(108,28,7));

        workoutExercises.add(new WorkoutExerciseModel(109,29,2));
        workoutExercises.add(new WorkoutExerciseModel(110,29,1));
        workoutExercises.add(new WorkoutExerciseModel(111,29,7));

        workoutExercises.add(new WorkoutExerciseModel(112,30,2));
        workoutExercises.add(new WorkoutExerciseModel(113,30,3));
        workoutExercises.add(new WorkoutExerciseModel(114,30,7));

        workoutExercises.add(new WorkoutExerciseModel(115,31,2));
        workoutExercises.add(new WorkoutExerciseModel(116,31,3));
        workoutExercises.add(new WorkoutExerciseModel(117,31,7));

        workoutExercises.add(new WorkoutExerciseModel(118,32,2));
        workoutExercises.add(new WorkoutExerciseModel(119,32,3));
        workoutExercises.add(new WorkoutExerciseModel(120,32,7));

        workoutExercises.add(new WorkoutExerciseModel(121,33,2));
        workoutExercises.add(new WorkoutExerciseModel(122,33,3));
        workoutExercises.add(new WorkoutExerciseModel(123,33,7));

        workoutExercises.add(new WorkoutExerciseModel(124,34,2));
        workoutExercises.add(new WorkoutExerciseModel(125,34,3));
        workoutExercises.add(new WorkoutExerciseModel(126,34,2));

        workoutExercises.add(new WorkoutExerciseModel(127,35,2));
        workoutExercises.add(new WorkoutExerciseModel(128,35,3));
        workoutExercises.add(new WorkoutExerciseModel(129,35,7));



        workoutExercises.add(new WorkoutExerciseModel(130,36,1));
        workoutExercises.add(new WorkoutExerciseModel(131,36,3));
        workoutExercises.add(new WorkoutExerciseModel(132,36,7));

        workoutExercises.add(new WorkoutExerciseModel(133,37,2));
        workoutExercises.add(new WorkoutExerciseModel(134,37,3));
        workoutExercises.add(new WorkoutExerciseModel(135,37,7));

        workoutExercises.add(new WorkoutExerciseModel(136,38,2));
        workoutExercises.add(new WorkoutExerciseModel(137,38,5));
        workoutExercises.add(new WorkoutExerciseModel(138,38,7));

        workoutExercises.add(new WorkoutExerciseModel(139,39,7));
        workoutExercises.add(new WorkoutExerciseModel(140,39,3));
        workoutExercises.add(new WorkoutExerciseModel(141,39,7));

        workoutExercises.add(new WorkoutExerciseModel(142,40,2));
        workoutExercises.add(new WorkoutExerciseModel(143,40,3));
        workoutExercises.add(new WorkoutExerciseModel(144,40,7));

        workoutExercises.add(new WorkoutExerciseModel(145,41,2));
        workoutExercises.add(new WorkoutExerciseModel(146,41,5));
        workoutExercises.add(new WorkoutExerciseModel(147,41,7));

        workoutExercises.add(new WorkoutExerciseModel(148,42,2));
        workoutExercises.add(new WorkoutExerciseModel(149,42,3));
        workoutExercises.add(new WorkoutExerciseModel(150,42,7));



        workoutExercises.add(new WorkoutExerciseModel(151,43,2));
        workoutExercises.add(new WorkoutExerciseModel(152,43,3));
        workoutExercises.add(new WorkoutExerciseModel(153,43,7));

        workoutExercises.add(new WorkoutExerciseModel(154,44,2));
        workoutExercises.add(new WorkoutExerciseModel(155,44,3));
        workoutExercises.add(new WorkoutExerciseModel(156,44,7));

        workoutExercises.add(new WorkoutExerciseModel(157,45,2));
        workoutExercises.add(new WorkoutExerciseModel(158,45,8));
        workoutExercises.add(new WorkoutExerciseModel(159,45,1));

        workoutExercises.add(new WorkoutExerciseModel(160,46,3));
        workoutExercises.add(new WorkoutExerciseModel(161,46,4));
        workoutExercises.add(new WorkoutExerciseModel(162,46,5));


        addRecordsWorkoutExercise(workoutExercises);

        ArrayList<HistoryProgramModel> historyPrograms = new ArrayList<>();
        historyPrograms.add(
                new HistoryProgramModel(1, 1, 1, 0, "2022-06-05 19:30:40",""));
        historyPrograms.add(
                new HistoryProgramModel(2, 1, 2, 0, "2022-05-06 19:40:40",""));
        historyPrograms.add(
                new HistoryProgramModel(3, 1, 3, 0, "2022-04-07 19:20:40",""));
        historyPrograms.add(
                new HistoryProgramModel(4, 1, 4, 0, "2022-04-08 19:25:40",""));

        addRecordsHistoryProgram(historyPrograms);


        ArrayList<HistoryWorkoutModel> historyWorkouts = new ArrayList<>();
        historyWorkouts.add(
                new HistoryWorkoutModel(1,1,1, 1, 1,60, "2022-06-05 19:30"));
        historyWorkouts.add(
                new HistoryWorkoutModel(2,1,2, 4, 2,200, "2022-05-05 19:30"));
        historyWorkouts.add(
                new HistoryWorkoutModel(3,1,3, 8, 3,100, "2022-03-05 08:30"));
        historyWorkouts.add(
                new HistoryWorkoutModel(4,1,1, 2, 4,180, "2021-12-19 15:30"));

        addRecordsHistoryWorkout(historyWorkouts);

        ArrayList<ActivityModel> activities = new ArrayList<>();
        activities.add(new ActivityModel(1,"Бег",7, R.drawable.run, ""));
        activities.add(new ActivityModel(2,"Езда на велосипеде",3, R.drawable.bike, ""));
        activities.add(new ActivityModel(3,"Подметание",2, R.drawable.cleaning, ""));
        addRecordsActivity(activities);
    }

    private void addRecordsUser(UserModel user) {
        databaseHelper.addUser(user, "EMPTY");
    }

    private void addRecordsLevels(ArrayList<LevelModel> levels){

        for(LevelModel level : levels){
            String id_level = String.valueOf(level.getLevel_id());
            DocumentReference df=db.collection("levels").document(id_level);
            df.set(level).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });

            databaseHelper.addLevel(level);
        }
    }

    private void addRecordsPrograms(ArrayList<ProgramModel> programs){

        for(ProgramModel program : programs){
            String id_program = String.valueOf(program.getProgram_id());
            DocumentReference df=db.collection("programs").document(id_program);
            df.set(program).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });

            databaseHelper.addProgram(program);
        }
    }

    private void addRecordsWorkout(ArrayList<WorkoutModel> workouts) {

        for(WorkoutModel workout : workouts) {
            String id_workout = String.valueOf(workout.getId());
            DocumentReference df = db.collection("workouts").document(id_workout);
            df.set(workout).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addWorkout(workout);
        }
    }

    private void addRecordsExercises(ArrayList<ExerciseModel> exercises){

        for(ExerciseModel exercise : exercises){
            String id_exercise = String.valueOf(exercise.getId());
            DocumentReference df=db.collection("exercises").document(id_exercise);
            df.set(exercise).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });

            databaseHelper.addExercise(exercise);
        }
    }

    private void addRecordsActivity(ArrayList<ActivityModel> activities){

        for(ActivityModel activity : activities){
            String id_activity = String.valueOf(activity.getActivity_id());
            DocumentReference df=db.collection("activities").document(id_activity);
            df.set(activity).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });

            databaseHelper.addActivity(activity);
        }
    }

    private void addRecordsWorkoutExercise(ArrayList<WorkoutExerciseModel> workoutExercises) {

        for(WorkoutExerciseModel we : workoutExercises) {
            String id_we = String.valueOf(we.getWorkout_exercise_id());
            DocumentReference df = db.collection("workout_exercises").document(id_we);
            df.set(we).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addWorkoutExercise(we);
        }
    }

    private void addRecordsProgramWorkout(ArrayList<ProgramWorkoutModel> programWorkouts) {

        for(ProgramWorkoutModel pw : programWorkouts) {
            String id_pw = String.valueOf(pw.getProgram_workout_id());
            DocumentReference df = db.collection("program_workouts").document(id_pw);
            df.set(pw).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addProgramWorkout(pw);
        }
    }


    private void addRecordsLevelProgram(ArrayList<LevelProgramModel> levelPrograms) {

        for(LevelProgramModel lp : levelPrograms) {
            String id_lp = String.valueOf(lp.getLevel_program_id());
            DocumentReference df = db.collection("level_programs").document(id_lp);
            df.set(lp).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addLevelProgram(lp);
        }
    }

    private void addRecordsHistoryProgram(ArrayList<HistoryProgramModel> historyPrograms) {

        for(HistoryProgramModel hp : historyPrograms) {
            String id_hp = String.valueOf(hp.getHistory_program_id());
            DocumentReference df = db.collection("history_workouts").document(id_hp);
            df.set(hp).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addHistoryProgram(hp);
        }
    }

    private void addRecordsHistoryWorkout(ArrayList<HistoryWorkoutModel> historyWorkouts) {

        for(HistoryWorkoutModel hw : historyWorkouts) {
            String id_hw = String.valueOf(hw.getHistory_workout_id());
            DocumentReference df = db.collection("history_workouts").document(id_hw);
            df.set(hw).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "DocumentSnapshot successfully written!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error writing document", e);
                }
            });
            databaseHelper.addHistoryWorkout(hw);
        }
    }
}


















