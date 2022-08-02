package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;


public class SecondPage extends Fragment {

    LinearLayout female, male;
    int gender;
    private static SecondPage instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.second_page, container, false);

        male = v.findViewById(R.id.male);
        female = v.findViewById(R.id.female);

        male.setOnClickListener(view -> {
            gender = 1;
            ((AcquaintanceScreen) getActivity()).NextMethod();
        });
        female.setOnClickListener(view -> {
            gender = 2;
            ((AcquaintanceScreen) getActivity()).NextMethod();
        });

        return v;
    }

    public static SecondPage GetInstance() {
        return instance;
    }
}