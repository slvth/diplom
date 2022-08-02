package com.example.lifestyle.UI.Workout.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lifestyle.UI.Nutrition.BottomSheets.BottomSheetFragment;
import com.example.lifestyle.UI.Workout.WorkoutUI.AddActive.AddActiveFragment;

public class BottomSheetAddFragmentAdapter extends FragmentStateAdapter {

    public BottomSheetAddFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0)
            return new AddActiveFragment();
        return new BottomSheetFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
