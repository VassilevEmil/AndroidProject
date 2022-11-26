package com.example.androidproject.Entities.Market;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Market implements Serializable
{

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("current_price")
    @Expose
    private Double currentPrice;
    @SerializedName("market_cap")
    @Expose
    private Long marketCap;
    @SerializedName("market_cap_rank")
    @Expose
    private Integer marketCapRank;
    @SerializedName("price_change_percentage_24h")
    @Expose
    private Double priceChangePercentage24h;
    private final static long serialVersionUID = 747698509230512162L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Market() {
    }

    /**
     *
     * @param symbol
     * @param image
     * @param marketCap
     * @param priceChangePercentage24h
     * @param marketCapRank
     * @param name
     * @param currentPrice
     */
    public Market(String symbol, String name, String image, Double currentPrice, Long marketCap, Integer marketCapRank, Double priceChangePercentage24h) {
        super();
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public Integer getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public Double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(Double priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

}