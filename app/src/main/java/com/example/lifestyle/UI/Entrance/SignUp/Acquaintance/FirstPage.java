package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;


public class FirstPage extends Fragment {

    private static FirstPage instance;
    LinearLayout get_fitter, gain_muscle, lose_weight;
    int lose = 0, gain = 0, get = 0, goal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.first_page, container, false);

        lose_weight = v.findViewById(R.id.lose_weight);
        gain_muscle = v.findViewById(R.id.gain_muscle);
        get_fitter = v.findViewById(R.id.get_fitter);

        lose_weight.setOnClickListener(view -> {
            lose = 1;
            gain = 0;
            get = 0;
            goal = 1;
            ((AcquaintanceScreen) getActivity()).NextMethod();
        });
        get_fitter.setOnClickListener(view -> {
            gain = 0;
            get = 1;
            lose = 0;
            goal = 2;
            ((AcquaintanceScreen) getActivity()).NextMethod();
        });
        gain_muscle.setOnClickListener(view -> {
            gain = 1;
            get = 0;
            lose = 0;
            goal = 3;
            ((AcquaintanceScreen) getActivity()).NextMethod();
        });
        return v;
    }

    public static FirstPage GetInstance() {
        return instance;
    }
}