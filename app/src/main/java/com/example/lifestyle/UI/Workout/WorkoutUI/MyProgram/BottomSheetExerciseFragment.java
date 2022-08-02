package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ExerciseModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import pl.droidsonroids.gif.GifImageView;


public class BottomSheetExerciseFragment extends BottomSheetDialogFragment {

    BottomSheetBehavior<View> bottomSheetBehavior;
    BottomSheetDialog dialog;

    public BottomSheetExerciseFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet_exercise, container, false);

        Bundle arguments = getArguments();
        if(arguments!=null){
            ExerciseModel exercise = (ExerciseModel) arguments.getSerializable(ExerciseModel.class.getSimpleName());
            GifImageView gifImageView = v.findViewById(R.id.imgExerciseBottomSheet);
            gifImageView.setImageResource(exercise.getImage());

            TextView txtNameExercise = v.findViewById(R.id.txtNameExerciseBottomSheet);
            txtNameExercise.setText(exercise.getName());
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setMaxHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels*0.80));

        CoordinatorLayout layout = dialog.findViewById(R.id.bottom_sheet_exercise_layout);
        assert layout != null;
        layout.setMinimumHeight((int) (Resources.getSystem().getDisplayMetrics().heightPixels));
    }
}