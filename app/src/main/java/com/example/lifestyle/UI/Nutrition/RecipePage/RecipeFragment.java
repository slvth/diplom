package com.example.lifestyle.UI.Nutrition.RecipePage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.RecipePage.Fast.FastAdapter;
import com.example.lifestyle.UI.Nutrition.RecipePage.Shake.ShakeAdapter;
import com.example.lifestyle.UI.Nutrition.RecipePage.Top.TopAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    RecyclerView rec_shake,rec_fast,rec_top;
    ArrayList<RecipeModel> arrayList1,arrayList2,arrayList3;
    ShakeAdapter shakeAdapter;
    FastAdapter fastAdapter;
    TopAdapter topAdapter;

    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DocumentReference df;
    Chip chip1,chip2,chip3;
    String type="breakfast";
    ChipGroup chipGroup;
    TextView second,third;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe, container, false);

        second=v.findViewById(R.id.second);
        third=v.findViewById(R.id.third);
        chip1=v.findViewById(R.id.chip1);
        chip2=v.findViewById(R.id.chip2);
        chip3=v.findViewById(R.id.chip3);
        chipGroup=v.findViewById(R.id.chipGroup);

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                arrayList1.clear();
                arrayList2.clear();
                arrayList3.clear();
                if (chip1.isChecked()){
                    second.setText("Шейк");
                    third.setText("Быстрое приготовление");
                    type="breakfast";
                }else if (chip2.isChecked()){
                    second.setText("Салат");
                    third.setText("Суп");
                    type="dinner";
                }else if (chip3.isChecked()){
                    second.setText("Запеканка");
                    third.setText("Картошка");
                    type="lunch";
                }
                ShakeRView();
                FastRView();
                TopRView();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();

        rec_shake = v.findViewById(R.id.rec_shake);
        rec_fast = v.findViewById(R.id.rec_fast);
        rec_top = v.findViewById(R.id.rec_top);

        rec_shake.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rec_fast.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rec_top.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        arrayList1 = new ArrayList<RecipeModel>();
        arrayList2 = new ArrayList<RecipeModel>();
        arrayList3 = new ArrayList<RecipeModel>();

        shakeAdapter = new ShakeAdapter(getActivity(), arrayList1);
        fastAdapter = new FastAdapter(getActivity(), arrayList2);
        topAdapter = new TopAdapter(getActivity(), arrayList3);

        rec_shake.setAdapter(shakeAdapter);
        rec_fast.setAdapter(fastAdapter);
        rec_top.setAdapter(topAdapter);

        ShakeRView();
        FastRView();
        TopRView();

        return v;
    }

    private void ShakeRView() {
        fStore.collection("meal").whereEqualTo("type","shake "+type)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("error", error.getMessage());
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList1.add(dc.getDocument().toObject(RecipeModel.class));
                            }
                        }
                        shakeAdapter.notifyDataSetChanged();

                    }
                });
    }
    private void FastRView() {
        fStore.collection("meal").whereEqualTo("type","fast "+type)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("error", error.getMessage());
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList2.add(dc.getDocument().toObject(RecipeModel.class));
                            }
                        }
                        fastAdapter.notifyDataSetChanged();

                    }
                });
    }
    private void TopRView() {
        fStore.collection("meal").whereEqualTo("type","top "+type)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("error", error.getMessage());
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList3.add(dc.getDocument().toObject(RecipeModel.class));
                            }
                        }
                        topAdapter.notifyDataSetChanged();

                    }
                });
    }
}