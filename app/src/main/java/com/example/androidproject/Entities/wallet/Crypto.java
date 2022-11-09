package com.example.androidproject.Entities.wallet;

public class Crypto {
    private String uid;
    private String cryptoName;
    private float value;

    public Crypto() {
    }

    public Crypto(String uid, String cryptoName, float value) {
        this.uid = uid;
        this.cryptoName = cryptoName;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float valueæ) {
        this.value = valueæ;
    }

    public String getUid() {
        return uid;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "uid='" + uid + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                '}';
    }
}
