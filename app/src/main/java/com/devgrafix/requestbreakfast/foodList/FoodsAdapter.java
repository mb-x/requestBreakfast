package com.devgrafix.requestbreakfast.foodList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Food;

import java.util.List;

/**
 * Created by PC-MA13 on 11/09/2016.
 */
public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodViewHolder> {
    private List<Food> foodsList;

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName, foodPrice;

        public FoodViewHolder(View view) {
            super(view);
            foodName = (TextView) view.findViewById(R.id.txt_food_name);
            foodPrice = (TextView) view.findViewById(R.id.txt_food_price);

        }
    }

    public FoodsAdapter(List<Food> foodList){
        this.foodsList = foodList;
    }
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food_row, parent, false);
        return new FoodViewHolder(itemView);
    }
    public void onBindViewHolder(FoodViewHolder holder, int position){
        Food food = foodsList.get(position);
        holder.foodName.setText(food.getFoodName());
        holder.foodPrice.setText(String.valueOf(food.getFoodPrice()));
    }
    public int getItemCount(){
        return foodsList.size();
    }
}
