package com.example.lifestyle.UI.Workout.Statistics;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StaticFragmentAdapter extends FragmentStateAdapter {


    public StaticFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0)
            return new FoodStaticFragment();
        return new WorkoutStaticFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
