package com.example.lifestyle.UI.Workout.WorkoutUI.AddActive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.ActivityModel;
import com.example.lifestyle.UI.Workout.Adapters.AddActiveFragmentAdapter;
import com.example.lifestyle.UI.Workout.Custom.DebounceClickListener;

import java.util.ArrayList;


public class AddActiveFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_active, container, false);

        Context context = v.getContext();
        ListView activityList = v.findViewById(R.id.add_active_list);
        View footer = getLayoutInflater().inflate(R.layout.footer_listview, null);

        //список активностей
        ArrayList<ActivityModel> activities = DatabaseHelper.getInstance(context).getAllActivity();
        AddActiveFragmentAdapter adapter =
                new AddActiveFragmentAdapter(context, R.layout.item_add_active, activities);

        //отображение активностей в activityList
        activityList.setAdapter(adapter);
        activityList.addFooterView(footer,null, false);

        //обработка нажатия на элемент списка
        activityList.setOnItemClickListener(new DebounceClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(context, DetailAddActiveActivity.class);
            intent.putExtra(ActivityModel.class.getSimpleName(), activities.get(i));
            startActivity(intent);
        }));

        return v;
    }


}