package com.example.androidproject.UI.Responses;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Entities.NewsModel;

public class MarketResponse {

    private String image, symbol;
    private double current_price, price_change_24h, market_cap;
    private int market_cap_rank;

    public Market getMarket(){
        return new Market(market_cap_rank, image, symbol, current_price, price_change_24h, market_cap);
    }
}
