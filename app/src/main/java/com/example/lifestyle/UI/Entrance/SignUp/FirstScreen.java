package com.example.lifestyle.UI.Entrance.SignUp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.MainScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirstScreen extends AppCompatActivity {

    Button start;
    double formula, c, d, e;
    ProgressBar progress1, progress2, progress3, progress4;
    String formulaStr;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.my6));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        TextView title_plan = findViewById(R.id.title_plan);
        TextView one1 = findViewById(R.id.one1);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        Map<String, Object> userInfo = new HashMap<>();
        DocumentReference df = fStore.collection("users").document(user.getUid());
        DocumentReference workoutInfo = df.collection("workout_info").document("1");
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        title_plan.setText(document.getString("name") + ", ваш персональный план здоровья готов!");
                    } else {
                        Toast.makeText(FirstScreen.this, "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FirstScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        workoutInfo.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        one1.setText(String.valueOf(Math.round(document.getDouble("carbohydrates")) * 4 + Math.round(document.getDouble("protein")) * 4 + Math.round(document.getDouble("fat")) * 9) + " ккал");
                    } else {
                        Toast.makeText(FirstScreen.this, "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FirstScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        progress1 = findViewById(R.id.progressBar1);
        progress2 = findViewById(R.id.progressBar2);
        progress3 = findViewById(R.id.progressBar3);
        progress4 = findViewById(R.id.progressBar4);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progress1.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my1)));
            progress2.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my3)));
            progress3.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my5)));
            progress4.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.my6)));
        }

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstScreen.this, MainScreen.class));
                finishAffinity();
            }
        });

    }
}