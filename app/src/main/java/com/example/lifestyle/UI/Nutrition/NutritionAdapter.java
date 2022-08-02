package com.example.lifestyle.UI.Nutrition;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lifestyle.UI.Nutrition.PlanPage.PlanFragment;
import com.example.lifestyle.UI.Nutrition.RecipePage.RecipeFragment;

public class NutritionAdapter extends FragmentStateAdapter {


    public NutritionAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new RecipeFragment();
        }
        return new PlanFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
