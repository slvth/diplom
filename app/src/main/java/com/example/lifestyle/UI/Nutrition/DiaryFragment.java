package com.example.lifestyle.UI.Nutrition;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Article.ArticleFragment;
import com.example.lifestyle.UI.MainScreen;
import com.example.lifestyle.UI.Nutrition.Diary.Add.AddMealScreen;
import com.example.lifestyle.UI.Nutrition.Diary.ProfileScreen;
import com.example.lifestyle.UI.Workout.WorkoutFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DiaryFragment extends Fragment {

    private static DiaryFragment instance;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    ImageView imgProfile, minus, add;
    TextView kkal, kal_ost_text, uglevod, kkal_eat, jir, belki, uglevod_ost, jir_ost, belki_ost, kg, gr, datee,weight_txt;
    RelativeLayout weight_update, zavtrak, perecus, obed, ujin, workout_in, go_workout_all, next, back_calendar;
    LinearLayout calendar;
    int i = 2, weight_kg, weight_gr;
    int day, month, year;
    final Calendar myCalendar = Calendar.getInstance();
    double carbohydrates = 0, fats = 0, protein = 0;
    DocumentReference df;
    FirebaseUser user;
    String myFormat2 = "yyyy-MM-dd";
    String myFormat = "dd LLL yyyy г.";
    String myFormat3 = "dd LLL";
    String myFormat4 = "d LLL";
    SimpleDateFormat dateFormat2, dateFormat;
    DocumentReference workoutInf;
    ProgressBar progressCarb, progressFat, progressProtein;
    CircularProgressBar kal_progress;
    double protein_eat = 0, fat_eat = 0, carbohydrates_eat = 0, kal_eat, kal_eat_doc;
    public Date date_now;
    long kal_all;
    LinearLayout article_go;
    SimpleDateFormat dateFormat3;
    SimpleDateFormat dateFormat4;
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.fragment_diary, container, false);

        imgProfile = v.findViewById(R.id.imgProfile);
        weight_txt = v.findViewById(R.id.weight_txt);
        article_go = v.findViewById(R.id.article_go);
        progressCarb = v.findViewById(R.id.progress_carb);
        progressFat = v.findViewById(R.id.progress_fat);
        progressProtein = v.findViewById(R.id.progress_protein);
        kal_progress = v.findViewById(R.id.kal_progress);
        kkal_eat = v.findViewById(R.id.kkal_eat);
        kal_ost_text = v.findViewById(R.id.kal_ost_text);
        kkal = v.findViewById(R.id.kkal);
        weight_update = v.findViewById(R.id.weight_update);
        uglevod_ost = v.findViewById(R.id.uglevod_ost);
        jir_ost = v.findViewById(R.id.jir_ost);
        belki_ost = v.findViewById(R.id.belki_ost);
        uglevod = v.findViewById(R.id.uglevod);
        jir = v.findViewById(R.id.jir);
        belki = v.findViewById(R.id.belki);
        workout_in = v.findViewById(R.id.workout_in);
        go_workout_all = v.findViewById(R.id.go_workout_all);
        next = v.findViewById(R.id.next);
        back_calendar = v.findViewById(R.id.back_calendar);
        calendar = v.findViewById(R.id.calendar);
        zavtrak = v.findViewById(R.id.zavtrak);
        perecus = v.findViewById(R.id.perecus);
        obed = v.findViewById(R.id.obed);
        datee = v.findViewById(R.id.date);
        ujin = v.findViewById(R.id.ujin);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressCarb.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my3)));
        progressFat.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my3)));
        progressProtein.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my3)));

        article_go.setOnClickListener(view -> {
            go_article();
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
                data_update();
            }
        };
        calendar.setOnClickListener(view -> {
            new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);

        user = mAuth.getCurrentUser();
        df = fStore.collection("users").document(user.getUid());
        workout_in.setOnClickListener(view -> {
            go_workout();
        });

        dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());
        dateFormat4 = new SimpleDateFormat(myFormat4, Locale.getDefault());
        datee.setText("Сегодня");

        workoutInf = df.collection("workout_info").document("1");
        next.setOnClickListener(view -> {
            myCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if (isYesterday(myCalendar.getTime())){
                datee.setText("Вчера");
            }else if (isTomorrow(myCalendar.getTime())){
                datee.setText("Завтра");
            }else if (isToday(myCalendar.getTime())){
                datee.setText("Сегодня");
            }else {
                datee.setText(dateFormat4.format(myCalendar.getTime()));
            }
            data_update();
        });
        back_calendar.setOnClickListener(view -> {
            myCalendar.add(Calendar.DAY_OF_MONTH, -1);
            if (isYesterday(myCalendar.getTime())){
                datee.setText("Вчера");
            }else if (isTomorrow(myCalendar.getTime())){
                datee.setText("Завтра");
            }else if (isToday(myCalendar.getTime())){
                datee.setText("Сегодня");
            }else {
                datee.setText(dateFormat4.format(myCalendar.getTime()));
            }
            data_update();
        });

        go_workout_all.setOnClickListener(view -> {
            go_workout();
        });
        intent = new Intent(getActivity(), AddMealScreen.class);

        zavtrak.setOnClickListener(view -> {
            Zavtrak();
        });
        perecus.setOnClickListener(view -> {
            Perecus();
        });
        obed.setOnClickListener(view -> {
            Obed();
        });
        ujin.setOnClickListener(view -> {
            Ujin();
        });

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference photoReference = storageReference.child("user_images/" + user.getUid());

        final long ONE_MEGABYTE = 1024 * 1024;
        if (photoReference != null) {
            photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imgProfile.setImageBitmap(bmp);
                }
            });
        }

        weight_update.setOnClickListener(view -> {
            dialog();
        });
        imgProfile.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ProfileScreen.class));
        });

        data_update();
        return v;
    }

    public void Ujin() {
        intent.putExtra("meal_type", "Ужин");
        intent.putExtra("date_time", date_now.getTime());
        startActivity(intent);
    }

    public void Obed() {
        intent.putExtra("meal_type", "Обед");
        intent.putExtra("date_time", date_now.getTime());
        startActivity(intent);
    }

    public void Perecus() {
        intent.putExtra("meal_type", "Перекус");
        intent.putExtra("date_time", date_now.getTime());
        startActivity(intent);
    }

    public void Zavtrak() {
        intent.putExtra("meal_type", "Завтрак");
        intent.putExtra("date_time", date_now.getTime());
        startActivity(intent);
    }

    public static DiaryFragment GetInstance() {
        return instance;
    }
    public static boolean isYesterday(Date d) {
        return DateUtils.isToday(d.getTime() + DateUtils.DAY_IN_MILLIS);
    }
    public static boolean isToday(Date d) {
        return DateUtils.isToday(d.getTime());
    }
    public static boolean isTomorrow(Date d) {
        return DateUtils.isToday(d.getTime() - DateUtils.DAY_IN_MILLIS);
    }
    public void data_update() {
        kal_eat = Math.round(protein_eat * 4 + fat_eat * 9 + carbohydrates_eat * 4);
        date_now = myCalendar.getTime();

        //вес для статистики, времени не хватает, поэтому делать не буду
        /*Calendar now=Calendar.getInstance();
        Calendar yourDate = Calendar.getInstance();
        yourDate.setTime(myCalendar.getTime());
        if (now.before(yourDate)) {
            weight_txt.setVisibility(View.GONE);
            weight_update.setVisibility(View.GONE);
        }else {
            weight_txt.setVisibility(View.VISIBLE);
            weight_update.setVisibility(View.VISIBLE);
        }*/

        DocumentReference diaryInfk = df.collection("diary_info").document("" + dateFormat2.format(date_now));
        diaryInfk.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> diaryInfo = new HashMap<>();
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        carbohydrates = document.getDouble("carbohydrates_eat");
                        fats = document.getDouble("fat_eat");
                        protein = document.getDouble("protein_eat");
                        kal_eat_doc = document.getDouble("kal_eat");

                        uglevod.setText(String.valueOf("Углеводы: " + Math.round(carbohydrates) + " г"));
                        jir.setText(String.valueOf("Жиры: " + Math.round(fats) + " г"));
                        belki.setText(String.valueOf("Белки: " + Math.round(protein) + " г"));

                        workoutInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        if (carbohydrates == 0) {
                                            progressCarb.setProgress(0);
                                            uglevod_ost.setText(String.valueOf(Math.round(document.getDouble("carbohydrates") - 0) + " г осталось"));
                                        } else {
                                            if (carbohydrates <= Math.round(document.getDouble("carbohydrates"))) {
                                                progressCarb.setProgress((int) (carbohydrates / document.getDouble("carbohydrates") * 100));
                                                uglevod_ost.setText(String.valueOf(Math.round(document.getDouble("carbohydrates") - carbohydrates) + " г осталось"));
                                            } else {
                                                progressCarb.setProgress(100);
                                                uglevod_ost.setText(String.valueOf(Math.abs(Math.round(document.getDouble("carbohydrates") - carbohydrates)) + " г перебор"));
                                            }
                                        }
                                        if (fats == 0) {
                                            progressFat.setProgress(0);
                                            jir_ost.setText(String.valueOf(Math.round(document.getDouble("fat") - 0) + " г осталось"));
                                        } else {
                                            if (fats <= Math.round(document.getDouble("fat"))) {
                                                progressFat.setProgress((int) (fats / document.getDouble("fat") * 100));
                                                jir_ost.setText(String.valueOf(Math.round(document.getDouble("fat") - fats) + " г осталось"));
                                            } else {
                                                progressFat.setProgress(100);
                                                jir_ost.setText(String.valueOf(Math.abs(Math.round(document.getDouble("fat") - fats)) + " г перебор"));
                                            }
                                        }
                                        if (protein == 0) {
                                            progressProtein.setProgress(0);
                                            belki_ost.setText(String.valueOf(Math.round(document.getDouble("protein") - 0) + " г осталось"));
                                        } else {
                                            if (protein <= Math.round(document.getDouble("protein"))) {
                                                progressProtein.setProgress((int) (protein / document.getDouble("protein") * 100));
                                                belki_ost.setText(String.valueOf(Math.round(document.getDouble("protein") - protein) + " г осталось"));
                                            } else {
                                                progressProtein.setProgress(100);
                                                belki_ost.setText(String.valueOf(Math.abs(Math.round(document.getDouble("protein") - protein)) + " г перебор"));
                                            }
                                        }

                                        kal_all = Math.round(document.getDouble("carbohydrates")) * 4 + Math.round(document.getDouble("protein")) * 4 + Math.round(document.getDouble("fat")) * 9;
                                        long kal_proc = Math.round(kal_eat_doc / kal_all * 100);
                                        if (kal_all < kal_eat_doc) {
                                            kal_progress.setProgress(100);
                                            kal_ost_text.setText("ккал перебор");
                                            kkal.setText(String.valueOf(Math.abs(Math.round(kal_all - kal_eat_doc))));
                                        } else {
                                            kal_ost_text.setText("ккал осталось");
                                            kal_progress.setProgress(kal_proc);
                                            kkal.setText(String.valueOf(Math.round(kal_all - kal_eat_doc)));
                                        }
                                        kkal_eat.setText(String.valueOf(Math.round(kal_eat_doc) + " ккал съедено"));
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {

                        uglevod.setText(String.valueOf("Углеводы: " + 0 + " г"));
                        jir.setText(String.valueOf("Жиры: " + 0 + " г"));
                        belki.setText(String.valueOf("Белки: " + 0 + " г"));
                        progressProtein.setProgress(0);
                        progressFat.setProgress(0);
                        progressCarb.setProgress(0);
                        kal_progress.setProgress(0);

                        workoutInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        jir_ost.setText(String.valueOf(Math.round(document.getDouble("fat"))) + " г осталось");
                                        belki_ost.setText(String.valueOf(Math.round(document.getDouble("protein"))) + " г осталось");
                                        uglevod_ost.setText(String.valueOf(Math.round(document.getDouble("carbohydrates"))) + " г осталось");
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        kkal_eat.setText(0 + " ккал съедено");
                        kal_ost_text.setText("ккал осталось");
                        workoutInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    kkal.setText(String.valueOf(Math.round(document.getDouble("kal"))));
                                } else {
                                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        if (isYesterday(myCalendar.getTime())){
            datee.setText("Вчера");
        }else if (isTomorrow(myCalendar.getTime())){
            datee.setText("Завтра");
        }else if (isToday(myCalendar.getTime())){
            datee.setText("Сегодня");
        }else {
            datee.setText(dateFormat4.format(myCalendar.getTime()));
        }
    }

    public void go_workout() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkoutFragment()).addToBackStack("null").commit();

        ((MainScreen) getActivity()).onNavigationItemSelected(((MainScreen) getActivity()).bottomNav.getMenu().getItem(1).setChecked(true));
    }
    public void go_article() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ArticleFragment()).addToBackStack("null").commit();

        ((MainScreen) getActivity()).onNavigationItemSelected(((MainScreen) getActivity()).bottomNav.getMenu().getItem(4).setChecked(true));
    }

    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.weight_update_dialog, null);

        kg = v.findViewById(R.id.kg);
        gr = v.findViewById(R.id.gr);
        minus = v.findViewById(R.id.minus);
        add = v.findViewById(R.id.add);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        i = 2;

        kg.setTextColor(getResources().getColor(R.color.black));
        gr.setTextColor(getResources().getColor(R.color.my1));

        FirebaseUser user = mAuth.getCurrentUser();
        Map<String, Object> userInfo = new HashMap<>();
        DocumentReference df = fStore.collection("users").document(user.getUid());
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        weight_kg = document.getLong("weight").intValue();
                        weight_gr = document.getLong("weight_gr").intValue();

                        kg.setText(String.valueOf(weight_kg));
                        gr.setText(String.valueOf("." + weight_gr));

                        kg.setOnClickListener(view -> {
                            i = 1;
                            kg.setTextColor(getResources().getColor(R.color.my1));
                            gr.setTextColor(getResources().getColor(R.color.black));
                        });
                        gr.setOnClickListener(view -> {
                            i = 2;
                            kg.setTextColor(getResources().getColor(R.color.black));
                            gr.setTextColor(getResources().getColor(R.color.my1));
                        });
                    } else {
                        weight_kg = 0;
                        weight_gr = 0;
                    }

                    minus.setOnClickListener(view -> {
                        if (weight_kg > 46 && i == 1) {
                            weight_kg = weight_kg - 1;
                        } else if (weight_gr > 0 && i == 2) {
                            weight_gr = weight_gr - 1;
                        } else if (weight_gr == 0 && i == 2) {
                            if (weight_kg > 46) {
                                weight_gr = 9;
                                weight_kg = weight_kg - 1;
                            }
                        }

                        kg.setText(String.valueOf(weight_kg));
                        gr.setText(String.valueOf("." + weight_gr));
                    });
                    add.setOnClickListener(view -> {
                        if (weight_kg < 260 && i == 1) {
                            weight_kg = weight_kg + 1;
                        } else if (weight_gr < 9 && i == 2) {
                            weight_gr = weight_gr + 1;
                        } else if (weight_gr == 9 && i == 2) {
                            if (weight_kg < 260) {
                                weight_gr = 0;
                                weight_kg = weight_kg + 1;
                            }
                        }
                        if (weight_kg == 260) {
                            weight_kg = 260;
                            weight_gr = 0;
                        }

                        kg.setText(String.valueOf(weight_kg));
                        gr.setText(String.valueOf("." + weight_gr));
                    });
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final RelativeLayout relativeLayout = v.findViewById(R.id.update);
        builder.setView(v).setCancelable(true);
        final AlertDialog alertDialog = builder.create();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("weight", weight_kg);
                userInfo.put("weight_gr", weight_gr);
                df.update(userInfo);

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        data_update();
    }
}