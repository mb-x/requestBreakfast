package com.devgrafix.requestbreakfast.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Food;


public class newFoodActivity extends AppCompatActivity {

    protected EditText edt_foodName;
    protected EditText edt_foodPrice;
    protected Button btnSaveFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        initViews();
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

                String foodName = edt_foodName.getText().toString();
                Float foodPrice = Float.valueOf(edt_foodPrice.getText().toString());
                Food food = new Food();
                food.setFoodName(foodName);
                food.setFoodPrice(foodPrice);
                food.save();
                Toast.makeText(getApplicationContext(), "The food " + foodName + " is successfully saved", Toast.LENGTH_LONG).show();
            }
        });
    }

}
