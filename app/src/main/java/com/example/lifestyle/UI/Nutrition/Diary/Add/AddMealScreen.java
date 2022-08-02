package com.example.lifestyle.UI.Nutrition.Diary.Add;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.BottomSheets.BottomSheetAddMeal;
import com.example.lifestyle.UI.Nutrition.Diary.Add.Search.SearchAdapter;
import com.example.lifestyle.UI.Nutrition.Diary.Add.Search.SearchModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMealScreen extends AppCompatActivity {

    ImageView close;
    Button enter;
    long kal_eat_doc;
    public TextView meal;
    public String value;
    LinearLayout manual;
    public Date dt;
    FirebaseFirestore db;
    RecyclerView mlistViewMeal,mlistViewMeal2;

    ArrayList<MealModel> arrayList;
    ArrayList<SearchModel> arrayList2;

    MealAdapter mealAdapter;

    SearchAdapter mealAdapter2;

    DocumentReference df, workoutInf;
    FirebaseUser user;
    TextView all_kal;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DocumentReference diaryInfoFood, diaryInf;
    SimpleDateFormat dateFormat2, dateFormat3;
    String myFormat2 = "yyyy-MM-dd", myFormat3 = "HH:mm:SS";
    final Calendar myCalendar = Calendar.getInstance();
    CollectionReference collectionReference;
    public SearchView search;
    LinearLayout manual_LL,head1;

    Boolean closing=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.my1));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal_screen);
        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat3 = new SimpleDateFormat(myFormat3, Locale.getDefault());

        dt = new Date(getIntent().getLongExtra("date_time", -1));

        inital();

        mlistViewMeal.setLayoutManager(new LinearLayoutManager(this));
        mlistViewMeal2.setLayoutManager(new LinearLayoutManager(this));
        mlistViewMeal.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mlistViewMeal2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();

        df = fStore.collection("users").document(user.getUid());
        diaryInf = df.collection("diary_info").document("" + dateFormat2.format(dt));
        diaryInfoFood = diaryInf.collection("diary_info_food").document("" + value + " " + dateFormat3.format(myCalendar.getTime()));

        arrayList = new ArrayList<MealModel>();
        mealAdapter = new MealAdapter(AddMealScreen.this, arrayList);
        mlistViewMeal.setAdapter(mealAdapter);

        search.setOnClickListener(view -> {
            searchM();
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mealAdapter2.getFilter().filter(newText);
                return false;
            }
        });

        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Deleting();
                arrayList.remove(position);
                mealAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(mlistViewMeal);
*/

        EventChangeListener();
        All_kal();

        value = getIntent().getStringExtra("meal_type");
        meal.setText(value);
        close.setOnClickListener(view -> {
            Close();
            search.clearFocus();
        });
        enter.setOnClickListener(view -> {
            Close();
            search.clearFocus();
        });
        manual.setOnClickListener(view -> {
            BottomSheetAddMeal bottomSheetAddMeal = new BottomSheetAddMeal();

            Bundle args = new Bundle();
            args.putLong("date", dt.getTime());
            args.putString("value", value);

            bottomSheetAddMeal.setArguments(args);
            bottomSheetAddMeal.show(getSupportFragmentManager(), "TAG");
        });
    }


    private void searchM() {
        if (closing==true){
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110, getResources().getDisplayMetrics());
            head1.getLayoutParams().height = height;
            head1.requestLayout();
            arrayList.clear();

            manual_LL.setVisibility(View.GONE);
            meal.setVisibility(View.GONE);
            all_kal.setVisibility(View.GONE);
            close.setImageResource(R.drawable.arrow_back);
            search.setIconified(false);
            closing=false;


            arrayList2 = new ArrayList<SearchModel>();
            mealAdapter2 = new SearchAdapter(AddMealScreen.this, arrayList2);
            mlistViewMeal2.setAdapter(mealAdapter2);

            Bundle args = new Bundle();
            args.putLong("date", dt.getTime());
            args.putString("value", value);

            MealRView();
        }
    }

    private void inital() {
        head1 = findViewById(R.id.head1);
        manual_LL = findViewById(R.id.manual_LL);
        search = findViewById(R.id.search);
        close = findViewById(R.id.close);
        all_kal = findViewById(R.id.all_kal);
        meal = findViewById(R.id.meal);
        enter = findViewById(R.id.enter);
        manual = findViewById(R.id.manual);
        mlistViewMeal = findViewById(R.id.rec_view);
        mlistViewMeal2 = findViewById(R.id.rec_view);
    }

    public void Close() {
        if (closing==true){
            finish();
        }else {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
            head1.getLayoutParams().height = height;
            head1.requestLayout();
            manual_LL.setVisibility(View.VISIBLE);
            meal.setVisibility(View.VISIBLE);
            all_kal.setVisibility(View.VISIBLE);
            close.setImageResource(R.drawable.close);
            search.setIconified(true);
            closing=true;
            arrayList = new ArrayList<MealModel>();
            mealAdapter = new MealAdapter(AddMealScreen.this, arrayList);
            mlistViewMeal.setAdapter(mealAdapter);
            EventChangeListener();
        }
    }

    public void All_kal() {
        collectionReference = diaryInf.collection("diary_info_food");
        Query query = collectionReference.whereEqualTo("meal_type", getIntent().getStringExtra("meal_type"));
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    long count = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        kal_eat_doc = document.getLong("kal_food");
                        count = count + kal_eat_doc;
                        all_kal.setText(String.valueOf("Всего добавлено: " + Math.round(count) + " ккал"));
                    }
                } else {
                    Toast.makeText(AddMealScreen.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void Deleting() {

        collectionReference= diaryInfoFood.collection("diary_info_food");


        BottomSheetAddMeal bottomSheetAddMeal = BottomSheetAddMeal.GetInstance();



        collectionReference.document(""+bottomSheetAddMeal.id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddMealScreen.this, "Удалено", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMealScreen.this, "Не удалено", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
    private void MealRView() {
        fStore.collection("meal")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("error", error.getMessage());
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList2.add(dc.getDocument().toObject(SearchModel.class));
                            }
                        }
                        mealAdapter2.notifyDataSetChanged();

                    }
                });
    }
    private void EventChangeListener() {
        diaryInf.collection("diary_info_food").whereEqualTo("meal_type", getIntent().getStringExtra("meal_type"))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("error", error.getMessage());
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList.add(dc.getDocument().toObject(MealModel.class));
                                All_kal();
                            }
                        }
                        mealAdapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public void onBackPressed() {
        Close();
    }
}