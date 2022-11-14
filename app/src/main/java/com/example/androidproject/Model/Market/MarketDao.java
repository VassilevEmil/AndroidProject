package com.example.androidproject.Model.Market;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;


import java.util.ArrayList;
import java.util.List;

public class MarketDao {
    private final MutableLiveData<List<Market>> marketData;
    private static MarketDao instance;

    private MarketDao(){
        marketData = new MutableLiveData<>();
        List<Market> marketList =  new ArrayList<>();
        marketData.setValue(marketList);
    }

    public static MarketDao getInstance(){
        if(instance == null){
            instance = new MarketDao();
        }
        return instance;
    }

    public LiveData<List<Market>> getMarketData(){
        return marketData;
    }


}
