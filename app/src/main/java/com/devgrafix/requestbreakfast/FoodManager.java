package com.devgrafix.requestbreakfast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;


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


    public Cursor findAll() {
        Cursor cursor = null;
        try{
            this.open();
            cursor = database.query(TABLE_NAME,
                    allColumns, null, null, null, null, null);

            /*cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Folder folder = cursorToEntity(cursor);
                folders.add(folder);
                cursor.moveToNext();
            }*/
            // assurez-vous de la fermeture du curseur
            //cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.close();
        }

        return cursor;
    }

    /**
     *
     */
    public long add(String foodName, String price){
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
    public void edit(Long id, String name, String price) {
    }
    /**
     * @param id l'identifiant du métier à récupérer
     */
    public String[] findById(long id) {
        String[] food = new String[3];
        try {
            open();
            Cursor cursor =  database.query(TABLE_NAME, allColumns, ID + " = " + id, null,
                    null, null, null);
            cursor.moveToFirst();
            food[0] = Long.toString(cursor.getLong(0));
            food[1] = cursor.getString(1);
            food[3] = cursor.getString(2);
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return food;
    }





}
