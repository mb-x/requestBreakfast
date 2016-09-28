package com.devgrafix.requestbreakfast.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
/**
 * Created by PC-MA13 on 25/09/2016.
 */
public class Breakfast extends Model {

    @Column(name = "person" , uniqueGroups = {"groupBreakfast"}, onUniqueConflicts = {Column.ConflictAction.REPLACE})
    protected Person person;
    @Column(name = "food", uniqueGroups = {"groupBreakfast"}, onUniqueConflicts = {Column.ConflictAction.REPLACE})
    protected Food food;
    @Column(name = "date", uniqueGroups = {"groupBreakfast"}, onUniqueConflicts = {Column.ConflictAction.REPLACE})
    protected String date;

    @Column(name = "quantity")
    protected int quantity;

    public Breakfast() {
        super();
    }

    public Breakfast(Person person, Food food, int quantity, String date) {
        this.person = person;
        this.food = food;
        this.quantity = quantity;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity < 1 ? 1 : quantity;
    }

    public static List<Breakfast> findByPersonAndDate(Person person , String date){
        return new Select()
                .from(Breakfast.class)
                .where("person = ?", person.getId())
                .where("date = ?", date)
                .execute();
    }

    public static String convertDateToFormatedString(Date date) {
        // Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = formatter.format(date);
        System.out.println("formatedDate : " + formatedDate);
        return formatedDate;
    }
    /*public Date getDateFromString(String date){
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        this.date = format.parse(date);
        return this.date;
    }*/
   /* public static List<Breakfast> findRecent(Date newerThan) {
        return new Select().from(Breakfast.class).where("date > ?", newerThan.getTimeInMillis()).execute();
    }*/

}
