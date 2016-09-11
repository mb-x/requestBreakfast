package com.devgrafix.requestbreakfast.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.devgrafix.requestbreakfast.model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sbxramses on 17/04/16.
 */
public class PersonManager extends EntityManager {
    public static final String TABLE_NAME= "tbl_person";

    public static final String ID= "id";

    public static final String NAME= "person_name";




    private String[] allColumns = { ID, NAME };

    public PersonManager(Context context) {
        super(context);
    }


    public List<Person> findAll() {
        Cursor cursor = null;
        List<Person> result = new ArrayList<Person>();
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

    /**
     *
     */
    public long add(String personName){
        //Log.e("Folder Add", entity.getName()+" -- "+entity.getOrder());
        long insertId = 0;
        try {
            open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, personName);
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
    public void edit(Long id, String name) {
    }
    /**
     * @param id l'identifiant du métier à récupérer
     */
    public String[] findById(long id) {
        String[] person = new String[2];
        try {
            open();
            Cursor cursor =  database.query(TABLE_NAME, allColumns, ID + " = " + id, null,
                    null, null, null);
            cursor.moveToFirst();
            person[0] = Long.toString(cursor.getLong(0));
            person[1] = cursor.getString(1);
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return person;
    }


    protected Person cursorToEntity(Cursor cursor){

        Person person = new Person();
        person.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        person.setPseudo(cursor.getString(cursor.getColumnIndex(NAME)));
        return person;
    }



}