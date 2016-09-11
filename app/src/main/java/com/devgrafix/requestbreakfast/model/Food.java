package com.devgrafix.requestbreakfast.model;

/**
 * Created by PC-MA13 on 10/09/2016.
 */
public class Food {

    private int id;
    private String foodName;
    private Float foodPrice;

    public Food() {
    }

    public Food(String foodName, Float foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
