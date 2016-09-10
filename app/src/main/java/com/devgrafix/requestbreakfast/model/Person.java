package com.devgrafix.requestbreakfast.model;

/**
 * Created by PC-MA13 on 10/09/2016.
 */
public class Person {
    private int id;
    private String pseudo;
    private String description;
    private String avatar;

    public Person() {
    }

    public Person(String pseudo, String description) {
        this.pseudo = pseudo;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
