package com.devgrafix.requestbreakfast.foodList;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;

/**
 * Created by PC-MA13 on 16/09/2016.
 */
public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public TextView foodName, foodPrice;

    public FoodViewHolder(View view) {
        super(view);
        foodName = (TextView) view.findViewById(R.id.txt_food_name);
        foodPrice = (TextView) view.findViewById(R.id.txt_food_price);

        //view.setOnClickListener(this);
        view.setOnCreateContextMenuListener(this);

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

}
