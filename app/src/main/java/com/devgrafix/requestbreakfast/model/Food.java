package com.devgrafix.requestbreakfast.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by PC-MA13 on 10/09/2016.
 */
@Table(name = "tbl_food")
public class Food extends Model {


    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_price")
    private Float foodPrice;
    @Column(name = "image")
    private String image;

    public Food() {
        super();
    }

    public Food(String foodName, Float foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Float foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return foodName + " : " + foodPrice + " DH";
    }

    public static List<Food> getAll(){
        return new Select()
                .from(Food.class)
                .orderBy("food_name ASC")
                .execute();
    }
    public static Food getOneById(long id){
        return new Select()
                .from(Food.class)
                .where("Id = ?", id)
                .executeSingle();
    }
}
