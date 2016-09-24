package com.devgrafix.requestbreakfast.foodList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.activities.editFoodActivity;
import com.devgrafix.requestbreakfast.model.Food;

import java.security.PrivateKey;

/**
 * Created by PC-MA13 on 21/09/2016.
 */
public class FoodMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
    Context mContext;
    Food food;
    int position;
    public FoodMenuItemClickListener(Context context, Food pFood) {
        mContext = context;
        food = pFood;
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //case R.id.action_add_favourite:
            case R.id.action_edit_food:
                Intent nextIntent = new Intent(mContext, editFoodActivity.class);
                nextIntent.putExtra("foodId", food.getId());
                nextIntent.putExtra("foodName", food.getFoodName());
                nextIntent.putExtra("foodPrice", food.getFoodPrice());
                //Toast.makeText(mContext, food.getFoodPrice() , Toast.LENGTH_LONG).show();
                ((Activity) mContext).startActivityForResult(nextIntent, 199);

                return true;
            //case R.id.action_play_next:
            case R.id.action_delete_food:
                Toast.makeText(mContext, "Play next "+ food.getFoodName() , Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return false;
    }
}
