package com.example.lifestyle.UI.Nutrition.RecipePage.Fast;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.RecipePage.AboutMealScreen;
import com.example.lifestyle.UI.Nutrition.RecipePage.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FastAdapter extends RecyclerView.Adapter<FastAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    List<RecipeModel> fastList;
    RelativeLayout meal_item_id;
    Long kalL,timeL;
    String img_mealS, nameS,description1,description2,description3, complexity;

    public FastAdapter(Context context, List<RecipeModel> fastList) {
        this.layoutInflater = layoutInflater.from(context);
        this.fastList = fastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeModel shake = fastList.get(position);

        holder.name.setText(shake.getName());
        holder.kal.setText(shake.getKal()+" ккал");
        holder.time.setText(shake.getTime()+" мин");
        Picasso.get().load(fastList.get(position).getImg_meal()).into(holder.img_meal);
        meal_item_id.setOnClickListener(view -> {

            img_mealS = fastList.get(position).getImg_meal();
            kalL = fastList.get(position).getKal();
            nameS = fastList.get(position).getName();
            timeL = fastList.get(position).getTime();

            description1 = fastList.get(position).getDescription1();
            description2 = fastList.get(position).getDescription2();
            description3 = fastList.get(position).getDescription3();
            complexity = fastList.get(position).getComplexity();

            Intent intent = new Intent(view.getContext(), AboutMealScreen.class);

            intent.putExtra("kal", kalL);
            intent.putExtra("img_meal", img_mealS);
            intent.putExtra("name", nameS);
            intent.putExtra("time", timeL);
            intent.putExtra("description1", description1);
            intent.putExtra("description2", description2);
            intent.putExtra("description3", description3);
            intent.putExtra("complexity", complexity);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, kal,time;
        ImageView img_meal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            kal = itemView.findViewById(R.id.kal);
            time = itemView.findViewById(R.id.time);
            img_meal = itemView.findViewById(R.id.img_meal);
            meal_item_id = itemView.findViewById(R.id.meal_item_id);
        }
    }
}
