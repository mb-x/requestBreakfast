package com.devgrafix.requestbreakfast.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sbxramses on 24/04/16.
 */
public class DatabaseManager extends SQLiteOpenHelper{



    protected final static int VERSION = 2;
    protected final static String DATABASE_NAME = "database.db";



    public static final String TABLE_PERSON_CREATE= "CREATE TABLE IF NOT EXISTS "+ PersonManager.TABLE_NAME +" (" +
            "  id integer primary key autoincrement," +
            "  "+ PersonManager.NAME+" text not null," +
            "  "+ PersonManager.AVATAR+" text " +
            ");";

    private static final String TABLE_FOOD_CREATE = "CREATE TABLE IF NOT EXISTS "
            + FoodManager.TABLE_NAME + " ("
            +" id integer primary key autoincrement, "
            + FoodManager.NAME + " text not null, "
            + FoodManager.PRICE + " REAL not null, "
            + FoodManager.IMAGE + " text "
            +");";

    private static final String TABLE_BREAKFAST_CREATE = "CREATE TABLE IF NOT EXISTS "
            + BreakfastManager.TABLE_NAME + " ("
            + BreakfastManager.ID_PERSON + " integer not null, "
            + BreakfastManager.ID_FOOD + " integer not null, "
            + BreakfastManager.DATE_BREAKFAST + " text not null, "
            + BreakfastManager.QUANTITY + " integer not null "
            +");";
    public static final String TABLE_PERSON_DROP =  "DROP TABLE IF EXISTS " + PersonManager.TABLE_NAME + ";";
    public static final String TABLE_FOOD_DROP =  "DROP TABLE IF EXISTS " +FoodManager.TABLE_NAME + ";";
    public static final String TABLE_BREAKFAST_DROP =  "DROP TABLE IF EXISTS " +BreakfastManager.TABLE_NAME + ";";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME,null, VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PERSON_CREATE);
        db.execSQL(TABLE_FOOD_CREATE);
        db.execSQL(TABLE_BREAKFAST_CREATE);
    }

    /**
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_PERSON_DROP);
        db.execSQL(TABLE_FOOD_DROP);
        db.execSQL(TABLE_BREAKFAST_DROP);
        onCreate(db);
    }
}
