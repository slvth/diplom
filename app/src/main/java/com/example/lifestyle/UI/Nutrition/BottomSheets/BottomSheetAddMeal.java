package com.example.lifestyle.UI.Nutrition.BottomSheets;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.TextValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BottomSheetAddMeal extends BottomSheetDialogFragment {

    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetDialog dialog;
    ImageView close_bot_sheet;
    RelativeLayout add_btn;
    double protein_eat = 0, fat_eat = 0, carbohydrates_eat = 0, kal_eat_doc = 0;
    EditText descriptionEdt, kalEdt, carbEdt, proteinEdt, fatEdt;
    TextInputLayout descriptionET, kalET, carbET, proteinET, fatET;
    DocumentReference df, diaryInf;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    SimpleDateFormat dateFormat2, dateFormat3;
    String myFormat2 = "yyyy-MM-dd", myFormat3 = "HH:mm:SS", value;
    final Calendar myCalendar = Calendar.getInstance();
    Date dt;

    public BottomSheetAddMeal() {
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
        View v = inflater.inflate(R.layout.bottom_sheet_add_meal, container, false);
        close_bot_sheet = v.findViewById(R.id.close_bot_sheet);

        descriptionEdt = v.findViewById(R.id.descriptionEdt);
        add_btn = v.findViewById(R.id.add_btn);
        kalEdt = v.findViewById(R.id.kalEdt);
        carbEdt = v.findViewById(R.id.carbEdt);
        proteinEdt = v.findViewById(R.id.proteinEdt);
        fatEdt = v.findViewById(R.id.fatEdt);
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());

        descriptionET = v.findViewById(R.id.descriptionET);
        kalET = v.findViewById(R.id.kalET);
        carbET = v.findViewById(R.id.carbET);
        proteinET = v.findViewById(R.id.proteinET);
        fatET = v.findViewById(R.id.fatET);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        df = fStore.collection("users").document(user.getUid());

        Bundle setDate = this.getArguments();
        Long currDate = setDate.getLong("date");
        value = setDate.getString("value");

        dt = new Date(currDate);

        diaryInf = df.collection("diary_info").document("" + dateFormat2.format(dt));

        add_btn.setOnClickListener(view -> {
            AddMeal();
        });
        close_bot_sheet.setOnClickListener(view1 -> {
            dialog.dismiss();
        });

        descriptionEdt.addTextChangedListener(new TextValidator(descriptionEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String descSTR = descriptionEdt.getText().toString();
                if (descSTR.isEmpty()) {
                    descriptionET.setError("Поле не может быть пустым");
                } else {
                    descriptionET.setError(null);
                }
            }
        });
        kalEdt.addTextChangedListener(new TextValidator(kalEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String descSTR = kalEdt.getText().toString();
                if (descSTR.isEmpty()) {
                    kalET.setError("Поле не может быть пустым");
                } else {
                    kalET.setError(null);
                }
            }
        });
        carbEdt.addTextChangedListener(new TextValidator(carbEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String descSTR = carbEdt.getText().toString();
                if (descSTR.isEmpty()) {
                    carbET.setError("Поле не может быть пустым");
                } else {
                    carbET.setError(null);
                }
            }
        });
        proteinEdt.addTextChangedListener(new TextValidator(proteinEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String descSTR = proteinEdt.getText().toString();
                if (descSTR.isEmpty()) {
                    proteinET.setError("Поле не может быть пустым");
                } else {
                    proteinET.setError(null);
                }
            }
        });
        fatEdt.addTextChangedListener(new TextValidator(fatEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String descSTR = fatEdt.getText().toString();
                if (descSTR.isEmpty()) {
                    fatET.setError("Поле не может быть пустым");
                } else {
                    fatET.setError(null);
                }
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomSheetBehavior=BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight((int)(Resources.getSystem().getDisplayMetrics().heightPixels));

    }

    public void AddMeal() {
        String descSTR = descriptionEdt.getText().toString();
        String kalSTR = kalEdt.getText().toString();
        String carbSTR = carbEdt.getText().toString();
        String proteinSTR = proteinEdt.getText().toString();
        String fatSTR = fatEdt.getText().toString();
        if (descSTR.isEmpty() || kalSTR.isEmpty() || carbSTR.isEmpty() || proteinSTR.isEmpty() || fatSTR.isEmpty()) {
            if (descSTR.isEmpty()) {
                descriptionET.setError("Поле не может быть пустым");
            } else {
                descriptionET.setError(null);
            }
            if (kalSTR.isEmpty()) {
                kalET.setError("Поле не может быть пустым");
            } else {
                kalET.setError(null);
            }
            if (carbSTR.isEmpty()) {
                carbET.setError("Поле не может быть пустым");
            } else {
                carbET.setError(null);
            }
            if (proteinSTR.isEmpty()) {
                proteinET.setError("Поле не может быть пустым");
            } else {
                proteinET.setError(null);
            }
            if (fatSTR.isEmpty()) {
                fatET.setError("Поле не может быть пустым");
            } else {
                fatET.setError(null);
            }
        } else {
            DocumentReference diaryInfoFood = diaryInf.collection("diary_info_food").document("" + value + " " + dateFormat3.format(myCalendar.getTime()));

            diaryInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            carbohydrates_eat = document.getDouble("carbohydrates_eat");
                            fat_eat = document.getDouble("fat_eat");
                            protein_eat = document.getDouble("protein_eat");
                            kal_eat_doc = document.getDouble("kal_eat");

                            diaryInfo.put("protein_eat", protein_eat + Integer.parseInt(proteinSTR));
                            diaryInfo.put("fat_eat", fat_eat + Integer.parseInt(fatSTR));
                            diaryInfo.put("carbohydrates_eat", carbohydrates_eat + Integer.parseInt(carbSTR));
                            diaryInfo.put("kal_eat", kal_eat_doc + Integer.parseInt(kalSTR));
                            diaryInf.set(diaryInfo);
                        } else {
                            diaryInfo.put("protein_eat", Integer.parseInt(proteinSTR));
                            diaryInfo.put("fat_eat", Integer.parseInt(fatSTR));
                            diaryInfo.put("carbohydrates_eat", Integer.parseInt(carbSTR));
                            diaryInfo.put("kal_eat", Integer.parseInt(kalSTR));
                            diaryInf.set(diaryInfo);
                        }

                    } else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            diaryInfoFood.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                        diaryInfoFoodMap.put("Description_food", descriptionEdt.getText().toString());
                        diaryInfoFoodMap.put("kal_food", Integer.parseInt(kalEdt.getText().toString()));
                        diaryInfoFoodMap.put("carbohydrates_food", Integer.parseInt(carbEdt.getText().toString()));
                        diaryInfoFoodMap.put("proteins_food", Integer.parseInt(proteinEdt.getText().toString()));
                        diaryInfoFoodMap.put("fat_food", Integer.parseInt(fatEdt.getText().toString()));

                        diaryInfoFoodMap.put("portion", 1);
                        diaryInfoFoodMap.put("portion_gr", 1);

                        diaryInfoFoodMap.put("meal_type", value);
                        diaryInfoFood.set(diaryInfoFoodMap);
                    } else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.dismiss();
        }
    }
}