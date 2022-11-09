package com.example.androidproject.Model.Market;

import androidx.lifecycle.LiveData;

import com.example.androidproject.Entities.Market.Market;

import java.util.List;

public class MarketRepository {

    private final MarketDao marketDao;
    private static MarketRepository instance;
    
    private MarketRepository(){
        marketDao = MarketDao.getInstance();
    }
    
    public static MarketRepository getInstance(){
        if(instance == null)
            instance = new MarketRepository();
        return instance;
    }

    public LiveData<List<Market>> getMarketData() {
        return  marketDao.getMarketData();
    }
}
