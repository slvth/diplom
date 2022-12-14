package com.example.lifestyle.UI.Workout.Statistics;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class WorkoutStaticFragment extends Fragment {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    FirebaseFirestore firestore;
    FirebaseUser _user;
    DocumentReference userRef;
    String _uid;
    int current_user_id;

    BarChart barChart;
    AppCompatRadioButton rb7day, rb30day, rb6month, rb1year;
    ArrayList<String> _theDates;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBarChart(7);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout_static, container, false);

        barChart = v.findViewById(R.id.bar_chart_statistics_workout);

        rb7day = v.findViewById(R.id.rb_7day_workout);
        rb30day = v.findViewById(R.id.rb_30day_workout);
        rb6month = v.findViewById(R.id.rb_6month_workout);
        rb1year = v.findViewById(R.id.rb_1year_workout);
        onRadioButtonClicked();

        databaseHelper = DatabaseHelper.getInstance(getContext());
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        firestore = FirebaseFirestore.getInstance();
        _user = FirebaseAuth.getInstance().getCurrentUser();
        _uid = _user.getUid();
        current_user_id = databaseHelper.getUserId(_uid);

        Button btnOpenDetails = v.findViewById(R.id.btnAllDataStaticWorkout);
        btnOpenDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryDetailActivity.class);
                intent.putExtra("food",false);
                startActivity(intent);
            }
        });

        getBarChart(7);

        return v;
    }

    private void getBarChart(int days) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());
        databaseHelper.recreateTotalHistories(current_user_id);

        String[] table = databaseHelper.getNameColumnTABLE_HISTORY_WORKOUT2();
        String viewName = table[0]; //???????????????? ??????????????????????????
        String columnDate = table[1]; //?????????????? ????????
        String columnCalorie = table[2]; //?????????????? ??????????????
        String columnUser = table[3]; //?????????????? user_id
        String columnTotal = "total";

        BarDataSet barDataSet;
        String _selectedQuery ="";

        String selectedQuery7days = " WITH week(date) AS (\n" +
                "  SELECT date('now', '-6 day') \n" +
                "  UNION ALL \n" +
                "  SELECT date(date, '+1 day') \n" +
                "  FROM week \n" +
                "  WHERE date < date('now','+3 hour') \n" +
                ")\n" +
                "SELECT TOTAL(t." + columnCalorie + ") " + columnTotal + ", \n" +
                "       strftime('%d/%m', w.date) " + columnDate + "\n" +
                "FROM week w LEFT JOIN " + viewName + " t\n" +
                "ON strftime('%Y-%m-%d', t." + columnDate + ") = w.date\n" +
                "GROUP BY strftime('%d/%m', w.date)\n" +
                "ORDER BY w.date ASC";

        String selectedQuery30days = " WITH week(date) AS (\n" +
                "  SELECT date('now', '-29 day') \n" +
                "  UNION ALL \n" +
                "  SELECT date(date, '+1 day') \n" +
                "  FROM week \n" +
                "  WHERE date < date('now','+3 hour') \n" +
                ")\n" +
                "SELECT TOTAL(t." + columnCalorie + ") " + columnTotal + ", \n" +
                "       strftime('%d.%m', w.date) " + columnDate + "\n" +
                "FROM week w LEFT JOIN " + viewName + " t\n" +
                "ON strftime('%Y-%m-%d', t." + columnDate + ") = w.date\n" +
                "GROUP BY strftime('%d.%m', w.date)\n" +
                "ORDER BY w.date ASC";

        String selectedQuery180days = " WITH week(date) AS (\n" +
                "  SELECT date('now', '-5 months') \n" +
                "  UNION ALL \n" +
                "  SELECT date(date, '+1 day') \n" +
                "  FROM week \n" +
                "  WHERE date < date('now','+3 hour') \n" +
                ")\n" +
                "SELECT TOTAL(t." + columnCalorie + ") " + columnTotal + ", \n" +
                "       strftime('%m.%Y', w.date) " + columnDate + "\n" +
                "FROM week w LEFT JOIN " + viewName + " t\n" +
                "ON strftime('%Y-%m-%d', t." + columnDate + ") = w.date\n" +
                "GROUP BY strftime('%m.%Y', w.date)\n" +
                "ORDER BY w.date ASC";

        String selectedQuery365days = " WITH week(date) AS (\n" +
                "  SELECT date('now', '-1 years','+1 months') \n" +
                "  UNION ALL \n" +
                "  SELECT date(date, '+1 day') \n" +
                "  FROM week \n" +
                "  WHERE date < date('now','+3 hour') \n" +
                ")\n" +
                "SELECT TOTAL(t." + columnCalorie + ") " + columnTotal + ", \n" +
                "       strftime('%m/%Y', w.date) " + columnDate + "\n" +
                "FROM week w LEFT JOIN " + viewName + " t\n" +
                "ON strftime('%Y-%m-%d', t." + columnDate + ") = w.date\n" +
                "GROUP BY strftime('%m/%Y', w.date)\n" +
                "ORDER BY w.date ASC";

        //?????????? ?????????????? ?????? ???????????? ???????????? ?? barchart
        if(days==7){
            _selectedQuery = selectedQuery7days;
        }
        else if(days==30){
            _selectedQuery = selectedQuery30days;
        }
        else if(days==180){
            _selectedQuery = selectedQuery180days;
        }
        else if(days==365){
            _selectedQuery = selectedQuery365days;
        }

        //???????????????????? ???????????????? ?? barchart
        barDataSet = new BarDataSet(getRowsQueryCursor(_selectedQuery,columnTotal,columnDate), "");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();

        //?????????????????? barchart
        //barDataSet.setDrawValues(false); //?????????????? ???????????????? ??????????????
        barData.setBarWidth(0.60f); //???????????? ??????????????
        barData.setValueTextSize(14f); //???????????? ???????????? ???????????????? ??????????????
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.getDescription().setEnabled(false); //?????????????? "Description Label"
        barChart.getXAxis().setDrawGridLines(false);
        barChart.setPinchZoom(true);

        XAxis xAxis = barChart.getXAxis(); //?????? X
        xAxis.setValueFormatter(new IndexAxisValueFormatter(_theDates)); //???????????? ?????????????????????? ???? ?????? X = ????????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //?????????????? ??????
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        //xAxis.setAxisMinimum(0); //?????????????????????? ???????????????? ??????????????????
        //xAxis.setDrawLabels(false); //?????????????? ???????????????? ??????
    }

    private List<BarEntry> getRowsQueryCursor(String query, String columnTotal, String columnDate){
        ArrayList<BarEntry> dataValuesBar = new ArrayList<>();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery(query, null);
        int index = 0;
        _theDates = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                dataValuesBar.add(new BarEntry(index++, cursor.getInt(cursor.getColumnIndexOrThrow(columnTotal))));
                _theDates.add(cursor.getString(cursor.getColumnIndexOrThrow(columnDate)));
            }
            while (cursor.moveToNext());
        }

        return dataValuesBar;
    }

    public void onRadioButtonClicked() {

        rb7day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7day.setTextColor(Color.WHITE);
                rb30day.setTextColor(Color.BLACK);
                rb6month.setTextColor(Color.BLACK);
                rb1year.setTextColor(Color.BLACK);

                getBarChart(7);
            }
        });

        rb30day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7day.setTextColor(Color.BLACK);
                rb30day.setTextColor(Color.WHITE);
                rb6month.setTextColor(Color.BLACK);
                rb1year.setTextColor(Color.BLACK);

                getBarChart(30);
            }
        });

        rb6month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7day.setTextColor(Color.BLACK);
                rb30day.setTextColor(Color.BLACK);
                rb6month.setTextColor(Color.WHITE);
                rb1year.setTextColor(Color.BLACK);

                getBarChart(180);
            }
        });

        rb1year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb7day.setTextColor(Color.BLACK);
                rb30day.setTextColor(Color.BLACK);
                rb6month.setTextColor(Color.BLACK);
                rb1year.setTextColor(Color.WHITE);

                getBarChart(365);
            }
        });
    }


}