package com.example.lifestyle.UI.Workout.Adapters;

import androidx.annotation.NonNull;

import com.super_rabbit.wheel_picker.WheelAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WPDayPickerAdapter implements WheelAdapter {
    @Override
    public int getMaxIndex() {
        return 0;
    }

    @Override
    public int getMinIndex() {
        return -20;
    }

    @Override
    public int getPosition(@NonNull String s) {
        return 0;
    }

    @NonNull
    @Override
    public String getTextWithMaximumLength() {
        return "00 Mmm, 0000";
    }

    @NonNull
    @Override
    public String getValue(int position) {
        if (position == 0)
            return "Сегодня";

        if(position == -1)
            return "Вчера";

        if (position == 1)
            return "Завтра";

        Date curDate = new Date(System.currentTimeMillis());
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(curDate);
        rightNow.add(Calendar.DATE, position);

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("d MMM, yyyy");
        return simpleDateFormat.format(rightNow.getTime());
    }
}
