package com.example.androidproject.Entities.Market;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SparklineIn7d implements Serializable
{

    @SerializedName("price")
    @Expose
    private List<Double> price = null;
    private final static long serialVersionUID = 1493153886366493040L;

    /**
     * No args constructor for use in serialization
     *
     */
    public SparklineIn7d() {
    }

    /**
     *
     * @param price
     */
    public SparklineIn7d(List<Double> price) {
        super();
        this.price = price;
    }

    public List<Double> getPrice() {
        return price;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }

}