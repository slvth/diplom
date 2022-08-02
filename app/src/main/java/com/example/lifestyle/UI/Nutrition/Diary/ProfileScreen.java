package com.example.lifestyle.UI.Nutrition.Diary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.SplashScreen;
import com.example.lifestyle.UI.Workout.Statistics.StatisticActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    TextView name, goal;
    ImageView back;
    CircleImageView imgProfile;
    Uri imgUri;
    StorageReference storageReference;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageReference = FirebaseStorage.getInstance().getReference().child("user_images");

        fStore = FirebaseFirestore.getInstance();
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);
        imgProfile = findViewById(R.id.imgProfile);
        goal = findViewById(R.id.goal);
        FirebaseUser user = mAuth.getCurrentUser();

        imgProfile.setOnClickListener(view -> {
            choosePic();
        });
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference photoReference = storageReference.child("user_images/" + user.getUid());

        final long ONE_MEGABYTE = 1024 * 1024;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgProfile.setImageBitmap(bmp);
            }
        });

        DocumentReference df = fStore.collection("users").document(user.getUid());
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name.setText(document.getString("name"));
                    } else {
                        name.setText(document.getString("Пользователь"));
                    }
                } else {
                    name.setText("Пользователь");
                    Toast.makeText(ProfileScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DocumentReference workoutInf = df.collection("workout_info").document("1");
        workoutInf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.getLong("goal").intValue() == 1) {
                            goal.setText("Сбросить вес");
                        } else if (document.getLong("goal").intValue() == 2) {
                            goal.setText("Поддерживать вес");
                        } else if (document.getLong("goal").intValue() == 3) {
                            goal.setText("Набрать вес");
                        }
                    } else {
                        goal.setText("Цель");
                    }
                } else {
                    Toast.makeText(ProfileScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(view -> {
            finish();
        });


        Button btnSignOut = findViewById(R.id.btnSignOut);
        LinearLayout btnOpenStatic = findViewById(R.id.btnOpenStatic);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileScreen.this, SplashScreen.class));
                Toast.makeText(ProfileScreen.this, "Выход из учетной записи!", Toast.LENGTH_SHORT).show();
                ProfileScreen.this.finishAffinity();
            }
        });

        btnOpenStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileScreen.this, StatisticActivity.class));
            }
        });

    }

    private void choosePic() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            imgProfile.setImageURI(imgUri);
            uploadPic();
        }
    }

    private void uploadPic() {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Загрузка изображения...");
        pd.show();

        final String RandomKey = UUID.randomUUID().toString();
        StorageReference mountainsRef = storageReference.child("mountains.jpg");

        StorageReference mountainImagesRef = storageReference.child(user.getUid());

        mountainImagesRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(ProfileScreen.this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Прогресс " + (int) progress + "%");
            }
        });
        mountainsRef.getName().equals(mountainImagesRef.getName());
        mountainsRef.getPath().equals(mountainImagesRef.getPath());
    }
}