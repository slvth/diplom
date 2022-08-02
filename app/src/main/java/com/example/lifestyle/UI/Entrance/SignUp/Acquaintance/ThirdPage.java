package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;

import java.util.Calendar;
import java.util.Date;

public class ThirdPage extends Fragment {

    private static ThirdPage instance;
    DatePicker datePicker;
    String date;
    int dd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.third_page, container, false);

        datePicker = v.findViewById(R.id.datePicker);
        Calendar c = Calendar.getInstance();
        Date currentTime = Calendar.getInstance().getTime();
        Calendar min = Calendar.getInstance();
        c.set(datePicker.getYear() - 13, datePicker.getMonth(), datePicker.getDayOfMonth());
        min.set(datePicker.getYear() - 65, datePicker.getMonth(), datePicker.getDayOfMonth());
        datePicker.setMaxDate(c.getTimeInMillis());
        datePicker.setMinDate(min.getTimeInMillis());

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        date = day + "." + (month + 1) + "." + year;
        dd = getPerfectAgeInYears(year, month, day);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                date = i2 + "." + (i1 + 1) + "." + i;
                dd = getPerfectAgeInYears(i, i1, i2);
            }
        });

        return v;
    }

    public static ThirdPage GetInstance() {
        return instance;
    }

    public static int getPerfectAgeInYears(int year, int month, int date) {

        Calendar dobCalendar = Calendar.getInstance();

        dobCalendar.set(Calendar.YEAR, year);
        dobCalendar.set(Calendar.MONTH, month);
        dobCalendar.set(Calendar.DATE, date);

        int ageInteger = 0;

        Calendar today = Calendar.getInstance();

        ageInteger = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) == dobCalendar.get(Calendar.MONTH)) {

            if (today.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH)) {

                ageInteger = ageInteger - 1;
            }

        } else if (today.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH)) {

            ageInteger = ageInteger - 1;

        }

        return ageInteger;
    }
}