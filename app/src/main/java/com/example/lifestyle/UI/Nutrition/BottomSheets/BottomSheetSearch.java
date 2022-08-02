package com.example.lifestyle.UI.Nutrition.BottomSheets;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.Diary.Add.AddMealScreen;
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

public class BottomSheetSearch extends BottomSheetDialogFragment {

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
    public BottomSheetSearch() {
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
        View v = inflater.inflate(R.layout.bottom_sheet_search, container, false);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        df = fStore.collection("users").document(user.getUid());
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());

        Bundle setDate = this.getArguments();
        Long currDate = setDate.getLong("date");
        value = setDate.getString("value");

        dt = new Date(currDate);
        diaryInf = df.collection("diary_info").document("" + dateFormat2.format(dt));
        DocumentReference diaryInfoFood = diaryInf.collection("diary_info_food").document("" + value + " " + dateFormat3.format(myCalendar.getTime()));

        Bundle mArgs = getArguments();
        String myValue = mArgs.getString("name");
        Long kalS = mArgs.getLong("kal");

        Long carbohydrateS = mArgs.getLong("carbohydrate");
        Long fatS = mArgs.getLong("fat");
        Long proteinS = mArgs.getLong("protein");

        name=v.findViewById(R.id.name);

        carbohydrate=v.findViewById(R.id.carbohydrate);
        fat=v.findViewById(R.id.fat);
        protein=v.findViewById(R.id.protein);

        add_btn=v.findViewById(R.id.add_btn);
        portionTXT=v.findViewById(R.id.portionTXT);
        portionET=v.findViewById(R.id.portionET);
        portionEdt=v.findViewById(R.id.portionEdt);
        kal=v.findViewById(R.id.kal);


        portionEdt.addTextChangedListener(new TextValidator(portionEdt) {
            @Override
            public void validate(TextView textView, String text) {
                String portionSTR=portionEdt.getText().toString();
                if (portionSTR.isEmpty()) {
                    portionTXT.setText("1 x 100г");
                    kal.setText(kalS*1+" ккал");
                    carbohydrate.setText(String.valueOf(carbohydrateS*1));
                    fat.setText(String.valueOf(fatS*1));
                    protein.setText(String.valueOf(proteinS*1));
                } else {
                    portion=Integer.parseInt(portionSTR);
                    if (portion<=0){
                        portionTXT.setText("1 x 100г");
                        kal.setText(kalS*1+" ккал");
                        carbohydrate.setText(String.valueOf(carbohydrateS*1));
                        fat.setText(String.valueOf(fatS*1));
                        protein.setText(String.valueOf(proteinS*1));
                    }else {
                        portionTXT.setText(portion+" x 100г");
                        kal.setText(kalS*portion+" ккал");
                        carbohydrate.setText(String.valueOf(carbohydrateS*portion));
                        fat.setText(String.valueOf(fatS*portion));
                        protein.setText(String.valueOf(proteinS*portion));
                    }
                    portionET.setError(null);
                }
            }
        });

        name.setText(myValue);
        kal.setText(kalS+" ккал");
        carbohydrate.setText(String.valueOf(carbohydrateS*1));
        fat.setText(String.valueOf(fatS*1));
        protein.setText(String.valueOf(proteinS*1));


        add_btn.setOnClickListener(view -> {
            String portionSTR=portionEdt.getText().toString();
            if (portionSTR.equals("0")) {
                portionET.setError("Порция не может быть меньше одной");
            }
            else {
                portion=Integer.parseInt(portionSTR);
                if (portion<=0){
                    portionET.setError("Порция не может быть меньше одной");
                    portionTXT.setText("1 x 100г");
                    kal.setText(kalS*1+" ккал");

                    carbohydrate.setText(String.valueOf(carbohydrateS*1));
                    fat.setText(String.valueOf(fatS*1));
                    protein.setText(String.valueOf(proteinS*1));
                }
                else {
                    portionTXT.setText(portion+" x 100г");
                    kal.setText(kalS*portion+" ккал");
                    carbohydrate.setText(String.valueOf(carbohydrateS*portion));
                    fat.setText(String.valueOf(fatS*portion));
                    protein.setText(String.valueOf(proteinS*portion));
                }
                portionET.setError(null);

                diaryInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String, Object> diaryInfo = new HashMap<>();
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Double carbohydrates_eat = document.getDouble("carbohydrates_eat");
                                Double fat_eat = document.getDouble("fat_eat");
                                Double protein_eat = document.getDouble("protein_eat");
                                Double kal_eat_doc = document.getDouble("kal_eat");

                                diaryInfo.put("protein_eat", protein_eat + proteinS*portion);
                                diaryInfo.put("fat_eat", fat_eat + fatS*portion);
                                diaryInfo.put("carbohydrates_eat", carbohydrates_eat + carbohydrateS*portion);
                                diaryInfo.put("kal_eat", kal_eat_doc + kalS*portion);
                                diaryInf.set(diaryInfo);
                            } else {
                                diaryInfo.put("protein_eat", proteinS*portion);
                                diaryInfo.put("fat_eat", fatS*portion);
                                diaryInfo.put("carbohydrates_eat",  carbohydrateS*portion);
                                diaryInfo.put("kal_eat", kalS*portion);
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
                            diaryInfoFoodMap.put("Description_food", myValue);
                            diaryInfoFoodMap.put("kal_food", kalS*portion);
                            diaryInfoFoodMap.put("carbohydrates_food", carbohydrateS*portion);
                            diaryInfoFoodMap.put("proteins_food", proteinS*portion);
                            diaryInfoFoodMap.put("fat_food", fatS*portion);

                            diaryInfoFoodMap.put("portion", portion);
                            diaryInfoFoodMap.put("portion_gr", 1);

                            diaryInfoFoodMap.put("meal_type", value);
                            diaryInfoFood.set(diaryInfoFoodMap);
                        } else {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();
                ((AddMealScreen)getContext()).Close();
                ((AddMealScreen)getContext()).search.setQuery("", false);
                ((AddMealScreen)getContext()).search.clearFocus();

            }
        });

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior=BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
    }
}