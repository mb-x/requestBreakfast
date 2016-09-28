package com.devgrafix.requestbreakfast.model;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by PC-MA13 on 10/09/2016.
 */
@Table(name = "tbl_person")
public class Person extends Model implements Serializable {

    @Column(name = "person_name")
    private String pseudo;
    @Column(name = "description")
    private String description;
    @Column(name = "avatar")
    private String avatar;

    public Person() {
        super();
    }

    public Person(String pseudo, String description) {
        this.pseudo = pseudo;
        this.description = description;

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

    public static List<Person> getAll(){
        return new Select()
                .from(Person.class)
                .orderBy("person_name ASC")
                .execute();
    }
    public static Person getOneById(long id){
        return new Select()
                .from(Person.class)
                .where("Id = ?", id)
                .executeSingle();
    }

    @Override
    public String toString() {
        return  "{"+ this.getId() + "} " + this.getPseudo();
    }
}
