package com.devgrafix.requestbreakfast.foodList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Food;

import java.util.List;

/**
 * Created by PC-MA13 on 16/09/2016.
 */
public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    public TextView foodName, foodPrice;
    public ImageView thumbnail, overflow;
    private Food food;
    private List<Food> foodList;
    private Context context;
    public FoodViewHolder(View view, Context pContext, List<Food> pFoodList) {
        super(view);
        context = pContext;
        foodList = pFoodList;
        foodName = (TextView) view.findViewById(R.id.food_title);
        foodPrice = (TextView) view.findViewById(R.id.food_price);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        overflow = (ImageView) view.findViewById(R.id.overflow);
        //view.setOnClickListener(this);
        view.setOnCreateContextMenuListener(this);

    }
    public void bindData(Food mfood){
        this.food = mfood;
        foodName.setText(food.getFoodName());
        foodPrice.setText(String.valueOf(food.getFoodPrice()));
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(overflow);
            }
        });
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(this.context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_food, popup.getMenu());

        popup.setOnMenuItemClickListener(new FoodMenuItemClickListener(this.context, foodList.get(getAdapterPosition())));
        popup.show();
    }

   /* public void onClick(View v){
        Toast.makeText(FoodsAdapter.context, "you have clicked from holder Row " + getPosition(), Toast.LENGTH_LONG).show();
    }*/
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        String[] menuItems = FoodsAdapter.context.getResources().getStringArray(R.array.menu_edit_delete_items);
        menu.setHeaderTitle(foodName.getText());
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }

    }
    public boolean onMenuItemClick(MenuItem item) {
        // Menu Item Clicked!
        return true;
    }


}
