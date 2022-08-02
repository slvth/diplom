package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms.AllProgramsFragment;

public class MyProgramFragmentAdapter extends FragmentStateAdapter {

    public MyProgramFragmentAdapter(
            @NonNull FragmentManager fragmentManager,
            @NonNull Lifecycle lifecycle) {

        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0)
            return new MyProgramFragment();

        return new AllProgramsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}












