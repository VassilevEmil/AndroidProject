package com.example.androidproject.Entities.Wallet;

import java.util.ArrayList;


public class User {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private float walletBallanceUSD;

    public User(){
    }

    public User(String uid, String email, String firstName, String lastName, float walletBallanceUSD, ArrayList<String> cryptos, ArrayList<String> transactionsUID) {
        this.uid = uid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.walletBallanceUSD = walletBallanceUSD;
    }


    public float getWalletBallanceUSD() {
        return walletBallanceUSD;
    }


    public void setWalletBallanceUSD(float walletBallanceUSD) {
        this.walletBallanceUSD = walletBallanceUSD;
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
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", walletBallanceUSD=" + walletBallanceUSD +
                '}';
    }
}
