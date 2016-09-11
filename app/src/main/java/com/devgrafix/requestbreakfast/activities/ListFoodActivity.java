package com.devgrafix.requestbreakfast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.RecyclerView.ClickListener;
import com.devgrafix.requestbreakfast.RecyclerView.DividerItemDecoration;
import com.devgrafix.requestbreakfast.RecyclerView.RecyclerTouchListener;
import com.devgrafix.requestbreakfast.foodList.FoodsAdapter;
import com.devgrafix.requestbreakfast.managers.FoodManager;
import com.devgrafix.requestbreakfast.model.Food;

import java.util.List;

public class ListFoodActivity extends AppCompatActivity {
    private List<Food> foodList;
    private RecyclerView foodRecyclerView;
    private FoodsAdapter foodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        initViews();
        FoodManager foodManager = new FoodManager(getApplicationContext());
        foodList = foodManager.findAll();
        foodsAdapter = new FoodsAdapter(foodList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        foodRecyclerView.setLayoutManager(mLayoutManager);
        foodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foodRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        foodRecyclerView.setAdapter(foodsAdapter);

        foodRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), foodRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Food food = foodList.get(position);
                Toast.makeText(getApplicationContext(), food.getFoodName() + " is clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Food food = foodList.get(position);
                Toast.makeText(getApplicationContext(), food.getFoodName() + " is long clicked!", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void initViews(){
        foodRecyclerView = (RecyclerView) findViewById(R.id.food_recycler_view);

    }

    public void prepareFoodsData(){

        foodsAdapter.notifyDataSetChanged();
    }
}
