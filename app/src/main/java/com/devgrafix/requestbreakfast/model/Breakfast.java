package com.devgrafix.requestbreakfast.model;

import java.util.List;

/**
 * Created by PC-MA13 on 25/09/2016.
 */
public class Breakfast {

    protected Person person;
    protected Food food;
    protected int quantity;

    public Breakfast() {
    }

    public Breakfast(Person person, Food food, int quantity) {
        this.person = person;
        this.food = food;
        this.quantity = quantity;
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
}
