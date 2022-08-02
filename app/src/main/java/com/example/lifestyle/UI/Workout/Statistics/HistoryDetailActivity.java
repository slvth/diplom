package com.example.lifestyle.UI.Workout.Statistics;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.HistoryFoodModel;
import com.example.lifestyle.Models.TotalHistoryWorkoutModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.Workout.Adapters.HistoryDetailAdapter;
import com.example.lifestyle.UI.Workout.Adapters.HistoryWorkoutAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HistoryDetailActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    FirebaseUser firebaseUser;
    String _uid;

    ListView mlistViewHistory;
    TextView txtWorkoutColorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        databaseHelper = DatabaseHelper.getInstance(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        _uid = firebaseUser.getUid();

        mlistViewHistory = findViewById(R.id.history_workout_list2);
        txtWorkoutColorie = findViewById(R.id.txtWorkoutColorie2);

        Boolean isFoodActivity = getIntent().getBooleanExtra("food", false);
        if(isFoodActivity)
            getHistoryFoodList();
        else
            getHistoryWorkoutList();

        mlistViewHistory.setDividerHeight(5);
    }

    private void getHistoryWorkoutList() {
        int user_id = new UserModel().getCurrentUser(this).getUser_id();
        ArrayList<TotalHistoryWorkoutModel> historyWorkoutList = databaseHelper.getTotalHistories(user_id);
        int sum = 0;
        for (int i = 0; i < historyWorkoutList.size(); i++) {
            sum += historyWorkoutList.get(i).getCalorie();
        }
        txtWorkoutColorie.setText("ИТОГО: " + sum + " Калорий");

        HistoryWorkoutAdapter historyAdapter =
                new HistoryWorkoutAdapter(this, R.layout.item_history_workout, historyWorkoutList);

        mlistViewHistory.setAdapter(historyAdapter);
        mlistViewHistory.setClickable(false);
    }

    private void getHistoryFoodList() {
        ArrayList<HistoryFoodModel> historyFoodList = databaseHelper.getAllHistoryFood(_uid);
        int sum = 0;
        for (int i = 0; i < historyFoodList.size(); i++) {
            sum += historyFoodList.get(i).getCalorieTotal();
        }
        txtWorkoutColorie.setText("ИТОГО: " + sum + " Калорий");

        HistoryDetailAdapter historyAdapter =
                new HistoryDetailAdapter(this, R.layout.item_history_workout, historyFoodList);

        mlistViewHistory.setAdapter(historyAdapter);
        mlistViewHistory.setClickable(false);
    }


}