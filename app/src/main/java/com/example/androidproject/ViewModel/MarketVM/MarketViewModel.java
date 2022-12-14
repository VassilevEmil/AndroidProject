package com.example.androidproject.ViewModel.MarketVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Model.Market.MarketRepository;

import java.util.List;

public class MarketViewModel extends ViewModel {

    private final MarketRepository repository;

    public MarketViewModel() {
        repository = MarketRepository.getInstance();
    }

    public void getMarket(String currency, String order, int per_page, String percentageChangeTime,
                          boolean sparkline) {
        repository.loadMarket(currency, order, per_page, percentageChangeTime, sparkline);
    }
    public LiveData < List < Market >> getMarketData() {
        return repository.getMarketData();
    }

    public LiveData < List < Double >> getSparklineData(String cryptoSymbol) {
        return repository.getSparklineData(cryptoSymbol);
    }

}