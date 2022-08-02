package com.example.lifestyle.UI.Nutrition.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.DiaryFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetFragment extends Fragment {

    BottomSheetDialog dialog;
    LinearLayout scale,breakfast,cashew,omelette,fried_rice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        scale=v.findViewById(R.id.scale);

        breakfast=v.findViewById(R.id.breakfast);
        cashew=v.findViewById(R.id.cashew);
        omelette=v.findViewById(R.id.omelette);
        fried_rice=v.findViewById(R.id.fried_rice);
        DiaryFragment diary = DiaryFragment.GetInstance();

        breakfast.setOnClickListener(view -> {
            diary.Zavtrak();
            dialog.dismiss();
        });
        cashew.setOnClickListener(view -> {
            diary.Perecus();
            dialog.dismiss();
        });
        omelette.setOnClickListener(view -> {
            diary.Obed();
            dialog.dismiss();
        });
        fried_rice.setOnClickListener(view -> {
            diary.Ujin();
            dialog.dismiss();
        });
        scale.setOnClickListener(view -> {
            diary.dialog();
            dialog.dismiss();
        });

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}