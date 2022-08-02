package com.example.lifestyle.UI.Nutrition.Diary.Add;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.BottomSheets.BottomSheetAllMeal;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    LayoutInflater layoutInflater;
    List<MealModel> mealList;

    public MealAdapter(Context context, List<MealModel> mealList) {
        this.layoutInflater = layoutInflater.from(context);
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.add_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealModel meal = mealList.get(position);

        holder.Description_food.setText(meal.getDescription_food());
        holder.kal_food.setText(meal.getKal_food()+" ккал");
        holder.add_item.setOnClickListener(view -> {

            holder.args.putLong("carbohydrate", meal.getCarbohydrates_food());
            holder.args.putLong("fat", meal.getFat_food());
            holder.args.putLong("protein", meal.getProteins_food());

            holder.args.putLong("portion", meal.getPortion());

            holder.args.putString("name", meal.getDescription_food());
            holder.args.putLong("kal", meal.getKal_food());

            holder.bottomSheetAllMeal .setArguments(holder.args);
            holder.bottomSheetAllMeal.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(),holder.bottomSheetAllMeal.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Description_food, kal_food,kal,name,carbohydrate,fat,protein,portion;
        RelativeLayout add_item;
        BottomSheetAllMeal bottomSheetAllMeal=new BottomSheetAllMeal();
        Bundle args = new Bundle();
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            add_item = itemView.findViewById(R.id.add_item);
            Description_food = itemView.findViewById(R.id.Description_food);
            kal_food = itemView.findViewById(R.id.kal_food);

            carbohydrate = itemView.findViewById(R.id.carbohydrate);
            fat = itemView.findViewById(R.id.fat);
            protein = itemView.findViewById(R.id.protein);
            portion = itemView.findViewById(R.id.portionTXT);
        }
    }
}
