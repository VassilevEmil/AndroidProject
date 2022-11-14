package com.example.androidproject.Model.Market;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;

import java.util.List;

public class MarketRepository {

    private static MarketRepository instance;
    private final MutableLiveData<List<Market>> marketData;
    
    private MarketRepository(){
        marketData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Market>> getMarketData(){
        return marketData;
    }
    
    public static synchronized MarketRepository getInstance(){
        if(instance == null)
            instance = new MarketRepository();
        return instance;
    }


}
