package com.devgrafix.requestbreakfast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.managers.FoodManager;
import com.devgrafix.requestbreakfast.model.Food;

public class editFoodActivity extends AppCompatActivity {

    protected EditText edt_foodName;
    protected EditText edt_foodPrice;
    protected int foodId;
    protected Button btnSaveFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        Intent intent = getIntent();
        foodId = intent.getIntExtra("foodId", 0);
        String foodName= intent.getStringExtra("foodName");
        Float foodPrice= intent.getFloatExtra("foodPrice", 0);
        initViews();
        edt_foodName.setText(foodName);
        edt_foodPrice.setText(String.valueOf(foodPrice));

        initEvents();
    }

    protected void initViews(){
        edt_foodName = (EditText) findViewById(R.id.edt_foodName);
        edt_foodPrice = (EditText) findViewById(R.id.edt_foodPrice);
        btnSaveFood = (Button) findViewById(R.id.btn_save_food);
    }

    protected void initEvents(){
        btnSaveFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodManager foodManager = new FoodManager(getApplicationContext());
                String foodName = edt_foodName.getText().toString();
                Float foodPrice = edt_foodPrice.getText().toString() != "" ? Float.valueOf(edt_foodPrice.getText().toString()): 0;
                Food food = foodManager.findById(foodId);
                food.setFoodName(foodName);
                food.setFoodPrice(foodPrice);
                foodManager.update(food);
                Toast.makeText(getApplicationContext(), "The food " + foodName + " is successfully saved", Toast.LENGTH_LONG).show();
                Intent newIntent = new Intent();
                newIntent.putExtra("foodName", food.getFoodName());
                setResult(199, newIntent);
                finish();
            }
        });
    }

}
