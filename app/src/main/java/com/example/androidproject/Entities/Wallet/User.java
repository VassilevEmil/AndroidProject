package com.example.androidproject.Entities.Wallet;

import java.util.ArrayList;


public class User {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;

    public User(){
    }

    public User(String uid, String email, String firstName, String lastName) {
        this.uid = uid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUid() {
        return uid;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ID: \t" + uid + "\t\n" +
                "Email: \t" + email + "\t\n" +
                "First name: \t" + firstName + "\t\n" +
                "Last name: \t" + lastName + "\t\n";
    }
}
