package com.devgrafix.requestbreakfast.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.devgrafix.requestbreakfast.model.Food;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sbxramses on 17/04/16.
 */
public class FoodManager extends EntityManager {
    public static final String TABLE_NAME= "tbl_food";

    public static final String ID= "id";

    public static final String NAME= "food_name";
    public static final String PRICE= "food_price";




    private String[] allColumns = { ID, NAME, PRICE };

    public FoodManager(Context context) {
        super(context);
    }


    public List<Food> findAll() {
        Cursor cursor = null;
        List<Food> foods = new ArrayList<>();
        try{
            this.open();
            cursor = database.query(TABLE_NAME,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Food food = cursorToEntity(cursor);
                foods.add(food);
                cursor.moveToNext();
            }
            // assurez-vous de la fermeture du curseur
            //cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.close();
        }

        return foods;
    }

    /**
     *
     */
    public long add(String foodName, Float price){
        //Log.e("Folder Add", entity.getName()+" -- "+entity.getOrder());
        long insertId = 0;
        try {
            open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, foodName);
            contentValues.put(PRICE, price);
            insertId = database.insert(TABLE_NAME, null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return insertId;
    }

    public void delete(long id) {
        try {
            open();
            database.delete(TABLE_NAME, ID + " = " + id, null);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

    }
    /**
     *
     */
    public void update(Food food) {
        try {
            open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, food.getFoodName());
            contentValues.put(PRICE, food.getFoodPrice());
            database.update(TABLE_NAME, contentValues, ID + " = ?",
                    new String[]{String.valueOf(food.getId()) });
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    /**
     * @param id l'identifiant du métier à récupérer
     */
    public Food findById(long id) {
        Food food = null;
        try {
            open();
            Cursor cursor =  database.query(TABLE_NAME, allColumns, ID + " = " + id, null,
                    null, null, null);
            cursor.moveToFirst();
            food = cursorToEntity(cursor);
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return food;
    }


    protected Food cursorToEntity(Cursor cursor){

        Food food = new Food();
        food.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        food.setFoodName(cursor.getString(cursor.getColumnIndex(NAME)));
        food.setFoodPrice(cursor.getFloat(cursor.getColumnIndex(PRICE)));
        return food;
    }



}
