package com.example.lifestyle.UI.Nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lifestile.R;
import com.google.android.material.tabs.TabLayout;

public class NutritionFragment extends Fragment {

    TabLayout tab_layout;
    ViewPager2 pager2;
    NutritionAdapter nutritionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nutrition, container, false);

        tab_layout = v.findViewById(R.id.tab_layout);
        pager2 = v.findViewById(R.id.pager2);

        nutritionAdapter = new NutritionAdapter(getFragmentManager(), getLifecycle());
        pager2.setAdapter(nutritionAdapter);
        pager2.setUserInputEnabled(false);
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout.selectTab(tab_layout.getTabAt(position));
            }
        });
        return v;
    }
}