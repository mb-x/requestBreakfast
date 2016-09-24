package com.devgrafix.requestbreakfast.foodList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Food;

import java.util.List;

/**
 * Created by PC-MA13 on 11/09/2016.
 */
public class FoodsAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private List<Food> foodsList;
    private Food food;
    static Context context;


    public FoodsAdapter(Context context, List<Food> foodList){
        this.foodsList = foodList;
        this.context = context;
    }
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);
        return new FoodViewHolder(itemView, context, foodsList);
    }
    public void onBindViewHolder(FoodViewHolder holder, int position){
        this.food = foodsList.get(position);

        holder.bindData(food);
        // loading album cover using Glide library
        //Glide.with(this.context).load(food.getThumbnail()).into(holder.thumbnail);

    }
    public List<Food> getFoodsList(){
        return this.foodsList;
    }

    public void setFoodsList(List<Food> foodsList){
        this.foodsList = foodsList;

    }


    public int getItemCount(){
        return foodsList.size();
    }
}
