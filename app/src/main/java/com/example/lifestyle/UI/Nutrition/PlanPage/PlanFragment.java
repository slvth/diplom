package com.example.lifestyle.UI.Nutrition.PlanPage;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lifestile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlanFragment extends Fragment {
    private static PlanFragment instance;

    final Calendar myCalendar3 = Calendar.getInstance();
    final Calendar myCalendar4 = Calendar.getInstance();
    final Calendar myCalendar5 = Calendar.getInstance();
    final Calendar myCalendar6 = Calendar.getInstance();
    final Calendar myCalendar7 = Calendar.getInstance();
    TextView today1, today2, today3, today4, today5, today6, today7;
    SimpleDateFormat dateFormat3;
    String myFormat3 = "EEEE, d LLL";
    TabLayout tab_layout1, tab_layout2, tab_layout3, tab_layout4, tab_layout5, tab_layout6, tab_layout7;
    ViewPager2 pager21, pager22, pager23, pager24, pager25, pager26, pager27;
    PlanAdapter planAdapter1, planAdapter2, planAdapter3, planAdapter4, planAdapter5, planAdapter6, planAdapter7;
    LinearLayout f1, f2, f3, f4, f5, f6, f7;
    TextView f1S, f2S, f3S, f4S, f5S, f6S, f7S;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    CollectionReference collectionReference;
    String meal_type;
    int p1 = 1, p2 = 2, p3 = 3, p4 = 4, p5 = 5, p6 = 6, p7 = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.fragment_plan, container, false);
        f1 = v.findViewById(R.id.f1);
        f2 = v.findViewById(R.id.f2);
        f3 = v.findViewById(R.id.f3);
        f4 = v.findViewById(R.id.f4);
        f5 = v.findViewById(R.id.f5);
        f6 = v.findViewById(R.id.f6);
        f7 = v.findViewById(R.id.f7);
        Button button = v.findViewById(R.id.btn);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        collectionReference = fStore.collection("meal");

        Datee(v);
        TabLayoutM(v);

        CollectionM1();
        CollectionM2();
        CollectionM3();
        CollectionM4();
        CollectionM5();
        CollectionM6();
        CollectionM7();

        button.setOnClickListener(view -> {

            onTimeSet();
        });

        /*DocumentReference diar1=fStore.collection("meal").document("banano_oreh");
        DocumentReference diar2=fStore.collection("meal").document("farsh");
        DocumentReference diar3=fStore.collection("meal").document("ejevika");
        DocumentReference diar4=fStore.collection("meal").document("fasole");
        DocumentReference diar5=fStore.collection("meal").document("grech");
        DocumentReference diar6=fStore.collection("meal").document("gulash");
        DocumentReference diar7=fStore.collection("meal").document("jarenaya kartoshka");
        DocumentReference diar8=fStore.collection("meal").document("kabachok");
        DocumentReference diar9=fStore.collection("meal").document("kapreze");
        DocumentReference diar10=fStore.collection("meal").document("kartoshka");
        DocumentReference diar11=fStore.collection("meal").document("klubnika");
        DocumentReference diar12=fStore.collection("meal").document("kurica");
        DocumentReference diar13=fStore.collection("meal").document("makarons");
        DocumentReference diar14=fStore.collection("meal").document("malina");
        DocumentReference diar15=fStore.collection("meal").document("mango");
        DocumentReference diar16=fStore.collection("meal").document("nisuaz");
        DocumentReference diar17=fStore.collection("meal").document("ovsanka");
        DocumentReference diar18=fStore.collection("meal").document("ramen");
        DocumentReference diar19=fStore.collection("meal").document("ratatuy");
        DocumentReference diar20=fStore.collection("meal").document("smuzi");
        DocumentReference diar21=fStore.collection("meal").document("tushonaya kartoshka");
        diar1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 158);
                    diaryInfoFoodMap.put("fat", 6);
                    diaryInfoFoodMap.put("protein", 13);
                    diaryInfoFoodMap.put("carbohydrate", 13);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fbanana.jpg?alt=media&token=89c3de42-60b5-4a7e-a266-1be1d596be61");
                    diaryInfoFoodMap.put("name", "Бананово-ореховый шейк");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar1.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Ffarsh.jpg?alt=media&token=449594e1-0607-4f43-889f-dbdb23e8cdc2");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar2.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 325);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fkoktel.jpg?alt=media&token=cf4636db-1352-41b7-807e-82bc5db330c8");
                    diaryInfoFoodMap.put("name", "Коктейль из ежевики");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar3.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar4.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 342);
                    diaryInfoFoodMap.put("fat", 10);
                    diaryInfoFoodMap.put("protein", 25);
                    diaryInfoFoodMap.put("carbohydrate", 38);
                    diaryInfoFoodMap.put("time",45 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Ffasole.jpg?alt=media&token=2b75825f-7ca9-4d00-9be9-0b64d7a27469");
                    diaryInfoFoodMap.put("name", "Томатно фасолевый суп");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Сложный");
                    diaryInfoFoodMap.put("type", "");

                    diar4.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar5.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 226);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",7 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fgrech.jpg?alt=media&token=a9fa16f6-4ade-4c6d-8dc6-c3aabd678fb2");
                    diaryInfoFoodMap.put("name", "Греческий салат с сиртаки");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar5.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar6.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fgulash.jpg?alt=media&token=259434c0-1dd4-4d10-863c-6f3ac7586cd9");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar6.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar7.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fjar.jpg?alt=media&token=420dfff1-ec78-471d-8342-0529c2666cee");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar7.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar8.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 169);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",25 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fkabachok.jpg?alt=media&token=271a7474-8e60-412a-9c6c-5ac66e522f5b");
                    diaryInfoFoodMap.put("name", "Суп из кускуса с кабачком");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Средний");
                    diaryInfoFoodMap.put("type", "");

                    diar8.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar9.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 321);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fkaprese.jpg?alt=media&token=ebd3f540-bf99-4d60-9b41-64ef04b3402b");
                    diaryInfoFoodMap.put("name", "Салат капрезе");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar9.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar10.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 104);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",35 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fkartoshka.jpg?alt=media&token=fea1cd06-26fb-4427-a8a9-d9e464172713");
                    diaryInfoFoodMap.put("name", "Картофельный суп с горошком");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Средний");
                    diaryInfoFoodMap.put("type", "");

                    diar10.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar11.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 260);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",10 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fklubnika.jpg?alt=media&token=6654b914-03f9-4a77-9468-269f1facaa47");
                    diaryInfoFoodMap.put("name", "Французский тост с клубникой");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar11.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar12.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 304);
                    diaryInfoFoodMap.put("fat", 12);
                    diaryInfoFoodMap.put("protein", 23);
                    diaryInfoFoodMap.put("carbohydrate", 26);
                    diaryInfoFoodMap.put("time",25 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fbrokoli.jpg?alt=media&token=b10365d9-717a-463b-8594-a0691ff8607b");
                    diaryInfoFoodMap.put("name", "Курица с грибами и брокколи");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Средний");
                    diaryInfoFoodMap.put("type", "");

                    diar12.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar13.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fmakarons.jpg?alt=media&token=08fba9b5-fbcb-41b4-b6de-4d783cb00c70");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar13.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar14.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 467);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",10 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fmalina.jpg?alt=media&token=3eab0f83-b94e-4bd1-b578-614497f9cd4d");
                    diaryInfoFoodMap.put("name", "Бодрящий булгур с малиной");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar14.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar15.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 193);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fmango.jpg?alt=media&token=4e3bbee0-18d9-462b-8d82-f8374aca505d");
                    diaryInfoFoodMap.put("name", "Манговый ласси");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar15.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar16.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 560);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",14 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fnisuaz.jpg?alt=media&token=d5e10ca8-1a2a-4bb3-b9e8-f686f8d4188e");
                    diaryInfoFoodMap.put("name", "Салат нисуаз");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar16.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar17.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 215);
                    diaryInfoFoodMap.put("fat", 11);
                    diaryInfoFoodMap.put("protein", 15);
                    diaryInfoFoodMap.put("carbohydrate", 14);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fovsanka.jpg?alt=media&token=0ea15088-fe90-4f87-af03-f422dbe0346a");
                    diaryInfoFoodMap.put("name", "Овсяная каша");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "Легкий");
                    diaryInfoFoodMap.put("type", "");

                    diar17.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar18.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Framen.jpeg?alt=media&token=7e4d495b-35ca-4085-8a98-8a88937ce3ba");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar18.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar19.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Fratatui.jpg?alt=media&token=e58b4640-b67a-4245-9f60-9594ed69fe4c");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar19.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar20.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 189);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",5 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Ftop_smuzi.jpg?alt=media&token=bc3cb40f-5699-4cc7-bea9-7dfd08084eb6");
                    diaryInfoFoodMap.put("name", "Смузи из кефира с бананом и черникой");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar20.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        diar21.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> diaryInfoFoodMap = new HashMap<>();
                    diaryInfoFoodMap.put("kal", 0);
                    diaryInfoFoodMap.put("fat", 0);
                    diaryInfoFoodMap.put("protein", 0);
                    diaryInfoFoodMap.put("carbohydrate", 0);
                    diaryInfoFoodMap.put("time",0 );
                    diaryInfoFoodMap.put("img_meal", "https://firebasestorage.googleapis.com/v0/b/lifestyleproject-d1fbd.appspot.com/o/meal_images%2Ftushonka.jpg?alt=media&token=c03d03ea-311d-4ef5-b192-ad4ccd23ff37");
                    diaryInfoFoodMap.put("name", "");
                    diaryInfoFoodMap.put("description1", "");
                    diaryInfoFoodMap.put("description2", "");
                    diaryInfoFoodMap.put("description3", "");
                    diaryInfoFoodMap.put("complexity", "");
                    diaryInfoFoodMap.put("type", "");

                    diar21.update(diaryInfoFoodMap);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        return v;
    }

    public void onTimeSet() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 15);
        c.set(Calendar.MINUTE, 35);
        c.set(Calendar.SECOND, 0);
        long timeToWait = System.currentTimeMillis() - c.getTimeInMillis();
        if (timeToWait > 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CollectionReference collectionReference = fStore.collection("meal");
                    array();

                    CollectionM1();
                    CollectionM2();
                    CollectionM3();
                    CollectionM4();
                    CollectionM5();
                    CollectionM6();
                    CollectionM7();
                }
            }, timeToWait);
        }
    }

    private void array() {

        Query meal_plan_update1 = collectionReference.whereEqualTo("id", p1);
        Query meal_plan_update2 = collectionReference.whereEqualTo("id", p2);
        Query meal_plan_update3 = collectionReference.whereEqualTo("id", p3);
        Query meal_plan_update4 = collectionReference.whereEqualTo("id", p4);
        Query meal_plan_update5 = collectionReference.whereEqualTo("id", p5);
        Query meal_plan_update6 = collectionReference.whereEqualTo("id", p6);
        Query meal_plan_update7 = collectionReference.whereEqualTo("id", p7);

        ArrayList<Integer> seriesOfNumbers = new ArrayList<Integer>();

        int reset_p1 = p1;
        p1 = p2;
        p2 = p3;
        p3 = p4;
        p4 = p5;
        p5 = p6;
        p6 = p7;
        p7 = reset_p1;

        meal_plan_update1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p1);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p2);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update3.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p3);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update4.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p4);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update5.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p5);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update6.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p6);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        meal_plan_update7.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> diaryInfo = new HashMap<>();
                        diaryInfo.put("id", p7);
                        collectionReference.document(document.getId()).update(diaryInfo);
                    }

                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Toast.makeText(getContext(), "" + p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7, Toast.LENGTH_SHORT).show();
    }

    private void CollectionM1() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p1);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f1.findViewById(R.id.img_top_meal);
                        TextView name1 = f1.findViewById(R.id.name);
                        TextView kal1 = f1.findViewById(R.id.kal);
                        TextView time1 = f1.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM2() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p2);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f2.findViewById(R.id.img_top_meal);
                        TextView name1 = f2.findViewById(R.id.name);
                        TextView kal1 = f2.findViewById(R.id.kal);
                        TextView time1 = f2.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM3() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p3);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f3.findViewById(R.id.img_top_meal);
                        TextView name1 = f3.findViewById(R.id.name);
                        TextView kal1 = f3.findViewById(R.id.kal);
                        TextView time1 = f3.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM4() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p4);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f4.findViewById(R.id.img_top_meal);
                        TextView name1 = f4.findViewById(R.id.name);
                        TextView kal1 = f4.findViewById(R.id.kal);
                        TextView time1 = f4.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM5() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p5);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f5.findViewById(R.id.img_top_meal);
                        TextView name1 = f5.findViewById(R.id.name);
                        TextView kal1 = f5.findViewById(R.id.kal);
                        TextView time1 = f5.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM6() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p6);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f6.findViewById(R.id.img_top_meal);
                        TextView name1 = f6.findViewById(R.id.name);
                        TextView kal1 = f6.findViewById(R.id.kal);
                        TextView time1 = f6.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CollectionM7() {
        Query plan = collectionReference;
        plan = collectionReference.whereEqualTo("meal_type", meal_type).whereEqualTo("id", p7);
        plan.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String nameS = document.getString("name");
                        String kalS = document.getLong("kal").toString();
                        String timeS = document.getLong("time").toString();
                        String img_top_mealS = document.getString("img_meal");

                        ShapeableImageView img_top_meal1 = f7.findViewById(R.id.img_top_meal);
                        TextView name1 = f7.findViewById(R.id.name);
                        TextView kal1 = f7.findViewById(R.id.kal);
                        TextView time1 = f7.findViewById(R.id.time);
                        Picasso.get().load(img_top_mealS).into(img_top_meal1);
                        time1.setText(timeS + " мин");
                        kal1.setText(kalS + " ккал");
                        name1.setText(nameS);
                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void TabLayoutM(View v) {
        tab_layout1 = f1.findViewById(R.id.tab_layout);
        tab_layout2 = f2.findViewById(R.id.tab_layout);
        tab_layout3 = f3.findViewById(R.id.tab_layout);
        tab_layout4 = f4.findViewById(R.id.tab_layout);
        tab_layout5 = f5.findViewById(R.id.tab_layout);
        tab_layout6 = f6.findViewById(R.id.tab_layout);
        tab_layout7 = f7.findViewById(R.id.tab_layout);

        pager21 = f1.findViewById(R.id.pager2);
        pager22 = f2.findViewById(R.id.pager2);
        pager23 = f3.findViewById(R.id.pager2);
        pager24 = f4.findViewById(R.id.pager2);
        pager25 = f5.findViewById(R.id.pager2);
        pager26 = f6.findViewById(R.id.pager2);
        pager27 = f7.findViewById(R.id.pager2);

        planAdapter1 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter2 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter3 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter4 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter5 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter6 = new PlanAdapter(getFragmentManager(), getLifecycle());
        planAdapter7 = new PlanAdapter(getFragmentManager(), getLifecycle());

        pager21.setAdapter(planAdapter1);
        pager22.setAdapter(planAdapter2);
        pager23.setAdapter(planAdapter3);
        pager24.setAdapter(planAdapter4);
        pager25.setAdapter(planAdapter5);
        pager26.setAdapter(planAdapter6);
        pager27.setAdapter(planAdapter7);

        meal_type = "breakfast";
        tab_layout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager21.setCurrentItem(tab.getPosition());

                onTimeSet();
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM1();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager22.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM2();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager23.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM3();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout4.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager24.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM4();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout5.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager25.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM5();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout6.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager26.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM6();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab_layout7.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager27.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    meal_type = "breakfast";
                } else if (tab.getPosition() == 1) {
                    meal_type = "lunch";
                } else if (tab.getPosition() == 2) {
                    meal_type = "dinner";
                }
                CollectionM7();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager21.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout1.selectTab(tab_layout1.getTabAt(position));
            }
        });
        pager22.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout2.selectTab(tab_layout2.getTabAt(position));
            }
        });
        pager23.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout3.selectTab(tab_layout3.getTabAt(position));
            }
        });
        pager24.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout4.selectTab(tab_layout4.getTabAt(position));
            }
        });
        pager25.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout5.selectTab(tab_layout5.getTabAt(position));
            }
        });
        pager26.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout6.selectTab(tab_layout6.getTabAt(position));
            }
        });
        pager27.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout7.selectTab(tab_layout7.getTabAt(position));
            }
        });
    }

    private void Datee(View v) {
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());

        myCalendar3.add(Calendar.DAY_OF_MONTH, 2);
        myCalendar4.add(Calendar.DAY_OF_MONTH, 3);
        myCalendar5.add(Calendar.DAY_OF_MONTH, 4);
        myCalendar6.add(Calendar.DAY_OF_MONTH, 5);
        myCalendar7.add(Calendar.DAY_OF_MONTH, 6);

        f1S = f1.findViewById(R.id.day);
        f2S = f2.findViewById(R.id.day);
        f3S = f3.findViewById(R.id.day);
        f4S = f4.findViewById(R.id.day);
        f5S = f5.findViewById(R.id.day);
        f6S = f6.findViewById(R.id.day);
        f7S = f7.findViewById(R.id.day);

        f1S.setText("Сегодня");
        f2S.setText("Завтра");
        f3S.setText(dateFormat3.format(myCalendar3.getTime()));
        f4S.setText(dateFormat3.format(myCalendar4.getTime()));
        f5S.setText(dateFormat3.format(myCalendar5.getTime()));
        f6S.setText(dateFormat3.format(myCalendar6.getTime()));
        f7S.setText(dateFormat3.format(myCalendar7.getTime()));

    }

    public static PlanFragment GetInstance() {
        return instance;
    }
}