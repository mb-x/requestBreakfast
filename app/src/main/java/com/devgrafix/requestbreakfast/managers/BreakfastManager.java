package com.devgrafix.requestbreakfast.managers;

import android.content.Context;
import android.database.Cursor;

import com.devgrafix.requestbreakfast.model.Breakfast;
import com.devgrafix.requestbreakfast.model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-MA13 on 26/09/2016.
 */
public class BreakfastManager extends EntityManager  {

    public static final String TABLE_NAME= "tbl_breakfast";

    public static final String ID_PERSON= "id_person";

    public static final String ID_FOOD= "id_food";

    public static final String DATE_BREAKFAST = "date_breakfast";

    public static final String QUANTITY = "quantity";

    public BreakfastManager(Context context){
        super(context);
    }

    public List<Breakfast> findAll() {
        Cursor cursor = null;
        List<Breakfast> result = new ArrayList<Breakfast>();
        try{
            this.open();
            cursor = database.query(TABLE_NAME,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Person person = cursorToEntity(cursor);
                result.add(person);

                cursor.moveToNext();
            }
            // assurez-vous de la fermeture du curseur
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.close();
        }

        return result;
    }


}
