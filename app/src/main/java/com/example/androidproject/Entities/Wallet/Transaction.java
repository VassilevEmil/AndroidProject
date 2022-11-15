package com.example.androidproject.Entities.Wallet;

import java.util.UUID;

public class Transaction {
    private String uid;
    private String note;
    private String cryptoName;
    private String date;
    private float amount;
    private boolean buy;
    private String userUID;

    public Transaction(){
        uid = UUID.randomUUID().toString();
    }

    public Transaction(String note, String cryptoName,String date, float amount, boolean buy, String userUID) {
        this.uid = UUID.randomUUID().toString();
        this.note = note;
        this.cryptoName = cryptoName;
        this.date=date;
        this.amount = amount;
        this.buy=buy;
        this.userUID=userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public boolean isBuy() {
        return buy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getUid() {
        return uid;
    }

    public float getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uid='" + uid + '\'' +
                ", note='" + note + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", buy=" + buy +
                ", userUID='" + userUID + '\'' +
                '}';
    }
}
