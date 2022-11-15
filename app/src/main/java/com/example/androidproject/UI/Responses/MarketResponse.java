package com.example.androidproject.UI.Responses;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Entities.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class MarketResponse {

    private ArrayList<Market> results;

    public MarketResponse(ArrayList<Market> results){
        this.results = results;
    }

    public ArrayList<Market> getMarket() {
        return results;
    }

    public void setResults(ArrayList<Market> results) {
        this.results = results;
    }

}
