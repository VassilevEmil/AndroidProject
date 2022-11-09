package com.example.androidproject.Entities.Market;

public class Market {
    // USE /COINS/LIST endpoint

    int rank; // based on market_cap_rank (coingecko api documentation)
    String tokenImage_url;
    String symbol;
    double currentPrice;
    double price_change_percentage_24h;
    double market_cap;

    public Market(int rank, String tokenImage_url, String symbol, double currentPrice, double price_change_percentage_24h, double market_cap)
    {
        this.rank = rank;
        this.tokenImage_url = tokenImage_url;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.price_change_percentage_24h = price_change_percentage_24h;
        this.market_cap = market_cap;
    }

    public Market(){}

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTokenImage_url() {
        return tokenImage_url;
    }

    public void setTokenImage_url(String tokenImage_url) {
        this.tokenImage_url = tokenImage_url;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }
}
