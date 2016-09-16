package com.devgrafix.requestbreakfast.foodList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Food;

import java.util.List;

/**
 * Created by PC-MA13 on 11/09/2016.
 */
public class FoodsAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private List<Food> foodsList;
    static Context context;
    private int position;
    ContextMenu.ContextMenuInfo info;


    public FoodsAdapter(List<Food> foodList, Context context){
        this.foodsList = foodList;
        this.context = context;
    }
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food_row, parent, false);
        return new FoodViewHolder(itemView);
    }
    public void onBindViewHolder(FoodViewHolder holder, int position){
        Food food = foodsList.get(position);
        holder.foodName.setText(food.getFoodName());
        holder.foodPrice.setText(String.valueOf(food.getFoodPrice()));
        this.position = position;
    }
    public Food getSelectedFood(){
        return foodsList.get(position);
    }
    public int getPosition(){
        return position;
    }
    public int getItemCount(){
        return foodsList.size();
    }
}
