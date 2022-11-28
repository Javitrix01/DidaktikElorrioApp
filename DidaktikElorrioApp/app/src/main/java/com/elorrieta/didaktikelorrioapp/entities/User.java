package com.elorrieta.didaktikelorrioapp.entities;

import androidx.room.*;

//ESTRUCTURA DE UNA ENTIDAD
@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    //para un atributo que no conste en la base de datos
    @Ignore
    public String atributoIgnorado;

    //constructor
    public User(int uid, String firstName, String lastName, String email) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

