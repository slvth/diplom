package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.ExerciseModel;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.HistoryWorkoutModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import pl.droidsonroids.gif.GifImageView;

public class ExerciseActivity extends AppCompatActivity {
    //Модельки и переменные
    WorkoutModel workout;
    ArrayList<ExerciseModel> exerciseList;
    long timeMain, timeExercise, timeRest, countTime;
    int maxCountExercise;

    //Layout
    TextView countText, nameText, nextExerciseText;
    GifImageView imageViewGifs;
    ImageButton pauseButton, continueButton;
    View mShadowView;

    //Таймер
    boolean timer1Started = false, timer2Started = true;
    Chronometer chronometer1, chronometer2;
    long pauseOffset1, pauseOffset2, mainPause;
    ProgressBar progressBar;

    UserModel currentUser;
    FirebaseUser mUser;
    FirebaseFirestore dbFirebase;
    int id_user;
    DatabaseHelper databaseHelper;
    Handler h;
    int i;

    int exitTime = 0;
    int exitCalorie = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        imageViewGifs = findViewById(R.id.img_exercise_gifs);
        nextExerciseText = findViewById(R.id.txt_next_exercise);

        countText = findViewById(R.id.txt_exercise_count);
        nameText = findViewById(R.id.txt_exercise_name);
        pauseButton = findViewById(R.id.btn_exercise_pause);
        continueButton = findViewById(R.id.btn_exercise_continue);
        progressBar = findViewById(R.id.id_progress_bar_ex);

        chronometer1 = findViewById(R.id.id_chronometer);
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer2 = findViewById(R.id.id_chronometer2);
        chronometer2.setBase(SystemClock.elapsedRealtime());

        mShadowView = findViewById(R.id.shadow_view);
        mShadowView.setVisibility(View.VISIBLE);

        currentUser = new UserModel().getCurrentUser(this);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        dbFirebase = FirebaseFirestore.getInstance();
        DocumentReference documentUser = dbFirebase.collection("users").document(mUser.getUid());

        databaseHelper = DatabaseHelper.getInstance(this);
        timeRest = 3;
        i = 0;


        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            workout = (WorkoutModel) arguments.getSerializable(WorkoutModel.class.getSimpleName());
            timeMain = workout.getTime();
            exerciseList = workout.getExercices();
            maxCountExercise = exerciseList.size();

            h = new Handler();

            Thread mainThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (; i < exerciseList.size(); i++) {
                        if (timer2Started) {
                            mainPause = 0;
                            Log.i("SALAVATT", "I = " + i);
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    mShadowView.setVisibility(View.VISIBLE);
                                    nameText.setVisibility(View.GONE);
                                }
                            });

                            h.postDelayed(firstRunnable, timeRest * 1000);
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    chronometer2.setBase(SystemClock.elapsedRealtime());
                                    chronometer1.start();
                                    chronometer2.start();
                                    timer1Started = true;
                                    nextExerciseText.setText(exerciseList.get(i).getName());
                                    nameText.setText(exerciseList.get(i).getName());
                                    imageViewGifs.setImageResource(exerciseList.get(i).getImage());
                                    countText.setText((i + 1) + "/" + maxCountExercise);
                                    setChronometer(timeRest, timeMain);
                                }
                            });

                            try {
                                Thread.sleep(timeRest * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Thread m = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            });
                            m.start();


                            Thread t = new Thread(new Runnable() {
                                public void run() {
                                    h.post(nextExerciseRunnable);
                                }
                            });
                            t.start();

                            timeExercise = exerciseList.get(i).getTime_second();
                            try {
                                Thread.sleep(timeExercise * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                Thread.sleep(mainPause);
                                mainPause =0;
                                onResume();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    timer1Started = false;
                    chronometer2.stop();
                    chronometer1.stop();

                    int exitSeconds = (int) ((SystemClock.elapsedRealtime() - chronometer1.getBase()) / 1000);

                    double minutesCalorie = (double) workout.getCalorie() / workout.getTime(); //73.5 калорий в минуту
                    double secondsCalorie = (double) (workout.getCalorie() / workout.getTime()) / 60; //1.225 калорий в секунду

                    exitCalorie = (int) ((minutesCalorie * (exitSeconds / 60)) + (secondsCalorie * (exitSeconds % 60)));
                    exitTime = (int) (exitSeconds < 60 ? exitSeconds % 60 : exitSeconds / 60);

                    //String strTime = (exitSeconds / 60) + ":" + (exitSeconds % 60);

                    //Текущая дата и время
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR, 3);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String currentTime = dateFormat.format(calendar.getTime());
                    //Добавление в бд и firebase
                    id_user = databaseHelper.getUserId(mUser.getUid());

                    HistoryProgramModel histort_program =
                            databaseHelper.getLastHistoryProgram(currentUser.getUser_id());

                    int g= databaseHelper.getLastHistoryProgram(currentUser.getUser_id()).getHistory_program_id();

                    HistoryWorkoutModel historyWorkout =
                            new HistoryWorkoutModel(id_user, histort_program.getHistory_program_id(), workout.getId(),exitTime,exitCalorie, currentTime);

                    databaseHelper.addHistoryWorkout(historyWorkout);
                    documentUser.collection("history_workouts").document().set(historyWorkout);
                    finish();
                }
            });

            mainThread.start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    continueButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mainThread.interrupt();
                            onResume();
                        }
                    });
                    pauseButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (timer1Started) {
                                chronometer1.stop();
                                chronometer2.stop();
                                timer1Started = false;
                                timer2Started = false;

                                pauseOffset1 = SystemClock.elapsedRealtime() - chronometer1.getBase();
                                pauseOffset2 = SystemClock.elapsedRealtime() - chronometer2.getBase();
                                mainPause = SystemClock.elapsedRealtime();

                                Toast.makeText(ExerciseActivity.this, "СТОП", Toast.LENGTH_SHORT).show();
                            } else {
                                chronometer1.setBase(SystemClock.elapsedRealtime() - pauseOffset1);
                                chronometer2.setBase(SystemClock.elapsedRealtime() - pauseOffset2);
                                chronometer1.start();
                                chronometer2.start();
                                timer1Started = true;
                                timer2Started = true;

                                Toast.makeText(ExerciseActivity.this, "СТАРТ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Runnable firstRunnable = new Runnable() {
        @Override
        public void run() {
            mShadowView.setVisibility(View.GONE);
            nameText.setVisibility(View.VISIBLE);
            chronometer2.stop();
            chronometer2.setBase(SystemClock.elapsedRealtime());
            timer1Started = false;
            setChronometer(timeRest, timeMain);
        }
    };

    private Runnable nextExerciseRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                mainPause = 0;
                chronometer2.start();
                timer1Started = true;
                setChronometer(timeExercise, timeMain);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };

    private void setChronometer(long time2, long timeMain) {
        chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                progressBar.setProgress((int) (SystemClock.elapsedRealtime() - chronometer.getBase()));
                progressBar.setMax((int) time2 * 1000);

                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= time2 * 1000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ExerciseActivity.this, "Таймер2!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chronometer1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                /*
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= timeMain * 60000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ExerciseActivity.this, "Таймер!!", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

}
