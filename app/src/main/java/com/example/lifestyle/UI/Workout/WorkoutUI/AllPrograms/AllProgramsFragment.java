package com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.UI.Workout.Adapters.AllProgramsRVAdapter;

import java.util.ArrayList;


public class AllProgramsFragment extends Fragment {

    RecyclerView rvAllPrograms;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_programs, container, false);

        databaseHelper =DatabaseHelper.getInstance(getContext());
        rvAllPrograms = v.findViewById(R.id.all_programs_recycler_view2);

        getDataForRV(v);

        return v;
    }

    private void  getDataForRV(View v){
        rvAllPrograms.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<LevelModel> levels = databaseHelper.getAllLevels();
        AllProgramsRVAdapter adapter = new AllProgramsRVAdapter(v.getContext(), levels);
        rvAllPrograms.setAdapter(adapter);
    }

}