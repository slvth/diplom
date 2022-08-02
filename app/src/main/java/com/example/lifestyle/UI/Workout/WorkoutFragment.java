package com.example.lifestyle.UI.Workout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram.MyProgramFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class WorkoutFragment extends Fragment {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_workout, container, false);
        viewPager2 = v.findViewById(R.id.view_pager_workout2);
        tabLayout = v.findViewById(R.id.tab_layout_workout2);

        FragmentManager fragmentManager = getChildFragmentManager();
        MyProgramFragmentAdapter adapter =
                new MyProgramFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        return v;
    }
}


