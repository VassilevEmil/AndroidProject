package com.example.androidproject.ViewModel.MarketVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Model.Market.MarketRepository;

import java.util.List;

public class MarketViewModel extends ViewModel {

    private final MarketRepository repository;

    public MarketViewModel(){
        repository = MarketRepository.getInstance();
    }

    public LiveData<List<Market>> getMarketData(){
        return repository.getMarketData();
    }

}
