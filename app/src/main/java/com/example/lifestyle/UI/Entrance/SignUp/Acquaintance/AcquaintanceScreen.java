package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.Entrance.SignInScreen.SignInScreen;
import com.example.lifestyle.UI.Entrance.SignUp.SplashSignUpScreen;
import com.example.lifestyle.UI.Nutrition.CustomViewPager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcquaintanceScreen extends AppCompatActivity {

    public static String PrefsAcquaintance = "prefs";
    SharedPreferences skip_acquaintance;

    int progress;
    ProgressBar progressBar;
    CustomViewPager viewPager;
    AcquaintanceAdapter adapter;
    TextView title;
    ImageView back;
    List<Fragment> list;
    Button next_button;

    String formulaStr;
    int i, page = 1, y;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    int day, month, year;
    double formula, c, d, e;
    int carbohydrates = 0, fats = 0, protein = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acquaintance_screen);

        skip_acquaintance = getSharedPreferences(AcquaintanceScreen.PrefsAcquaintance, 0);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        next_button = findViewById(R.id.next_button);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my1)));

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);

        list = new ArrayList<>();
        list.add(new FirstPage());
        list.add(new SecondPage());
        list.add(new ThirdPage());
        list.add(new FourthPage());
        list.add(new FifthPage());
        list.add(new EighthPage());
        list.add(new NinthPage());
        list.add(new SixthPage());
        list.add(new SeventhPage());

        y = list.size();
        title.setText("Шаг " + page + " из " + y);

        viewPager = findViewById(R.id.pager);

        adapter = new AcquaintanceAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);

        if (i == 1) {
            back.setVisibility(View.GONE);
        }
        if (i < 2) {
            next_button.setVisibility(View.GONE);
        } else {
            next_button.setVisibility(View.VISIBLE);
        }
        back.setOnClickListener(view -> {
            onBackPressed();
        });
        next_button.setOnClickListener(view -> {
            NextMethod();
        });
    }

    public void NextMethod() {
        i++;
        FirstPage first = FirstPage.GetInstance();
        SecondPage second = SecondPage.GetInstance();
        ThirdPage third = ThirdPage.GetInstance();
        FourthPage fourth = FourthPage.GetInstance();
        FifthPage fifth = FifthPage.GetInstance();
        SixthPage sixth = SixthPage.GetInstance();
        SeventhPage seventh = SeventhPage.GetInstance();
        EighthPage eighth = EighthPage.GetInstance();
        NinthPage ninth = NinthPage.GetInstance();

        if (first.get == 1) {
            y = 8;
        } else {
            y = 9;
        }

        if (i > 0) {
            back.setVisibility(View.VISIBLE);
        }
        if (i > 1) {
            next_button.setVisibility(View.VISIBLE);
        }
        if (i == 4) {
            fourth.heightSTR = fourth.height_et.getText().toString();
            if (fourth.heightSTR.isEmpty()) {
                fourth.heightET.setError("Поле не может быть пустым");
                i = 3;
                page = 3;
            } else {
                fourth.height = Integer.parseInt(fourth.heightSTR);
                if (fourth.height < 122) {
                    fourth.heightET.setError("Рост не менее 122 см");
                    i = 3;
                    page = 3;
                } else if (fourth.height > 272) {
                    fourth.heightET.setError("Рост не более 272 см");
                    i = 3;
                    page = 3;
                } else {
                    fourth.heightET.setError(null);
                }
            }
        }

        if (i == 8) {
            sixth.current_weightSTR = sixth.current_weight_et.getText().toString();
            if (sixth.current_weightSTR.isEmpty()) {
                sixth.current_weightET.setError("Поле не может быть пустым");
                i = 7;
                page = 7;
            } else if (Integer.parseInt(sixth.current_weightSTR) < 46) {
                sixth.current_weightET.setError("Указанный вес не поддерживается приложением");
                i = 7;
                page = 7;
            } else if (Integer.parseInt(sixth.current_weightSTR) > 259) {
                sixth.current_weightET.setError("Указанный вес не поддерживается приложением");
                i = 7;
                page = 7;
            } else {
                sixth.current_weightET.setError(null);
            }
        }
        if (i == 9) {
            seventh.desired_weightSTR = seventh.desired_weight_et.getText().toString();
            sixth.current_weightSTR = sixth.current_weight_et.getText().toString();
            if (seventh.desired_weightSTR.isEmpty()) {
                seventh.desired_heightET.setError("Поле не может быть пустым");
                i = 8;
                page = 8;
            } else if (Integer.parseInt(seventh.desired_weightSTR) < 46) {
                seventh.desired_heightET.setError("Указанный вес не поддерживается приложением");
                i = 8;
                page = 8;
            } else if (Integer.parseInt(seventh.desired_weightSTR) > 259) {
                seventh.desired_heightET.setError("Указанный вес не поддерживается приложением");
                i = 8;
                page = 8;
            } else {
                if (first.gain == 1 && Integer.parseInt(seventh.desired_weightSTR) <= Integer.parseInt(sixth.current_weightSTR)) {
                    seventh.desired_heightET.setError("Введите число, превышающее ваш текущий вес");
                    i = 8;
                    page = 8;
                } else if (first.lose == 1 && Integer.parseInt(seventh.desired_weightSTR) >= Integer.parseInt(sixth.current_weightSTR)) {
                    seventh.desired_heightET.setError("Введите число, которое будет меньше вашего текущего веса");
                    i = 8;
                    page = 8;
                } else {
                    seventh.desired_heightET.setError(null);
                }
            }
        }

        if (i == y) {
            seventh.desired_weightSTR = seventh.desired_weight_et.getText().toString();
            sixth.current_weightSTR = sixth.current_weight_et.getText().toString();

            c = third.dd;
            fourth.height = Integer.parseInt(fourth.heightSTR);
            d = fourth.height;
            sixth.current = Integer.parseInt(sixth.current_weightSTR);
            e = sixth.current;

            if (second.gender == 1) {
                formula = 10 * e + 6.25 * d - 5 * c + 5;
            } else if (second.gender == 2) {
                formula = 10 * e + 6.25 * d - 5 * c - 161;
            }
            if (fifth.slider.getValue() == 0) {
                formula = formula * 1.2;
            } else if (fifth.slider.getValue() == 1) {
                formula = formula * 1.38;
            } else if (fifth.slider.getValue() == 2) {
                formula = formula * 1.46;
            } else if (fifth.slider.getValue() == 3) {
                formula = formula * 1.55;
            } else if (fifth.slider.getValue() == 4) {
                formula = formula * 1.64;
            }

            if (first.goal == 1) {
                formula = formula * 0.85;
            } else if (first.goal == 2) {
                formula = formula;
            } else if (first.goal == 3) {
                formula = formula + 800;
            }


            i = y - 1;
            page = y - 1;
            startActivity(new Intent(AcquaintanceScreen.this, SplashSignUpScreen.class));
            finishAffinity();
            progress = 100;
            progressBar.setProgress(progress);
            viewPager.setCurrentItem(i);

            FirebaseUser user = mAuth.getCurrentUser();
            DocumentReference df = fStore.collection("users").document(user.getUid());

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("gender", second.gender);
            userInfo.put("birthday", third.date);
            userInfo.put("age", third.dd);
            userInfo.put("height", fourth.height);
            userInfo.put("weight", Integer.parseInt(sixth.current_weightSTR));
            userInfo.put("weight_gr", 0);
            userInfo.put("account_type", "Стандартный");
            df.update(userInfo);

            df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String nameUser = document.getString("name");
                            String emailUser= document.getString("mail");
                            String passwordUser = document.getString("password");

                            //Салават // добавление пользователя в локальную базу
                            UserModel userDB = new UserModel(nameUser,third.dd,fourth.height,
                                    Integer.parseInt(sixth.current_weightSTR),emailUser,passwordUser);
                            userDB.setProgram_id(1);
                            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getBaseContext());
                            databaseHelper.addUser(userDB, user.getUid());

                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.HOUR, 3);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            String currentTime = dateFormat.format(calendar.getTime());
                            UserModel currentUser =new UserModel().getCurrentUser(AcquaintanceScreen.this);
                            HistoryProgramModel historyProgram =
                                    new HistoryProgramModel(currentUser.getUser_id(), 1, 0, currentTime, "");
                            databaseHelper.addHistoryProgram(historyProgram);
                            //

                        } else {
                            Toast.makeText(AcquaintanceScreen.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            Map<String, Object> diaryInfo = new HashMap<>();

            diaryInfo.put("carbohydrates_eat", carbohydrates);
            diaryInfo.put("fat_eat", protein);
            diaryInfo.put("protein_eat", fats);
            diaryInfo.put("kal_eat", Math.round(carbohydrates * 4 + protein * 4 + fats * 9));
            df.collection("diary_info").document("" + day + "." + (month + 1) + "." + year).set(diaryInfo);

            Map<String, Object> workoutInfo = new HashMap<>();
            workoutInfo.put("goal", first.goal);
            workoutInfo.put("active", fifth.slider.getValue());
            workoutInfo.put("stamina", eighth.slider.getValue());
            workoutInfo.put("strength", ninth.slider.getValue());
            workoutInfo.put("level", ((ninth.slider.getValue() + eighth.slider.getValue() + fifth.slider.getValue()) / 3) + 1);

            if (first.get == 1) {
                workoutInfo.put("weight_goal", Integer.parseInt(sixth.current_weightSTR));
            } else if (first.get == 0) {
                workoutInfo.put("weight_goal", Integer.parseInt(seventh.desired_weightSTR));
            }

            workoutInfo.put("kal", Math.round(formula / 100 * 45 / 4) * 4 + Math.round(formula / 100 * 30 / 9) * 9 + Math.round(formula / 100 * 25 / 4) * 4);
            workoutInfo.put("protein", formula / 100 * 25 / 4);
            workoutInfo.put("fat", formula / 100 * 30 / 9);
            workoutInfo.put("carbohydrates", formula / 100 * 45 / 4);
            df.collection("workout_info").document("1").set(workoutInfo);

            SharedPreferences.Editor skip_editor = skip_acquaintance.edit();
            skip_editor.putBoolean("hasLoggedIn2", true).apply();
        } else {
            progress = Math.round(100 / y * i);
            progressBar.setProgress(progress);
            viewPager.setCurrentItem(i);
        }
        page++;
        title.setText("Шаг " + page + " из " + y);
    }

    @Override
    public void onBackPressed() {
        if (i == 0) {
            finish();
        }
        if (i == 1) {
            back.setVisibility(View.GONE);
        }
        if (i < 2) {
            next_button.setVisibility(View.GONE);
        }
        if (i > 0) {
            i--;
            viewPager.setCurrentItem(i);
            page--;
            title.setText("Шаг " + page + " из " + y);
            progress = 100 / y * i;
            progressBar.setProgress(progress);
        }
    }
}