package com.example.lifestyle.UI.Nutrition.BottomSheets;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BottomSheetAllMeal extends BottomSheetDialogFragment {

    BottomSheetDialog dialog;
    BottomSheetBehavior bottomSheetBehavior;
    TextView name,kal,portionTXT,carbohydrate,fat,protein;
    EditText portionEdt;
    TextInputLayout portionET;
    RelativeLayout add_btn;
    int portion=1;

    DocumentReference df, diaryInf;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    SimpleDateFormat dateFormat2, dateFormat3;
    String myFormat2 = "yyyy-MM-dd", myFormat3 = "HH:mm:SS", value;
    Date dt;
    final Calendar myCalendar = Calendar.getInstance();

    public BottomSheetAllMeal() {
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
        View v = inflater.inflate(R.layout.bottom_sheet_all_meal, container, false);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        df = fStore.collection("users").document(user.getUid());
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());

        Bundle mArgs = getArguments();
        String myValue = mArgs.getString("name");
        Long kalS = mArgs.getLong("kal");

        Long proteinS = mArgs.getLong("protein");
        Long carbohydrateS = mArgs.getLong("carbohydrate");
        Long fatS = mArgs.getLong("fat");
        Long portionL = mArgs.getLong("portion");

        name=v.findViewById(R.id.name);
        kal=v.findViewById(R.id.kal_food);

        protein=v.findViewById(R.id.protein);
        carbohydrate=v.findViewById(R.id.carbohydrate);
        fat=v.findViewById(R.id.fat);
        TextView portionTV=v.findViewById(R.id.portionTXT);

        protein.setText(String.valueOf(proteinS));
        carbohydrate.setText(String.valueOf(carbohydrateS));
        fat.setText(String.valueOf(fatS));
        portionTV.setText(portionL+" x 100г");

        name.setText(myValue);
        kal.setText(kalS+" ккал");

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior=BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
    }
}