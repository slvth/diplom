package com.example.lifestyle.UI.Nutrition.Diary.Add.Search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.BottomSheets.BottomSheetSearch;
import com.example.lifestyle.UI.Nutrition.Diary.Add.AddMealScreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {
    LayoutInflater layoutInflater;
    List<SearchModel> searchList;

    public SearchAdapter(Context context, List<SearchModel> searchList) {
        this.layoutInflater = layoutInflater.from(context);
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.search_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchModel search = searchList.get(position);

        holder.name.setText(search.getName());
        holder.kal.setText(search.getKal()+" ккал");
        holder.add_item.setOnClickListener(view -> {
            holder.search.clearFocus();
            holder.args.putString("name", search.getName());

            holder.args.putLong("carbohydrate", search.getCarbohydrate());
            holder.args.putLong("fat", search.getFat());
            holder.args.putLong("protein", search.getProtein());

            holder.args.putLong("date", holder.date.getTime());
            holder.args.putString("value", holder.value);

            holder.args.putLong("kal", search.getKal());
            holder.bottomSheetSearch .setArguments(holder.args);
            holder.bottomSheetSearch.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(),holder.bottomSheetSearch.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
    @Override
    public Filter getFilter() {

        return myFilter;
    }
    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SearchModel> searchModels=new ArrayList<>();


            if (charSequence == null || charSequence.length() == 0) {
                searchModels.addAll(searchList);
            }else {
                for (SearchModel searchModel:searchList){
                    if (searchModel.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        searchModels.add(searchModel);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = searchModels;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            searchList.clear();
            searchList.addAll((Collection<SearchModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kal,name,carbohydrate,fat,protein;
        RelativeLayout add_item;
        BottomSheetSearch bottomSheetSearch=new BottomSheetSearch();
        Bundle args = new Bundle();
        Date date;
        String value;
        public androidx.appcompat.widget.SearchView search;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Description_food);
            kal = itemView.findViewById(R.id.kal_food);
            add_item = itemView.findViewById(R.id.add_item);

            date =((AddMealScreen)itemView.getContext()).dt;
            value =((AddMealScreen)itemView.getContext()).value;
            search =((AddMealScreen)itemView.getContext()).search;

            carbohydrate = itemView.findViewById(R.id.carbohydrate);
            fat = itemView.findViewById(R.id.fat);
            protein = itemView.findViewById(R.id.protein);
        }
    }
}
