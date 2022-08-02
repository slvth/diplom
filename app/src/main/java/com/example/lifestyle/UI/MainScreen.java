package com.example.lifestyle.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.example.lifestyle.UI.Article.ArticleFragment;
import com.example.lifestyle.UI.Article.DetailArticleActivity;
import com.example.lifestyle.UI.Nutrition.AlarmBroadcastReceiver;
import com.example.lifestyle.UI.Nutrition.DiaryFragment;
import com.example.lifestyle.UI.Nutrition.NutritionFragment;
import com.example.lifestyle.UI.Workout.WorkoutFragment;
import com.example.lifestyle.UI.Workout.WorkoutUI.AddActive.BottomSheetAddFragment;
import com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms.LevelDetailActivity;
import com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram.SplashScreenNextProgram;
import com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram.WorkoutPrewiewActivity;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class MainScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public BottomNavigationView bottomNav;
    Fragment selectedFragment;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        bottomNav = findViewById(R.id.bot_nav);


        BottomNavigationMenuView botMenu=(BottomNavigationMenuView) bottomNav.getChildAt(0);
        View view = botMenu.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) view;
        View cart_badge = LayoutInflater.from(this).inflate(R.layout.button_menu, botMenu, false);
        itemView.addView(cart_badge);

        itemView.setOnClickListener(view1 -> {
            BottomSheetAddFragment bottomSheet = new BottomSheetAddFragment();
            bottomSheet.show(getSupportFragmentManager(),"TAG");
        });

        bottomNav.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiaryFragment()).commit();
        }

        startAlarmBroadcastReceiver(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectedFragment = null;
        id = 0;
        switch (item.getItemId()) {
            case R.id.article:
                id = 4;
                bottomNav.getMenu().getItem(4).setChecked(true);
                selectedFragment = new ArticleFragment();
                break;
            case R.id.workout:
                id = 1;
                bottomNav.getMenu().getItem(1).setChecked(true);
                selectedFragment = new WorkoutFragment();
                break;
            case R.id.nutrition:
                id = 3;
                bottomNav.getMenu().getItem(3).setChecked(true);
                selectedFragment = new NutritionFragment();
                break;
            case R.id.diary:
                id = 0;
                bottomNav.getMenu().getItem(0).setChecked(true);
                selectedFragment = new DiaryFragment();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).addToBackStack(null).commit();

        return true;
    }

    @Override
    public void onBackPressed() {
        if (bottomNav.getSelectedItemId() == R.id.diary) {
            finishAffinity();
        } else {
            bottomNav.setSelectedItemId(R.id.diary);
        }
    }
    public static void startAlarmBroadcastReceiver(Context context) {
        Intent _intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, _intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void openDetailArticleActivity(String header, String desc, int image){
        Intent intent = new Intent(this, DetailArticleActivity.class);

        intent.putExtra("Header", header);
        intent.putExtra("Description", desc);
        intent.putExtra("Image", image);

        startActivity(intent);
    }
    public void openWorkoutPrewiewActivity(WorkoutModel workout, boolean isDoneWorkout){
        Intent intent = new Intent(this, WorkoutPrewiewActivity.class);
        intent.putExtra(WorkoutModel.class.getSimpleName(), workout);
        intent.putExtra("isDone", isDoneWorkout);
        startActivityForResult(intent, 2);
    }
    public void openLevelDetail(LevelModel level){
        Intent intent = new Intent(this, LevelDetailActivity.class);
        intent.putExtra(LevelModel.class.getSimpleName(), level);
        startActivityForResult(intent, 200);
    }
    public void openSplashScreenNextProgram(ProgramModel program){
        Intent intent = new Intent(this, SplashScreenNextProgram.class);
        intent.putExtra(ProgramModel.class.getSimpleName(), program);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==200){
                selectedFragment =new WorkoutFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).addToBackStack(null).commit();
            }
        }
        if(requestCode == 2){
            selectedFragment =new WorkoutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).addToBackStack(null).commit();
        }
    }
}