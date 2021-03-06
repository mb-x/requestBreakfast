package com.devgrafix.requestbreakfast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.devgrafix.requestbreakfast.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button btn_newPerson;
    protected Button btn_newFood;
    protected Button btn_newBreakFast;
    protected Button btn_listPersons;
    protected Button btn_listFoods;
    protected FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initViews();
        initEvents();
    }

    protected void initViews(){
        btn_newFood = (Button)findViewById(R.id.btn_newFood);
        btn_newPerson = (Button)findViewById(R.id.btn_newPerson);
        btn_newBreakFast = (Button)findViewById(R.id.btn_newBreakFast);
        btn_listPersons = (Button) findViewById(R.id.btn_listPersons);
        btn_listFoods = (Button) findViewById(R.id.btn_listFoods);
    }
    protected void initEvents(){
        btn_newFood.setOnClickListener(this); // calling onClick() method
        btn_newPerson.setOnClickListener(this);
        btn_newBreakFast.setOnClickListener(this);
        btn_listFoods.setOnClickListener(this);
        btn_listPersons.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        Intent nextIntent = null;
        switch (v.getId()) {
            case R.id.btn_newPerson:
                nextIntent = new Intent(HomeActivity.this, newPersonActivity.class);
                HomeActivity.this.startActivity(nextIntent);
                break;

            case R.id.btn_newFood:
                nextIntent = new Intent(HomeActivity.this, newFoodActivity.class);
                HomeActivity.this.startActivity(nextIntent);
                break;

            case R.id.btn_newBreakFast:
                nextIntent = new Intent(HomeActivity.this, BreakfastActivity.class);
                HomeActivity.this.startActivity(nextIntent);
                break;
            case R.id.btn_listFoods:
                nextIntent = new Intent(HomeActivity.this, ListFoodActivity.class);
                HomeActivity.this.startActivity(nextIntent);
                break;
            case R.id.btn_listPersons:
                nextIntent = new Intent(HomeActivity.this, listPresonsActivity.class);
                HomeActivity.this.startActivity(nextIntent);
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
