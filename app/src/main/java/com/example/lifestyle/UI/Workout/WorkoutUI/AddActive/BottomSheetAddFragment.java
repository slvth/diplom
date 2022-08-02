package com.example.lifestyle.UI.Workout.WorkoutUI.AddActive;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Workout.Adapters.BottomSheetAddFragmentAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;


public class BottomSheetAddFragment extends BottomSheetDialogFragment {

    BottomSheetBehavior<View> bottomSheetBehavior;
    BottomSheetDialog dialog;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    public BottomSheetAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet_add, container, false);

        viewPager2 = v.findViewById(R.id.view_pager_add);
        tabLayout = v.findViewById(R.id.tab_layout_add);

        FragmentManager fragmentManager = getChildFragmentManager();
        BottomSheetAddFragmentAdapter adapter = new BottomSheetAddFragmentAdapter(fragmentManager,getLifecycle());
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setMaxHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels*0.80));

        CoordinatorLayout layout = dialog.findViewById(R.id.bottom_sheet_add_layout);
        assert layout != null;
        layout.setMinimumHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels));
    }

}