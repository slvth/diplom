package com.example.lifestyle.UI.Workout.WorkoutUI.AddActive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.ActivityModel;
import com.example.lifestyle.Models.HistoryActivityModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.Workout.Adapters.WPDayPickerAdapter;
import com.super_rabbit.wheel_picker.WheelPicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailAddActiveActivity extends AppCompatActivity {
    //элементы интерфейса
    ImageView imageActivity;
    WheelPicker numberPicker;
    TextView txtDate;
    LinearLayout dateLinear;
    Toolbar toolbar;
    Button btnSave;
    EditText hourText, minuteText;

    //переменные
    String hourString, minuteString, date;
    ActivityModel _activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_add_active);

        imageActivity = findViewById(R.id.imgDetailAddActive);
        numberPicker = findViewById(R.id.wheelPicker);
        dateLinear = findViewById(R.id.date_linearDetailAddActive);
        txtDate = findViewById(R.id.txt_dateDetailAddActive);
        toolbar = findViewById(R.id.toolbar_DetailAddActive);
        btnSave = findViewById(R.id.btnSaveActive);
        hourText = findViewById(R.id.edit_txt_hours_active);
        minuteText = findViewById(R.id.edit_txt_minutes_active);
        _activity = new ActivityModel();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            _activity = (ActivityModel) arguments.getSerializable(ActivityModel.class.getSimpleName());
            toolbar.setTitle(_activity.getName());
            imageActivity.setImageResource(_activity.getImage());

            //сохранение активности
            btnSave.setOnClickListener(view -> {
                if (passedValidation()) {
                    try {
                        saveData();
                        Toast.makeText(DetailAddActiveActivity.this, "Успешно!", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        //настройка отображения выбора даты
        settingNumberPicker();

        //кнопка назад
        toolbar.setNavigationOnClickListener(view -> finish());

        //обработка выбора даты
        numberPicker.setOnValueChangeListener((wheelPicker, oldVal, newVal) -> {
            String out = String.format(newVal);
            txtDate.setText(out);
        });

        //скрывать или не скрывать выбор даты
        dateLinear.setOnClickListener(view -> {
            int visibility = numberPicker.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            numberPicker.setVisibility(visibility);
        });

    }

    private void saveData() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatOld = new SimpleDateFormat("d MMM, yyyy");
        SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();

        if(date.equals("Вчера")){
            calendar.setTime(myDate);
            calendar.add(Calendar.DATE, -1);
        }
        else if(date.equals("Сегодня")){
            calendar.setTime(myDate);
            calendar.add(Calendar.DATE, 0);
        }
        else{
            myDate = formatOld.parse(date);
            calendar.setTime(myDate);
        }
        calendar.add(Calendar.HOUR, 3);

        String currentDate = formatNew.format(calendar.getTime());
        UserModel _user = new UserModel().getCurrentUser(this);
        //вес пользователя
        int weight_user = _user.getWeight() > 10 ? _user.getWeight() : 50;
        //калории активности*3600(час в секундах)*вес пользователя
        int calorie_activity = _activity.getCalorie_hour() * 3600 * weight_user;

        //затраченное время на активность
        int hour_in_second = Integer.parseInt(hourString) * 3600;
        int minute_in_second = Integer.parseInt(minuteString) * 60;

        int user_id = _user.getUser_id(); //id пользователя
        int activity_id = _activity.getActivity_id(); //id активности
        int time_second = hour_in_second + minute_in_second; //общее время (в секундах), затраченное на активность
        int calorie = calorie_activity  / time_second; //общая калорийность за час (в секундах)/общее время(в секундах)

        HistoryActivityModel historyActivity =
                new HistoryActivityModel(user_id, activity_id, time_second, calorie, currentDate);

        DatabaseHelper.getInstance(this).addHistoryActivity(historyActivity);
    }

    private boolean passedValidation() {
        hourString = hourText.getText().toString();
        minuteString = minuteText.getText().toString();
        date = txtDate.getText().toString();

        if (hourString.isEmpty() && minuteString.isEmpty())
            Toast.makeText(DetailAddActiveActivity.this, "Введите продолжительность!", Toast.LENGTH_SHORT).show();
        else {
            hourString = !hourString.equals("") ? hourString : "0";
            minuteString = !minuteString.equals("") ? minuteString : "0";
            int hour = Integer.parseInt(hourString);
            int minute = Integer.parseInt(minuteString);

            if (hour == 0 && minute == 0)
                Toast.makeText(DetailAddActiveActivity.this, "Часы и минуты не должны быть равны нулю!", Toast.LENGTH_SHORT).show();
            else if (!(hour >= 0 && hour <= 12))
                Toast.makeText(DetailAddActiveActivity.this, "Часы должны быть до 12!", Toast.LENGTH_SHORT).show();
            else if (!(minute >= 0 && minute <= 59))
                Toast.makeText(DetailAddActiveActivity.this, "Минуты должны быть до 59!", Toast.LENGTH_SHORT).show();
            else
                return true;
        }
        return false;
    }

    private void settingNumberPicker() {
        numberPicker.setSelectorRoundedWrapPreferred(false);
        numberPicker.setWheelItemCount(3);
        numberPicker.setMax(0);
        numberPicker.setMin(-20);
        numberPicker.setSelectedTextColor(R.color.black);
        numberPicker.setUnselectedTextColor(com.super_rabbit.wheel_picker.R.color.color_26_gray);
        numberPicker.setAdapter(new WPDayPickerAdapter());
        numberPicker.setVisibility(View.GONE);
    }
}