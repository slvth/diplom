package com.example.lifestyle.UI.Nutrition.PlanPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlanAdapter extends FragmentStateAdapter {
    String meal_type="breakfast";
    Bundle args = new Bundle();
    public PlanAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                args.putString("meal_type", "lunch");
                return new LunchPage();
            case 2:
                args.putString("meal_type", "dinner");
                return new DinnerPage();
        }
        args.putString("meal_type", "breakfast");
        return new BreakfastPage();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
